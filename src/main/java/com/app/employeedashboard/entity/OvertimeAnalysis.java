package com.app.employeedashboard.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "overtime_analysis")
public class OvertimeAnalysis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "\"Job Code\"", columnDefinition = "text")
	private String jobCode;

	@Column(name = "\"Job Name\"", columnDefinition = "text")
	private String jobName;

	@Column(name = "\"Phase Code\"", columnDefinition = "text")
	private String phaseCode;

	@Column(name = "\"Phase Name\"", columnDefinition = "text")
	private String phaseName;

	@Column(name = "\"Project Code\"", columnDefinition = "text")
	private String projectCode;

	@Column(name = "\"Project Name\"", columnDefinition = "text")
	private String projectName;

	@Column(name = "\"Estimated-Hours\"", columnDefinition = "text")
	private String estimatedHours;

	@Column(name = "\"Status\"", columnDefinition = "text")
	private String status;

	@Column(name = "\"User ID\"")
	private Integer userId;

	@Column(name = "\"User Name\"", columnDefinition = "text")
	private String userName;

	@Column(name = "\"attendance-date\"", columnDefinition = "text")
	private String attendanceDate;

	@Column(name = "\"Job Hours\"", columnDefinition = "text")
	private String jobHours;

	@Column(name = "\"Job Count\"")
	private Double jobCount;

	@Column(name = "\"ot1\"", columnDefinition = "text")
	private String ot1;

	@Column(name = "\"ot2\"", columnDefinition = "text")
	private String ot2;

	@Column(name = "\"Clocked Hours\"")
	private Float clockedHours;

	@Column(name = "\"Overtime_Hours\"")
	private Float overtimeHours;

	@Column(name = "\"department-name\"", columnDefinition = "text")
	private String departmentName;

	@Column(name = "\"designation\"", columnDefinition = "text")
	private String designation;

	@Column(name = "\"designation-name\"", columnDefinition = "text")
	private String designationName;

	@Column(name = "\"organization-name\"", columnDefinition = "text")
	private String organizationName;

	@Column(name = "\"grade-name\"", columnDefinition = "text")
	private String gradeName;

	@Column(name = "\"section-name\"", columnDefinition = "text")
	private String sectionName;

	@Column(name = "\"category-name\"", columnDefinition = "text")
	private String categoryName;

	@Column(name = "\"branch-name\"", columnDefinition = "text")
	private String branchName;

	@Column(name = "\"Cost Per Hour\"")
	private Float costPerHour;

	@Column(name = "\"Cost Incurred\"")
	private Float costIncurred;

	@Column(name = "\"ProjectID\"", columnDefinition = "text")
	private String projectId;

	@Column(name = "\"max-end-date\"")
	private LocalDate maxEndDate;

	@Column(name = "\"Project Status\"", columnDefinition = "text")
	private String projectStatus;

	@Column(name = "\"HasProjectStarted\"", columnDefinition = "text")
	private String hasProjectStarted;

	@Column(name = "\"Active Project\"")
	private Float activeProject;

	@Column(name = "\"Created Datetime\"")
	private LocalDateTime createdDatetime;

	@Column(name = "\"Start Date Time\"")
	private LocalDateTime startDateTime;

	@Column(name = "\"End Date Time\"")
	private LocalDateTime endDateTime;

	@Column(name = "\"ProcessDate\"")
	private LocalDateTime processDate;

	@Column(name = "\"FHSHS\"", columnDefinition = "text")
	private String fhshs;

	@Column(name = "\"Day\"", columnDefinition = "text")
	private String day;

	@Column(name = "\"Overtime-Percent\"", columnDefinition = "text")
	private String overtimePercent;

}
