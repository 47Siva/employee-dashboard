package com.app.employeedashboard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;

public interface OverTimeAnalysisinList {

	public String getProjectName();

	public String getDay();

	public String getOverTimeHours();
}
