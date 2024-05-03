package org.example.clase6gtics.repository;

import org.example.clase6gtics.entity.Departments;
import org.example.clase6gtics.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM hr.departments WHERE manager_id is not null;")
    List<Departments> listarManagers();


}
