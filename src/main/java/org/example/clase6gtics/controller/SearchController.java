package org.example.clase6gtics.controller;

import org.example.clase6gtics.entity.Employees;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/Search")
public class SearchController {

    @GetMapping(value = {""})
    public String listarJobHistory(Model model){
        List<Employees> empleadosList = historyRepository.listarEmpleados();
        model.addAttribute("listaEmployee", empleadosList);
        return "employee/lista";
    }
}
