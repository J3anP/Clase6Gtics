package org.example.clase6gtics.controller;

import jakarta.validation.Valid;
import org.example.clase6gtics.entity.Departments;
import org.example.clase6gtics.entity.Employees;
import org.example.clase6gtics.entity.Jobs;
import org.example.clase6gtics.repository.DepartmentsRepository;
import org.example.clase6gtics.repository.EmployeesRepository;
import org.example.clase6gtics.repository.JobsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
    public String crearEmployee(@ModelAttribute("employee") Employees employee, Model model){
        List<Employees> managerList = employeesRepository.findAll();
        List<Departments> departmentsList = departmentsRepository.findAll();
        List<Jobs> jobsList = jobsRepository.findAll();
        model.addAttribute("departmentsList", departmentsList);
        model.addAttribute("managerList", managerList);
        model.addAttribute("jobs", jobsList);
        return "employee/new";
    }

    @GetMapping(value = {"/edit"})
    public String editarEmployee(@ModelAttribute("employee") Employees employee, Model model, @RequestParam("id") int id){
        Optional<Employees> optEmployee = employeesRepository.findById(id);

        if(optEmployee.isPresent()){
            List<Employees> managerList = employeesRepository.findAll();
            List<Departments> departmentsList = departmentsRepository.findAll();
            List<Jobs> jobsList = jobsRepository.findAll();
            employee = optEmployee.get();
            model.addAttribute("employee",employee);
            model.addAttribute("departmentsList", departmentsList);
            model.addAttribute("managerList", managerList);
            model.addAttribute("jobs", jobsList);
            return "employee/new";
        }else{
            return "redirect:/employees";
        }

    }

    @PostMapping("/guardarEmployee")
    public String guardarNuevoEmployee(@ModelAttribute("employee") @Valid Employees employee, BindingResult bindingResult, RedirectAttributes attr, Model model){
        if(bindingResult.hasErrors()){
            List<Departments> departmentsList = departmentsRepository.findAll();
            List<Employees> managerList = employeesRepository.findAll();
            List<Jobs> jobsList = jobsRepository.findAll();
            model.addAttribute("departmentsList", departmentsList);
            model.addAttribute("managerList", managerList);
            model.addAttribute("jobs", jobsList);
            return "employee/new";
        }else{
            employee.setHireDate(Date.from(Instant.now()));
            employeesRepository.save(employee);
            return "redirect:/employees";
        }
    }

    /*@PostMapping("/guardarEmployee")
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
    }*/
}
