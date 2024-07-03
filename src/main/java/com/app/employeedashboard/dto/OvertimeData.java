package com.app.employeedashboard.dto;

import lombok.Data;

@Data
public class OvertimeData {

    private float workingDayPercentage;
    private float holidayPercentage;
    private float weekOffPercentage;

    public OvertimeData(float workingDayPercentage, float holidayPercentage, float weekOffPercentage) {
        this.workingDayPercentage = workingDayPercentage;
        this.holidayPercentage = holidayPercentage;
        this.weekOffPercentage = weekOffPercentage;
    }
    
}
