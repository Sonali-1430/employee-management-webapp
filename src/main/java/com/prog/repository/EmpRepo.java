package com.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.prog.entity.Employee;

@Repository
public interface EmpRepo  extends JpaRepository<Employee, Integer>{

	/*
	 * @Query("update EMP_MANAGEMENT emp set emp.salary=:salary where emp.id=:id")
	 * int updateAndGetSalary(int salary,int id);
	 */
}
