package com.app.employeedashboard.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.employeedashboard.dto.OvertimeOverviewDto;
import com.app.employeedashboard.entity.OvertimeAnalysis;

@Repository
public interface OvertimeAnalysisRepository extends JpaRepository<OvertimeAnalysis, Integer> {
	
	@Query("SELECT SUM(oa.overtimeHours) FROM OvertimeAnalysis oa WHERE oa.attendanceDate BETWEEN :fromDate AND :toDate")
    float getOverTimeHoursBasedDate(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate
    );
	
    @Query("SELECT COUNT(DISTINCT oa.userId) FROM OvertimeAnalysis oa WHERE oa.attendanceDate BETWEEN :fromDate AND :toDate")
    int getEmployeesWithOvertimeBasedDate(
            @Param("fromDate") String fromDate,
            @Param("toDate") String toDate
    );
    
    @Query("SELECT SUM(oa.overtimeHours * 15) FROM OvertimeAnalysis oa WHERE oa.attendanceDate BETWEEN :fromDate AND :toDate")
    float getTotalCostIncurred(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    
    @Query("SELECT SUM(oa.estimatedHours) FROM OvertimeAnalysis oa WHERE oa.attendanceDate BETWEEN :fromDate AND :toDate")
    float getTotalEstimatedHours(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
    
//	 @Query("SELECT SUM(eo.overtimeHours) FROM EmployeeOvertime eo WHERE " +
//	            "eo.date BETWEEN :fromDate AND :toDate AND " +
//	            "eo.organizationName = :organizationName AND " +
//	            "eo.branchName = :branchName AND " +
//	            "eo.departmentName = :departmentName AND " +
//	            "eo.categoryName = :categoryName AND " +
//	            "eo.designationName = :designationName AND " +
//	            "eo.gradeName = :gradeName AND " +
//	            "eo.sectionName = :sectionName AND " +
//	            "eo.projectName = :projectName AND " +
//	            "eo.phaseName = :phaseName AND " +
//	            "eo.jobName = :jobName AND " +
//	            "eo.employeeName = :employeeName")
//	    long calculateTotalOvertimeHours(String fromDate, String toDate, String organizationName, 
//	                                     String branchName, String departmentName, String categoryName, 
//	                                     String designationName, String gradeName, String sectionName, 
//	                                     String projectName, String phaseName, String jobName, 
//	                                     String employeeName);



}
