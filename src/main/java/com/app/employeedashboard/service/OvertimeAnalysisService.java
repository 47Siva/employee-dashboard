package com.app.employeedashboard.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.employeedashboard.dto.OvertimeAnalysisDto;
import com.app.employeedashboard.dto.OvertimeOverviewDto;
import com.app.employeedashboard.dto.ResponseDto;
import com.app.employeedashboard.entity.OvertimeAnalysis;
import com.app.employeedashboard.repository.OvertimeAnalysisRepository;

@Service
//@RequiredArgsConstructor
public class OvertimeAnalysisService {

	@Autowired
	private OvertimeAnalysisRepository overtimeAnalysisRepository;

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
		if (fromDate != null && toDate != null) {
			Double overtimehowers = overtimeAnalysisRepository.getOverTimeHoursBasedDate(fromDate, toDate);
			map.put("OverTimeHowers", overtimehowers);
			ResponseDto responseDto = new ResponseDto();
			if (overtimehowers != 0) {
				responseDto.setData(map);
				responseDto.setStatus(HttpStatus.OK.toString());
			}
			return ResponseEntity.ok(responseDto);
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
			}
			return ResponseEntity.ok(responseDto);
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
			}
			return ResponseEntity.ok(responseDto);
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
			}
			return ResponseEntity.ok(responseDto);
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
			}
			return ResponseEntity.ok(responseDto);
		} else {
			map.put("Status", HttpStatus.BAD_REQUEST);
			map.put("Error", "Undefended Data");
			return ResponseEntity.badRequest().body(map);
		}
	}

}
