package com.example.wokshop.controller;

import com.example.wokshop.service.CompanyService;
import com.example.wokshop.service.EmployeeService;
import com.example.wokshop.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class ImportController {

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/import")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("xml/import-xml");
        boolean[] areImportd = new boolean[3];
        areImportd[0] = companyService.areImported();
        areImportd[1] = projectService.areImported();
        areImportd[2] = employeeService.areImported();

        modelAndView.addObject("areImported", areImportd);
        return  modelAndView;
    }

    @GetMapping("import/companies")
    public ModelAndView importCompanies() throws IOException {
        ModelAndView modelAndView = new ModelAndView("xml/import-companies");

        String companies = this.companyService.getCompaniesText();
        modelAndView.addObject("companies", companies);

        return modelAndView;
    }
}
