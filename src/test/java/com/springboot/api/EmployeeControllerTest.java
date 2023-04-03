package com.springboot.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.api.model.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Enable ordering of test methods
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    public void testAddEmployee() throws Exception {
        // Create a new employee object to add
        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLast_name("Doe");
        employee.setMail("john.doe@example.com");
        employee.setPassword("password");

        // Convert the employee object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post("/add/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.last_name").value("Doe"))
                .andExpect(jsonPath("$.mail").value("john.doe@example.com"))
                .andExpect(jsonPath("$.password").value("password"));
    }

    @Order(2)
    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Order(3)
    @Test
    public void testGetEmployee() throws Exception {
        mockMvc.perform(get("/employee/1")).andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("John"));
    }

    @Order(4)
    @Test
    public void testUpdateEmployee() throws Exception {
        // Create the employee object to update
        Employee employee = new Employee();
        employee.setFirstName("newName");
        employee.setLast_name("Doe");
        employee.setMail("john.doe@example.com");
        employee.setPassword("password");

        // Convert the employee object to JSON string
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(employee);

        // Update the employee in the database
        mockMvc.perform(put("/update/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("newName"));
    }

    @Order(5)
    @Test
    public void testDeleteEmployee() throws Exception {
        // Delete the employee
        mockMvc.perform(delete("/remove/employee/1"))
                .andExpect(status().isOk());

        // Check that the employee has been deleted
        mockMvc.perform(get("/employee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}
