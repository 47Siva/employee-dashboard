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
		return overtimeAnalysisService.getemployee(id);
	}

	@GetMapping("/getall")
	public ResponseEntity<?> getallemployee() {
		return overtimeAnalysisService.getallemployee();
	}

	@GetMapping("/overtime-howrs")
	public ResponseEntity<?> getOverTimeHoursBasedDate(@RequestParam String fromDate, @RequestParam String toDate) {
		return overtimeAnalysisService.getOverTimeHoursBasedDate(fromDate, toDate);
	}

	@GetMapping("/employees-with-overtime")
	public ResponseEntity<?> getEmployeesWithOvertimeBasedDate(@RequestParam String fromDate,
			@RequestParam String toDate) {
		return overtimeAnalysisService.getEmployeesWithOvertimeBasedDate(fromDate, toDate);
	}
	
    @GetMapping("/total-cost-incurred")
    public ResponseEntity<?> getTotalCostIncurred(@RequestParam String fromDate, @RequestParam String toDate) {
        return overtimeAnalysisService.getTotalCostIncurred(fromDate, toDate);
    }

    @GetMapping("/overtime-percentage")
    public ResponseEntity<?> getOvertimePercentage(@RequestParam String fromDate, @RequestParam String toDate) {
        return overtimeAnalysisService.getOvertimePercentage(fromDate, toDate);
    }
    
//    @PostMapping("/total-hours")
//    public ResponseEntity<Long> getTotalOvertimeHours(@RequestBody OvertimeAnalysisDto dto) {
//        long totalOvertimeHours = overtimeAnalysisService.calculateTotalOvertimeHours(dto);
//        return ResponseEntity.ok(totalOvertimeHours);
//    }
}
