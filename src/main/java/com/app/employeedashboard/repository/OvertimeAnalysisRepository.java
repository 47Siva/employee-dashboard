package com.app.employeedashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.employeedashboard.entity.OvertimeAnalysis;

@Repository
public interface OvertimeAnalysisRepository extends JpaRepository<OvertimeAnalysis, Integer> {

}
