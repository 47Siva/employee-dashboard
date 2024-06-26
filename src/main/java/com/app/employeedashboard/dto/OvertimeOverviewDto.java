package com.app.employeedashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class OvertimeOverviewDto {

	private double totalOvertimeHours;
    private long totalEmployeesWithOvertime;
    
}
