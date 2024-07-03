package com.app.employeedashboard.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class OverTimeAnalysisResponseDto {

	private int id;
	private String jobCode;
	private String jobName;
	private String phaseCode;
	private String phaseName;
	private String projectCode;
	private String projectName;
	private String estimatedHours;
	private String status;
	private int userId;
	private String userName;
	private String attendanceDate;
	private String jobHours;
	private Double jobCount;
	private String ot1;
	private String ot2;
	private Float clockedHours;
	private Float overtimeHours;
	private String departmentName;
	private String designation;
	private String designationName;
	private String organizationName;
	private String gradeName;
	private String sectionName;
	private String categoryName;
	private String branchName;
	private Float costPerHour;
	private Float costIncurred;
	private String projectId;
	private LocalDate maxEndDate;
	private String projectStatus;
	private String hasProjectStarted;
	private Float activeProject;
	private LocalDateTime createdDatetime;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private LocalDateTime processDate;
	private String fhshs;
	private String day;
	private String overtimePercent;


}
