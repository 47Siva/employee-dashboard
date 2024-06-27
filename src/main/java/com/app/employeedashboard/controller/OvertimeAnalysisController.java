package com.app.employeedashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.employeedashboard.dto.OvertimeAnalysisDto;
import com.app.employeedashboard.dto.OvertimeOverviewDto;
import com.app.employeedashboard.entity.OvertimeAnalysis;
import com.app.employeedashboard.service.OvertimeAnalysisService;

@RestController
//@RequiredArgsConstructor
@RequestMapping("api/overtimeanalysis")
public class OvertimeAnalysisController {

	@Autowired
	private OvertimeAnalysisService overtimeAnalysisService;

	@GetMapping("/get/{id}")
	public ResponseEntity<?> getemployee(@PathVariable int id) {
		return overtimeAnalysisService.getEmployee(id);
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getallemployee() {
		return overtimeAnalysisService.getallEmployee();
	}

	@GetMapping("/overtime-howrs")
	public ResponseEntity<?> getOverTimeHoursBasedDate(@RequestParam(required = false) String fromDate, @RequestParam (required = false) String toDate) {
		return overtimeAnalysisService.getOverTimeHoursBasedDate(fromDate, toDate);
	}

	@GetMapping("/employees-with-overtime")
	public ResponseEntity<?> getEmployeesWithOvertimeBasedDate(@RequestParam (required = false) String fromDate,
			@RequestParam (required = false) String toDate) {
		return overtimeAnalysisService.getEmployeesWithOvertimeBasedDate(fromDate, toDate);
	}
	
    @GetMapping("/total-cost-incurred")
    public ResponseEntity<?> getTotalCostIncurred(@RequestParam (required = false) String fromDate, @RequestParam (required = false) String toDate) {
        return overtimeAnalysisService.getTotalCostIncurred(fromDate, toDate);
    }

    @GetMapping("/overtime-percentage")
    public ResponseEntity<?> getOvertimePercentage(@RequestParam (required = false) String fromDate, @RequestParam (required = false) String toDate) {
        return overtimeAnalysisService.getOvertimePercentage(fromDate, toDate);
    }
    
    @GetMapping("/Overtimer-hours-overview")
    public ResponseEntity<?> getTotalOvertimeHours(@RequestParam (required = false) String fromDate, @RequestParam  (required = false) String toDate) {
        return overtimeAnalysisService.OvertimeOverviewMetrics(fromDate,toDate);
    }
}
