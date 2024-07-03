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
	@Column(name = "id",columnDefinition = "Integer")
	private Integer id;

	@Column(name = "job_code",columnDefinition = "text")
	private String jobCode;

	@Column(name = "job_name",columnDefinition = "text")
	private String jobName;

	@Column(name = "phase_code",columnDefinition = "text")
	private String phaseCode;

	@Column(name = "phase_name",columnDefinition = "text")
	private String phaseName;

	@Column(name = "project_code",columnDefinition = "text")
	private String projectCode;

	@Column(name = "project_name",columnDefinition = "text")
	private String projectName;

	@Column(name = "estimated_hours",columnDefinition = "text")
	private String estimatedHours;

	@Column(name = "status",columnDefinition = "text")
	private String status;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "user_name",columnDefinition = "text")
	private String userName;

	@Column(name = "attendance_date",columnDefinition = "text")
	private String attendanceDate;

	@Column(name = "job_hours",columnDefinition = "text")
	private String jobHours;

	@Column(name = "job_count")
	private Double jobCount;

	@Column(name = "ot1",columnDefinition = "text")
	private String ot1;

	@Column(name = "ot2",columnDefinition = "text")
	private String ot2;

	@Column(name = "clocked_hours")
	private Float clockedHours;

	@Column(name = "overtime_hours",columnDefinition = "text")
	private Float overtimeHours;

	@Column(name = "department_name",columnDefinition = "text")
	private String departmentName;

	@Column(name = "designation",columnDefinition = "text")
	private String designation;

	@Column(name = "designation_name",columnDefinition = "text")
	private String designationName;

	@Column(name = "organization_name",columnDefinition = "text")
	private String organizationName;

	@Column(name = "grade_name",columnDefinition = "text")
	private String gradeName;

	@Column(name = "section_name",columnDefinition = "text")
	private String sectionName;

	@Column(name = "category_name",columnDefinition = "text")
	private String categoryName;

	@Column(name = "branch_name",columnDefinition = "text")
	private String branchName;

	@Column(name = "cost_per_hour")
	private Float costPerHour;

	@Column(name = "cost_incurred")
	private Float costIncurred;

	@Column(name = "project_id",columnDefinition = "text")
	private String projectId;

	@Column(name = "max_end_date")
	private LocalDate maxEndDate;

	@Column(name = "project_status",columnDefinition = "text")
	private String projectStatus;

	@Column(name = "has_project_started",columnDefinition = "text")
	private String hasProjectStarted;

	@Column(name = "active_project")
	private Float activeProject;

	@Column(name = "created_datetime")
	private LocalDateTime createdDatetime;

	@Column(name = "start_date_time")
	private LocalDateTime startDateTime;

	@Column(name = "end_date_time")
	private LocalDateTime endDateTime;

	@Column(name = "process_date")
	private LocalDateTime processDate;

	@Column(name = "fhshs",columnDefinition = "text")
	private String fhshs;

	@Column(name = "day",columnDefinition = "text")
	private String day;

	@Column(name = "overtime_percent",columnDefinition = "text")
	private String overtimePercent;

}
