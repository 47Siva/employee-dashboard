package com.app.employeedashboard.calculation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.app.employeedashboard.entity.OvertimeAnalysis;

@Service
public class OvertimeCalculation {
	
	public Float calculateTotalOvertimeHoursByProject(List<Float> overtimeList, String project) {
		Float total = 0.0f;
		for (Float item : overtimeList) {
			total += item;
		}
		return total;
	}

	public Float calculatePercentageByDayTypeForProject(List<String> daysList, String dayType, Float totalOvertimeHours,
			List<Float> overtimeList, String project) {
		Float totalDayTypeHours = 0.0f;
		for (String item : daysList) {
			if (dayType.equals(item)) {
				for (float item2 : overtimeList) {
					totalDayTypeHours += item2;
				}
			}
		}
		if (totalOvertimeHours > 0) {
			return (totalDayTypeHours / totalOvertimeHours) * 100;
		} else {
			return 0.0f;
		}
	}

	public float calculateTotalOvertimeHours(List<OvertimeAnalysis> data) {
		float totalOvertimeHours = 0.0f;
		for (OvertimeAnalysis analysis : data) {
			Float overtimeHours = analysis.getOvertimeHours();
			if (overtimeHours != null) {
                totalOvertimeHours += overtimeHours.floatValue();
            } else {
            	totalOvertimeHours = 0.0f;
            }
//			totalOvertimeHours += Optional.ofNullable(analysis.getOvertimeHours()).orElse(0.0f);
		}
		return totalOvertimeHours;
	}

	public long calculateTotalEmployeesWithOvertime(List<OvertimeAnalysis> data) {
		Set<Integer> uniqueUserIds = new HashSet<>();
		for (OvertimeAnalysis analysis : data) {
			if (analysis.getOvertimeHours() > 0) {
				uniqueUserIds.add(analysis.getUserId());
			}
		}
		return uniqueUserIds.size();
	}

	public double calculateTotalCostIncurred(List<OvertimeAnalysis> data) {
		double hourlyRate = 15.0; // Assuming $15 per overtime hour
		double totalCostIncurred = 0.0;

		for (OvertimeAnalysis analysis : data) {
			float overtimeHours = analysis.getOvertimeHours();
			totalCostIncurred += overtimeHours * hourlyRate;
		}

		return totalCostIncurred;
	}

	public double calculateTotalOvertimePercentage(List<OvertimeAnalysis> data) {
		double totalOvertimeHours = calculateTotalOvertimeHours(data);
		double totalEstimatedHours = calculateTotalEstimatedHours(data); // Assuming you have a method for this

		if (totalEstimatedHours == 0) {
			return 0.0; // To handle division by zero scenario, if applicable
		}

		double overtimePercentage = (totalOvertimeHours / totalEstimatedHours) * 100;

		return overtimePercentage;
	}

	private double calculateTotalEstimatedHours(List<OvertimeAnalysis> data) {
		double totalEstimatedHours = 0.0;

		for (OvertimeAnalysis analysis : data) {
			String estimatedHoursText = analysis.getEstimatedHours();
			double estimatedHours = parseEstimatedHours(estimatedHoursText);
			totalEstimatedHours += estimatedHours;
		}

		return totalEstimatedHours;
	}

	private double parseEstimatedHours(String estimatedHoursText) {
		// Example: "08:00"
		String[] parts = estimatedHoursText.split(":");
		if (parts.length != 2) {
			return 0.0; // Handle invalid format gracefully
		}

		try {
			int hours = Integer.parseInt(parts[0]);
			int minutes = Integer.parseInt(parts[1]);
			double totalHours = hours + (minutes / 60.0); // Convert minutes to hours
			return totalHours;
		} catch (NumberFormatException e) {
			return 0.0; // Handle parsing errors gracefully
		}
	}

}
