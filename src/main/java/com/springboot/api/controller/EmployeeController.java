package com.springboot.api.controller;

import com.springboot.api.model.Employee;
import com.springboot.api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Create - Add a new employee
     * @param employee An object employee
     * @return The employee object saved
     */
    @Operation(summary = "Add a new employee")
    @ApiResponse(responseCode = "201", description = "Employee created")
    @PostMapping("/add/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return  employeeService.saveEmployee(employee);
    }

    /**
     * Read - Get all employees
     * @return - An Iterable object of Employees fulfilled
     */
    @Operation(summary = "Get all the employees")
    @ApiResponse(responseCode = "200", description = "Employees returned")
    @GetMapping("/employees")
    public Iterable<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    /**
     * Read - Get one employee
     * @param id The id of the employee
     * @return An employee object fulfilled
     */
    @Operation(summary = "Get an employee")
    @ApiResponse(responseCode = "200", description = "Employee returned")
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") final Long id) {
        Optional<Employee> employee = employeeService.getEmployee(id);
        return employee.orElse(null); // If employee is found it returns it, or null is returned
    }

    /**
     * Delete - Delete an employee
     * @param id - The id of the employee to delete
     */
    @Operation(summary = "Remove an employee")
    @ApiResponse(responseCode = "200", description = "Employee removed")
    @DeleteMapping("remove/employee/{id}")
    public void deleteEmployee(@PathVariable("id") final Long id) {
        employeeService.deleteEmployee(id);
    }

    @Operation(summary = "Update an employee")
    @ApiResponse(responseCode = "200", description = "Employee updated")
    @PutMapping("update/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") final Long id, @RequestBody Employee employee) {
        Optional<Employee> e = employeeService.getEmployee(id);
        if(e.isPresent()) {
            Employee currentEmployee = e.get();

            String firstName = employee.getFirstName();
            if (firstName != null) {
                currentEmployee.setFirstName(firstName);
            }
            String lastName = employee.getLast_name();
            if (lastName != null) {
                currentEmployee.setLast_name(lastName);
            }
            String mail = employee.getMail();
            if (mail != null) {
                currentEmployee.setMail(mail);
            }
            String password = employee.getPassword();
            if (password != null) {
                currentEmployee.setPassword(password);
            }

            return currentEmployee;
        } else {
            return null;
        }
    }
}
