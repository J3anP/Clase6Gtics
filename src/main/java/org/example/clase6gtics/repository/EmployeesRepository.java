package org.example.clase6gtics.repository;

import jakarta.transaction.Transactional;
import org.example.clase6gtics.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepository extends JpaRepository<Employees,Integer> {
    @Query(nativeQuery = true, value ="SELECT MAX(e.employee_id) FROM hr.employees e")
    Integer findLastEmployeeId();

    @Query(nativeQuery = true, value = "SELECT e.employee_id, e.first_name, e.last_name, e.email, e.password, e.phone_number, e.hire_date, e.salary, e.enabled, \n" +
            "d.department_id, d.department_name, d.manager_id, j.job_id, j.job_title, j.min_salary, j.min_salary ,l.* \n" +
            "FROM hr.employees e INNER JOIN hr.jobs j, hr.departments d, hr.locations l \n" +
            "where e.job_id = j.job_id and d.department_id = e.department_id and d.location_id = l.location_id;")
    List<Employees> listarEmpleados();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO hr.employees(employee_id, first_name, last_name, email, password, job_id, salary, manager_id, department_id)\n" +
            "VALUES (?1, ?2, ?3, ?4,?5, ?6, ?7, ?8, ?9)")
    void crearEmployee(int id, String name, String lastname, String email, String password, String jobid, Float salary,int managerid, int departmentid);
}
