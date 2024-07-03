package com.app.employeedashboard.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OvertimeAnalysisRequest {

	  private String fromDate ;
	  private String toDate;
	  private String organizationName;
	  private String branchName;
	  private String departmentName;
	  private String categoryName;
	  private String designationName;
	  private String gradeName;
	  private String sectionName;
	  private String projectName;
	  private String phaseName;
	  private String jobName;
	  private String employeeName;
}
