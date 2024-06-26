package com.app.employeedashboard.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.employeedashboard.dto.OvertimeAnalysisDto;
import com.app.employeedashboard.dto.OvertimeOverviewDto;
import com.app.employeedashboard.entity.OvertimeAnalysis;
import com.app.employeedashboard.repository.OvertimeAnalysisRepository;

@Service
//@RequiredArgsConstructor
public class OvertimeAnalysisService {

	@Autowired
	private OvertimeAnalysisRepository overtimeAnalysisRepository;

	public ResponseEntity<?> getemployee(int id) {
		Optional<OvertimeAnalysis> optional = overtimeAnalysisRepository.findById(id);
		return ResponseEntity.ok(optional);
	}

	public ResponseEntity<?> getallemployee() {
		List<OvertimeAnalysis> list = overtimeAnalysisRepository.findAll();
		return ResponseEntity.ok(list);
	}
	
	public ResponseEntity<?> getOverTimeHoursBasedDate(String fromDate, String toDate) {
		float overtimehowers =  overtimeAnalysisRepository.getOverTimeHoursBasedDate(fromDate,toDate);
		return ResponseEntity.ok(overtimehowers);
	}

	public ResponseEntity<?> getEmployeesWithOvertimeBasedDate(String fromDate, String toDate) {
        int employeesWithOvertime = overtimeAnalysisRepository.getEmployeesWithOvertimeBasedDate(fromDate, toDate);
        return ResponseEntity.ok(employeesWithOvertime);
    }
	
    public ResponseEntity<?> getTotalCostIncurred(String fromDate, String toDate) {
        float totalCost = overtimeAnalysisRepository.getTotalCostIncurred(fromDate, toDate);
        return ResponseEntity.ok(totalCost);
    }
	
    public ResponseEntity<?> getOvertimePercentage(String fromDate, String toDate) {
        float totalOvertimeHours = overtimeAnalysisRepository.getOverTimeHoursBasedDate(fromDate, toDate);
        float totalEstimatedHours = overtimeAnalysisRepository.getTotalEstimatedHours(fromDate, toDate);
        
        if (totalEstimatedHours == 0) {
            return ResponseEntity.ok(0); // To avoid division by zero
        }

        float overtimePercentage = (totalOvertimeHours / totalEstimatedHours) * 100;
        return ResponseEntity.ok(overtimePercentage);
    }
    
//	public long calculateTotalOvertimeHours(OvertimeAnalysisDto dto) {
//		return overtimeAnalysisRepository.calculateTotalOvertimeHours(dto.getFromDate(), dto.getToDate(),
//				dto.getOrganizationName(), dto.getBranchName(), dto.getDepartmentName(), dto.getCategoryName(),
//				dto.getDesignationName(), dto.getGradeName(), dto.getSectionName(), dto.getProjectName(),
//				dto.getPhaseName(), dto.getJobName(), dto.getEmployeeName());
//	}
	
}
