package org.example.clase6gtics.controller;

import org.example.clase6gtics.entity.Departments;
import org.example.clase6gtics.entity.Employees;
import org.example.clase6gtics.entity.Jobs;
import org.example.clase6gtics.repository.DepartmentsRepository;
import org.example.clase6gtics.repository.EmployeesRepository;
import org.example.clase6gtics.repository.JobsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    final EmployeesRepository employeesRepository;
    final DepartmentsRepository departmentsRepository;
    final JobsRepository jobsRepository;

    public EmployeeController(EmployeesRepository employeesRepository, DepartmentsRepository departmentsRepository, JobsRepository jobsRepository) {
        this.employeesRepository = employeesRepository;
        this.departmentsRepository = departmentsRepository;
        this.jobsRepository = jobsRepository;
    }

    @GetMapping(value = {""})
    public String listarEmpleados(Model model){
        List<Employees> empleadosList = employeesRepository.listarEmpleados();
        model.addAttribute("listaEmployee", empleadosList);
        return "employee/lista";
    }

    @GetMapping(value = {"/newEmployee"})
    public String crearEmployee(Model model){
        List<Employees> managerList = employeesRepository.findAll();
        List<Departments> departmentsList = departmentsRepository.findAll();
        List<Jobs> jobsList = jobsRepository.findAll();
        model.addAttribute("departmentsList", departmentsList);
        model.addAttribute("managerList", managerList);
        model.addAttribute("jobs", jobsList);
        return "employee/new";
    }

    @PostMapping("/guardarEmployee")
    public String guardarNuevoEmployee(@RequestParam("firstName") String firstName,
                                            @RequestParam("lastName") String lastName,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password,
                                            @RequestParam("idcargo") String idcargo,
                                            @RequestParam("salary") Float salary,
                                            @RequestParam("idmanager") String idmanager,
                                            @RequestParam("iddepartment") String iddepartment){

        int idEmployee = employeesRepository.findLastEmployeeId() + 1;
        int managerId = Integer.parseInt(idmanager);
        int departmentId = Integer.parseInt(iddepartment);

       employeesRepository.crearEmployee(idEmployee,firstName,lastName, email, password,idcargo,salary, managerId, departmentId);

        return "redirect:/employees";
    }
}
