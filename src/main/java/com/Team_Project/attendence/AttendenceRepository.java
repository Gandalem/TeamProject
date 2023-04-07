package com.Team_Project.attendence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Team_Project.entity.Commute;

public interface AttendenceRepository extends JpaRepository<Commute, Long>{

	List<Commute> findAllBy();
	
    @Query("SELECT a FROM Commute a WHERE a.company.id = :companyId AND a.department.id = :departmentId AND a.employee.id = :employeeIdx AND YEAR(a.today) = :year AND MONTH(a.today) = :month")
    List<Commute> findByCompanyIdAndDepartmentIdAndEmployeeIdxAndYearAndMonth(
            @Param("companyId") Long companyId,
            @Param("departmentId") Long departmentId,
            @Param("employeeIdx") Long employeeIdx,
            @Param("year") int year,
            @Param("month") int month);
    
}
