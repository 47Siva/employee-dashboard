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
    private long totalEmployeesWithOvertime;
    private double totalCastincurred;
    private double totalOvertimepercentage;
}
