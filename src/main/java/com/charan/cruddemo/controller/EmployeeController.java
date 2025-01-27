package com.charan.cruddemo.controller;

import com.charan.cruddemo.entity.Employee;
import com.charan.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;
    //quick and dirty: inject employee dao(use constructor injection)
    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    //expose "/employees" and return the list of employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @PostMapping("/employees")
    public Employee saveEmployees(@RequestBody Employee theEmployee){
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    @GetMapping("/employees/{theId}")
    public Employee getEmployeeById(@PathVariable int theId){

        Employee theEmployee = employeeService.findById(theId);
        if (theEmployee == null){
            throw new RuntimeException("Employee id not found" + theId);
        }
        return theEmployee;
    }

    @PutMapping("/employees")
    public Employee updateEmployeeById(@RequestBody Employee theEmployee){
        return employeeService.save(theEmployee);
    }

    @DeleteMapping("/employees/{theId}")
    public void deleteEmployee(@PathVariable int theId){
        System.out.println(theId);
        employeeService.deleteById(theId);
    }


}
