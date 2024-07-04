package com.app.employeedashboard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.employeedashboard.dto.OvertimeAnalysisRequest;
import com.app.employeedashboard.service.OvertimeAnalysisService;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/overtimeanalysis")
public class OvertimeAnalysisController {

	@Autowired
	private OvertimeAnalysisService overtimeAnalysisService;

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getEmployee(@PathVariable int id) {
		return overtimeAnalysisService.getEmployee(id);
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getAllEmployee() {
		return overtimeAnalysisService.getallEmployee();
	}

	@GetMapping("/overtime-howrs")
	public ResponseEntity<?> getOverTimeHoursBasedDate(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getOverTimeHoursBasedDate(fromDate, toDate);
	}

	@GetMapping("/employees-with-overtime")
	public ResponseEntity<?> getEmployeesWithOvertimeBasedDate(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getEmployeesWithOvertimeBasedDate(fromDate, toDate);
	}

	@GetMapping("/total-cost-incurred")
	public ResponseEntity<?> getTotalCostIncurred(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getTotalCostIncurred(fromDate, toDate);
	}

	@GetMapping("/overtime-percentage")
	public ResponseEntity<?> getOvertimePercentage(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getOvertimePercentage(fromDate, toDate);
	}

	@GetMapping("/Overtimer-hours-overview")
	public ResponseEntity<?> getTotalOvertimeHours(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.OvertimeOverviewMetrics(fromDate, toDate);
	}
	
	@GetMapping("/Overtimer-hours-overview-filter")
	public ResponseEntity<?> getOvertimeOverview(@RequestBody(required = false) OvertimeAnalysisRequest request) {
		return overtimeAnalysisService.OvertimeOverviewMetricsFilter(request);
	}

	@GetMapping("/project")
	public ResponseEntity<?> getTotalProjectBasedDate(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getTotalProjectBasedDate(fromDate, toDate);
	}

	@GetMapping("/overtime-Alldays")
	public ResponseEntity<?> getTotalProjectBasedDatee(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getTotalProjectBasedDatee(fromDate, toDate);
	}

	@GetMapping("/get/project/percentages")
	public ResponseEntity<?> getDayPercentageForProject(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getPercentageOfProjects(fromDate, toDate);
	}

	@GetMapping("/get/employee/percentages")
	public ResponseEntity<?> getDayPercentageForEmployee(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getPercentageOfEmployee(fromDate, toDate);
	}

	@GetMapping("/get/phase/percentages")
	public ResponseEntity<?> getDayPercentageForPhase(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getPercentageOfPhase(fromDate, toDate);
	}

	@GetMapping("/get/jobs/percentages")
	public ResponseEntity<?> getDayPercentageForjobs(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate) {
		return overtimeAnalysisService.getPercentageOfjobs(fromDate, toDate);
	}

	@GetMapping("/get/category/percentages")
	public ResponseEntity<?> getDayPercentageForCategory(@RequestParam(required = false) String fromDate,
			@RequestParam(required = false) String toDate, @RequestParam(required = false) String category) {
		Map<String, Object> responseMap = new HashMap<>();
		if (fromDate != null && toDate != null && category != null) {
			if (category.equals("Employee")) {
				ResponseEntity<?> response = overtimeAnalysisService.getPercentageOfEmployee(fromDate, toDate);
				responseMap.put("Oveertime By Employee", response.getBody());
			} else if (category.equals("Jobs")) {
				ResponseEntity<?> response = overtimeAnalysisService.getPercentageOfjobs(fromDate, toDate);
				responseMap.put("Oveertime By Jobs", response.getBody());
			} else if (category.equals("Phase")) {
				ResponseEntity<?> response = overtimeAnalysisService.getPercentageOfPhase(fromDate, toDate);
				responseMap.put("Oveertime By Phase", response.getBody());
			} else if (category.equals("Project")) {
				ResponseEntity<?> response = overtimeAnalysisService.getPercentageOfProjects(fromDate, toDate);
				responseMap.put("Oveertime By Project", response.getBody());
			} else {
				responseMap.put("Status", HttpStatus.BAD_REQUEST);
				responseMap.put("Error", "Undefined Category");
			}
		}else {
			responseMap.put("Status", HttpStatus.BAD_REQUEST);
			responseMap.put("Error", "Undefined Data");
		}

		return ResponseEntity.ok(responseMap);
	}
}
