package com.app.employeedashboard.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.employeedashboard.calculation.OvertimeCalculation;
import com.app.employeedashboard.dto.OverTimeAnalysisinList;
import com.app.employeedashboard.dto.OvertimeAnalysisRequest;
import com.app.employeedashboard.dto.OvertimeOverviewDto;
import com.app.employeedashboard.dto.ResponseDto;
import com.app.employeedashboard.entity.OvertimeAnalysis;
import com.app.employeedashboard.repository.OvertimeAnalysisRepository;

@Service
//@RequiredArgsConstructor
public class OvertimeAnalysisService {

	@Autowired
	private OvertimeAnalysisRepository overtimeAnalysisRepository;
	
	@Autowired
	public OvertimeCalculation overtimeCalculation ;

	public ResponseEntity<?> getEmployee(int id) {
		Optional<OvertimeAnalysis> optional = overtimeAnalysisRepository.findById(id);
		return ResponseEntity.ok(optional);
	}

	public ResponseEntity<?> getallEmployee() {
		List<OvertimeAnalysis> list = overtimeAnalysisRepository.findAll();
		return ResponseEntity.ok(list);
	}

	public ResponseEntity<?> getOverTimeHoursBasedDate(String fromDate, String toDate) {
		Map<String, Object> map = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();
		if (fromDate != null && toDate != null) {
			Double overtimehowers = overtimeAnalysisRepository.getOverTimeHoursBasedDate(fromDate, toDate);
			map.put("OverTimeHowers", overtimehowers);
			if (overtimehowers != 0) {
				responseDto.setData(map);
				responseDto.setStatus(HttpStatus.OK.toString());
				return ResponseEntity.ok(responseDto);
			}
			return ResponseEntity.badRequest().body("Data Null");
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}

	}

	public ResponseEntity<?> getEmployeesWithOvertimeBasedDate(String fromDate, String toDate) {

		Map<String, Object> map = new HashMap<>();
		if (fromDate != null && toDate != null) {
			int employeesWithOvertime = overtimeAnalysisRepository.getEmployeesWithOvertimeBasedDate(fromDate, toDate);
			map.put("EmployeesWithOvertime", employeesWithOvertime);
			ResponseDto responseDto = new ResponseDto();
			if (employeesWithOvertime != 0) {
				responseDto.setData(map);
				responseDto.setStatus(HttpStatus.OK.toString());
				return ResponseEntity.ok(responseDto);
			}
			return ResponseEntity.badRequest().body("Data Null");
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}
	}

	public ResponseEntity<?> getTotalCostIncurred(String fromDate, String toDate) {

		Map<String, Object> map = new HashMap<>();
		if (fromDate != null && toDate != null) {
			Double totalCost = overtimeAnalysisRepository.getTotalCostIncurred(fromDate, toDate);
			map.put("TotalCostIncurred", totalCost);
			ResponseDto responseDto = new ResponseDto();
			if (totalCost != 0) {
				responseDto.setData(map);
				responseDto.setStatus(HttpStatus.OK.toString());
				return ResponseEntity.ok(responseDto);
			}
			return ResponseEntity.badRequest().body("Data Null");
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}
	}

	public ResponseEntity<?> getOvertimePercentage(String fromDate, String toDate) {
		Map<String, Object> map = new HashMap<>();
		if (fromDate != null && toDate != null) {
			Double totalOvertimeHours = overtimeAnalysisRepository.getOverTimeHoursBasedDate(fromDate, toDate);
			Double totalEstimatedHours = overtimeAnalysisRepository.getTotalEstimatedHours(fromDate, toDate);

			if (totalEstimatedHours == 0) {
				return ResponseEntity.ok(0); // To avoid division by zero
			}

			Double overtimePercentage = (totalOvertimeHours / totalEstimatedHours) * 100;

			map.put("OvertimePercentage", overtimePercentage);
			ResponseDto responseDto = new ResponseDto();
			if (overtimePercentage != 0) {
				responseDto.setData(map);
				responseDto.setStatus(HttpStatus.OK.toString());
				return ResponseEntity.ok(responseDto);
			}
			return ResponseEntity.badRequest().body("Data Null");
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}
	}

	public ResponseEntity<?> OvertimeOverviewMetrics(String fromDate, String toDate) {

		Map<String, Object> map = new HashMap<>();
		if (fromDate != null && toDate != null) {
			Double overtimehowers = overtimeAnalysisRepository.getOverTimeHoursBasedDate(fromDate, toDate);
			int employeesWithOvertime = overtimeAnalysisRepository.getEmployeesWithOvertimeBasedDate(fromDate, toDate);
			Double totalCost = overtimeAnalysisRepository.getTotalCostIncurred(fromDate, toDate);
			Double totalEstimatedHours = overtimeAnalysisRepository.getTotalEstimatedHours(fromDate, toDate);
			ResponseDto responseDto = new ResponseDto();
			if (totalEstimatedHours == 0) {
				return ResponseEntity.ok(0); // To avoid division by zero
			}

			Double overtimePercentage = (overtimehowers / totalEstimatedHours) * 100;

			OvertimeOverviewDto overtimeOverviewDto = OvertimeOverviewDto.builder().totalOvertimeHours(overtimehowers)
					.totalEmployeesWithOvertime(employeesWithOvertime).totalCastincurred(totalCost)
					.totalOvertimepercentage(overtimePercentage).build();

			if (overtimeOverviewDto != null) {
				responseDto.setData(overtimeOverviewDto);
				responseDto.setStatus(HttpStatus.OK.toString());
				return ResponseEntity.ok(responseDto);
			}
			return ResponseEntity.badRequest().body("Data Null");
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}
	}

	public ResponseEntity<?> OvertimeOverviewMetricsFilter(OvertimeAnalysisRequest request) {
		Map<String, Object> responmap = new HashMap<>();

		// Prepare filters based on request fields
		String fromDate = request.getFromDate();
		String toDate = request.getToDate();
		String organizationName = request.getOrganizationName();
		String branchName = request.getBranchName();
		String departmentName = request.getDepartmentName();
		String categoryName = request.getCategoryName();
		String designationName = request.getDesignationName();
		String gradeName = request.getGradeName();
		String sectionName = request.getSectionName();
		String projectName = request.getProjectName();
		String phaseName = request.getPhaseName();
		String jobName = request.getJobName();
		String employeeName = request.getEmployeeName();

		List<OvertimeAnalysis> overtimeData = overtimeAnalysisRepository.findByFilters(fromDate, toDate,
				organizationName, branchName, departmentName, categoryName, designationName, gradeName, sectionName,
				projectName, phaseName, jobName, employeeName);

		float totalOvertimeHours =overtimeCalculation.calculateTotalOvertimeHours(overtimeData);
		long totalEmployeesWithOvertime = overtimeCalculation.calculateTotalEmployeesWithOvertime(overtimeData);
		double totalCostIncurred = overtimeCalculation.calculateTotalCostIncurred(overtimeData);
		double totalOvertimePercentage =overtimeCalculation.calculateTotalOvertimePercentage(overtimeData);
		
		OvertimeOverviewDto overtimeOverviewDto = OvertimeOverviewDto.builder()
				.totalOvertimeHours(totalOvertimeHours)
				.totalOvertimeHours(totalEmployeesWithOvertime)
				.totalCastincurred(totalCostIncurred)
				.totalOvertimepercentage(totalOvertimePercentage).build();
		return ResponseEntity.ok(overtimeOverviewDto);
	}

	public ResponseEntity<?> getTotalProjectBasedDate(String fromDate, String toDate) {
		Map<String, Object> map = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();

		if (fromDate != null && toDate != null) {
			List<OverTimeAnalysisinList> list = overtimeAnalysisRepository.findAllProjectsBasedDate(fromDate, toDate);
			map.put("Projects", list);
			responseDto.setData(map);
			responseDto.setStatus(HttpStatus.OK.toString());
			return ResponseEntity.ok(responseDto);
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}

	}

	public ResponseEntity<?> getTotalProjectBasedDatee(String fromDate, String toDate) {
		Map<String, Object> responseMap = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();

		if (fromDate != null && toDate != null) {
//	            List<OverTimeAnalysisBasedProject> list = overtimeAnalysisRepository.findAllProjectBasedDatee(fromDate, toDate);
			List<String> projectList = overtimeAnalysisRepository.findAllProjectBasedDate(fromDate, toDate);
			List<String> daysList = overtimeAnalysisRepository.findAllDaysBasedDate(fromDate, toDate);
			List<Float> overtimeList = overtimeAnalysisRepository.findAllOverTimesBasedDate(fromDate, toDate);

			Map<String, Map<String, Float>> projectOvertimePercentages = new HashMap<>();

			for (String project : projectList) {
				if (project != null && !project.isEmpty()) {
					float totalOvertimeHours = overtimeCalculation.calculateTotalOvertimeHoursByProject(overtimeList, project);
					Float percentWorkingDay = overtimeCalculation.calculatePercentageByDayTypeForProject(daysList, "Working Day",
							totalOvertimeHours, overtimeList, project);
					Float percentPublicHoliday = overtimeCalculation.calculatePercentageByDayTypeForProject(daysList, "Public Holiday",
							totalOvertimeHours, overtimeList, project);
					Float percentWeekoff = overtimeCalculation.calculatePercentageByDayTypeForProject(daysList, "Weekoff",
							totalOvertimeHours, overtimeList, project);

					Float totalPercentage = percentWorkingDay + percentPublicHoliday + percentWeekoff;

					Map<String, Float> percentages = new HashMap<>();
					percentages.put("percentWorkingDay", percentWorkingDay);
					percentages.put("percentPublicHoliday", percentPublicHoliday);
					percentages.put("percentWeekoff", percentWeekoff);
					percentages.put("totalPercentage", totalPercentage);

					projectOvertimePercentages.put(project, percentages);
				}
			}

			responseMap.put("Projects", projectOvertimePercentages);
			responseDto.setData(responseMap);
			responseDto.setStatus(HttpStatus.OK.toString());
			return ResponseEntity.ok(responseDto);
		} else {
			responseMap.put("Status", HttpStatus.BAD_REQUEST);
			responseMap.put("Error", "Undefined Data");
			return ResponseEntity.badRequest().body(responseMap);
		}
	}

	public ResponseEntity<?> getPercentageOfProjects(String fromDate, String toDate) {
		Map<String, Object> responseMap = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();

		if (fromDate != null && toDate != null) {
			List<String> projectList = overtimeAnalysisRepository.findAllProjectBasedDate(fromDate, toDate);
			List<Double> weeklist = new ArrayList<Double>();
			List<Double> workinglist = new ArrayList<Double>();
			List<Double> holidaylist = new ArrayList<Double>();
			for (int i = 0; i < projectList.size(); i++) {
				Double totalHours = overtimeAnalysisRepository.findCountOfDays(projectList.get(i), fromDate, toDate);
				Double totalweekofflist = overtimeAnalysisRepository.findOverTimeHoursByDay(projectList.get(i),
						fromDate, toDate, "Weekoff");
				Double totalworkdaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(projectList.get(i),
						fromDate, toDate, "Working Day");
				Double totalholidaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(projectList.get(i),
						fromDate, toDate, "Public Holiday");

				Double percentWeekoff = calculatePercentage(totalweekofflist, totalHours);
				Double percentWorkingDay = calculatePercentage(totalworkdaylist, totalHours);
				Double percentPublicHoliday = calculatePercentage(totalholidaylist, totalHours);

				weeklist.add(percentWeekoff);
				workinglist.add(percentWorkingDay);
				holidaylist.add(percentPublicHoliday);
			}
			responseMap.put("Projects", projectList);
			responseMap.put("percentWorkingDay", workinglist);
			responseMap.put("percentPublicHoliday", holidaylist);
			responseMap.put("percentWeekoff", weeklist);
			responseDto.setData(responseMap);
			responseDto.setStatus(HttpStatus.OK.toString());
			return ResponseEntity.ok(responseDto);
		} else {
			responseMap.put("Status", HttpStatus.BAD_REQUEST);
			responseMap.put("Error", "Undefined Data");
			return ResponseEntity.badRequest().body(responseMap);
		}
	}

	public static Double calculatePercentage(Double hours, Double totalHours) {
		if (hours == null || hours == 0) {
			return 0.00;
		}
		Double result = (hours / totalHours) * 100;
		return result;
	}

	public ResponseEntity<?> getPercentageOfEmployee(String fromDate, String toDate) {
		Map<String, Object> responseMap = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();

		if (fromDate != null && toDate != null) {
			List<String> employeeList = overtimeAnalysisRepository.findAllEmployeeBasedDate(fromDate, toDate);
			List<Double> weeklist = new ArrayList<Double>();
			List<Double> workinglist = new ArrayList<Double>();
			List<Double> holidaylist = new ArrayList<Double>();
			for (int i = 0; i < employeeList.size(); i++) {
				Double totalHours = overtimeAnalysisRepository.findCountOfDays(employeeList.get(i), fromDate, toDate);
				Double totalweekofflist = overtimeAnalysisRepository.findOverTimeHoursByDay(employeeList.get(i),
						fromDate, toDate, "Weekoff");
				Double totalworkdaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(employeeList.get(i),
						fromDate, toDate, "Working Day");
				Double totalholidaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(employeeList.get(i),
						fromDate, toDate, "Public Holiday");

				Double percentWeekoff = calculatePercentage(totalweekofflist, totalHours);
				Double percentWorkingDay = calculatePercentage(totalworkdaylist, totalHours);
				Double percentPublicHoliday = calculatePercentage(totalholidaylist, totalHours);

				weeklist.add(percentWeekoff);
				workinglist.add(percentWorkingDay);
				holidaylist.add(percentPublicHoliday);
			}
			responseMap.put("Employees", employeeList);
			responseMap.put("percentWorkingDay", workinglist);
			responseMap.put("percentPublicHoliday", holidaylist);
			responseMap.put("percentWeekoff", weeklist);
			responseDto.setData(responseMap);
			responseDto.setStatus(HttpStatus.OK.toString());
			return ResponseEntity.ok(responseDto);
		} else {
			responseMap.put("Status", HttpStatus.BAD_REQUEST);
			responseMap.put("Error", "Undefined Data");
			return ResponseEntity.badRequest().body(responseMap);
		}
	}

	public ResponseEntity<?> getPercentageOfPhase(String fromDate, String toDate) {
		Map<String, Object> responseMap = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();

		if (fromDate != null && toDate != null) {
			List<String> PhaseList = overtimeAnalysisRepository.findAllPhaseBasedDate(fromDate, toDate);
			List<Double> weeklist = new ArrayList<Double>();
			List<Double> workinglist = new ArrayList<Double>();
			List<Double> holidaylist = new ArrayList<Double>();
			for (int i = 0; i < PhaseList.size(); i++) {
				Double totalHours = overtimeAnalysisRepository.findCountOfDays(PhaseList.get(i), fromDate, toDate);
				Double totalweekofflist = overtimeAnalysisRepository.findOverTimeHoursByDay(PhaseList.get(i), fromDate,
						toDate, "Weekoff");
				Double totalworkdaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(PhaseList.get(i), fromDate,
						toDate, "Working Day");
				Double totalholidaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(PhaseList.get(i), fromDate,
						toDate, "Public Holiday");

				Double percentWeekoff = calculatePercentage(totalweekofflist, totalHours);
				Double percentWorkingDay = calculatePercentage(totalworkdaylist, totalHours);
				Double percentPublicHoliday = calculatePercentage(totalholidaylist, totalHours);

				weeklist.add(percentWeekoff);
				workinglist.add(percentWorkingDay);
				holidaylist.add(percentPublicHoliday);
			}
			responseMap.put("Phase", PhaseList);
			responseMap.put("percentWorkingDay", workinglist);
			responseMap.put("percentPublicHoliday", holidaylist);
			responseMap.put("percentWeekoff", weeklist);
			responseDto.setData(responseMap);
			responseDto.setStatus(HttpStatus.OK.toString());
			return ResponseEntity.ok(responseDto);
		} else {
			responseMap.put("Status", HttpStatus.BAD_REQUEST);
			responseMap.put("Error", "Undefined Data");
			return ResponseEntity.badRequest().body(responseMap);
		}
	}

	public ResponseEntity<?> getPercentageOfjobs(String fromDate, String toDate) {
		Map<String, Object> responseMap = new HashMap<>();
		ResponseDto responseDto = new ResponseDto();

		if (fromDate != null && toDate != null) {
			List<String> JobList = overtimeAnalysisRepository.findAllJobsBasedDate(fromDate, toDate);
			List<Double> weeklist = new ArrayList<Double>();
			List<Double> workinglist = new ArrayList<Double>();
			List<Double> holidaylist = new ArrayList<Double>();
			for (int i = 0; i < JobList.size(); i++) {
				Double totalHours = overtimeAnalysisRepository.findCountOfDays(JobList.get(i), fromDate, toDate);
				Double totalweekofflist = overtimeAnalysisRepository.findOverTimeHoursByDay(JobList.get(i), fromDate,
						toDate, "Weekoff");
				Double totalworkdaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(JobList.get(i), fromDate,
						toDate, "Working Day");
				Double totalholidaylist = overtimeAnalysisRepository.findOverTimeHoursByDay(JobList.get(i), fromDate,
						toDate, "Public Holiday");

				Double percentWeekoff = calculatePercentage(totalweekofflist, totalHours);
				Double percentWorkingDay = calculatePercentage(totalworkdaylist, totalHours);
				Double percentPublicHoliday = calculatePercentage(totalholidaylist, totalHours);

				weeklist.add(percentWeekoff);
				workinglist.add(percentWorkingDay);
				holidaylist.add(percentPublicHoliday);
			}
			responseMap.put("Jobs", JobList);
			responseMap.put("percentWorkingDay", workinglist);
			responseMap.put("percentPublicHoliday", holidaylist);
			responseMap.put("percentWeekoff", weeklist);
			responseDto.setData(responseMap);
			responseDto.setStatus(HttpStatus.OK.toString());
			return ResponseEntity.ok(responseDto);
		} else {
			responseMap.put("Status", HttpStatus.BAD_REQUEST);
			responseMap.put("Error", "Undefined Data");
			return ResponseEntity.badRequest().body(responseMap);
		}
	}

}
