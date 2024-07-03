package com.app.employeedashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.employeedashboard.dto.OverTimeAnalysisinList;
import com.app.employeedashboard.entity.OvertimeAnalysis;

@Repository
public interface OvertimeAnalysisRepository extends JpaRepository<OvertimeAnalysis, Integer> {

//	@Query("SELECT SUM(oa.overtimeHours) FROM OvertimeAnalysis oa WHERE oa.attendanceDate BETWEEN :fromDate AND :toDate")
	@Query("SELECT SUM(oa.overtimeHours) " + "FROM OvertimeAnalysis oa "
			+ "WHERE TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')")
	Double getOverTimeHoursBasedDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT COUNT(DISTINCT oa.userId) FROM OvertimeAnalysis oa WHERE oa.overtimeHours>0 AND TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')")
	int getEmployeesWithOvertimeBasedDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT SUM(oa.overtimeHours * 15) FROM OvertimeAnalysis oa WHERE TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')")
	Double getTotalCostIncurred(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

//	@Query(value = "SELECT SUM(CASE WHEN oa.estimated_hours = '' THEN 0.0 ELSE EXTRACT(EPOCH FROM CAST(estimated_hours AS interval)) / 3600.0 END) FROM overtime_analysis as oa WHERE TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')", nativeQuery = true)
	@Query(value = "SELECT SUM(CASE WHEN oa.estimated_hours IS NULL OR oa.estimated_hours = '' THEN 0.0 ELSE EXTRACT(EPOCH FROM CAST(oa.estimated_hours AS interval)) / 3600.0 END) FROM overtime_analysis as oa WHERE TO_DATE(oa.attendance_date, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')", nativeQuery = true)
	Double getTotalEstimatedHours(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "SELECT * " + "FROM overtime_analysis oa "
			+ "WHERE TO_DATE(oa.attendance_date, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')", nativeQuery = true)
	List<OvertimeAnalysis> findByMaxEndDateBetween(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

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

	@Query(value = "SELECT Distinct project_name as ProjectName , day as Day ,overtime_hours as OverTimeHours FROM overtime_analysis oa  WHERE oa.overtime_hours>0 AND TO_DATE(oa.attendance_date, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY') AND oa.project_name IS NOT NULL AND oa.project_name != ''", nativeQuery = true)
	List<OverTimeAnalysisinList> findAllProjectsBasedDate(String fromDate, String toDate);

	@Query(value = "SELECT Distinct project_name FROM overtime_analysis oa  WHERE oa.overtime_hours>0 AND TO_DATE(oa.attendance_date, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY') AND oa.project_name IS NOT NULL AND oa.project_name != ''", nativeQuery = true)
	List<String> findAllProjectBasedDate(String fromDate, String toDate);

	@Query(value = "SELECT  day FROM overtime_analysis oa  WHERE oa.overtime_hours>0 AND TO_DATE(oa.attendance_date, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')", nativeQuery = true)
	List<String> findAllDaysBasedDate(String fromDate, String toDate);

	@Query(value = "SELECT overtime_hours FROM overtime_analysis oa  WHERE oa.overtime_hours>0 AND TO_DATE(oa.attendance_date, 'DD/MM/YYYY') BETWEEN TO_DATE(:fromDate, 'DD/MM/YYYY') AND TO_DATE(:toDate, 'DD/MM/YYYY')", nativeQuery = true)
	List<Float> findAllOverTimesBasedDate(String fromDate, String toDate);

	@Query(value = "select sum(oa.overtime_hours) from overtime_analysis oa where oa.overtime_hours > 0 and TO_DATE(oa.attendance_date, 'dd/mm/YYYY') BETWEEN TO_DATE(:fromDate, 'dd/mm/YYYY') AND TO_DATE(:toDate, 'dd/mm/YYYY') and (oa.project_name=:value or oa.job_name=:value or oa.phase_name=:value or oa.user_name=:value)",nativeQuery = true)
	Double findCountOfDays(String value, String fromDate, String toDate);
	
	@Query(value = " select sum(oa.overtime_hours) from overtime_analysis oa where (oa.project_name=:value or oa.job_name=:value or oa.phase_name=:value or oa.user_name=:value) and oa.day=:dayType and oa.overtime_hours > 0 and TO_DATE(oa.attendance_date, 'dd/mm/YYYY') BETWEEN TO_DATE(:fromDate, 'dd/mm/YYYY') AND TO_DATE(:toDate, 'dd/mm/YYYY') ", nativeQuery = true)
	Double findOverTimeHoursByDay(String value, String fromDate, String toDate,String dayType);

	@Query("SELECT Distinct userName FROM OvertimeAnalysis oa  WHERE oa.overtimeHours>0 AND TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE('10/04/2024', 'DD/MM/YYYY') AND TO_DATE('20/04/2024', 'DD/MM/YYYY')")
	List<String> findAllEmployeeBasedDate(String fromDate, String toDate);

	@Query("SELECT Distinct oa.phaseName FROM OvertimeAnalysis oa  WHERE oa.overtimeHours>0 AND TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE('10/04/2024', 'DD/MM/YYYY') AND TO_DATE('20/04/2024', 'DD/MM/YYYY')  AND oa.phaseName IS NOT NULL AND oa.phaseName != ''")
	List<String> findAllPhaseBasedDate(String fromDate, String toDate);

	@Query("SELECT Distinct jobName FROM OvertimeAnalysis oa  WHERE oa.overtimeHours>0 AND TO_DATE(oa.attendanceDate, 'DD/MM/YYYY') BETWEEN TO_DATE('10/04/2024', 'DD/MM/YYYY') AND TO_DATE('20/04/2024', 'DD/MM/YYYY')  AND oa.jobName IS NOT NULL AND oa.jobName != ''")
	List<String> findAllJobsBasedDate(String fromDate, String toDate);
}
