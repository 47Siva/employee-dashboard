package com.app.employeedashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class OvertimeOverviewDto {

	private double totalOvertimeHours;
    private int totalEmployeesWithOvertime;
    private double totalCostIncurred;
    private double totalOvertimePercentage;
}
