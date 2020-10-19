package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.ServiceCodeing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class EmployeeController {

@Autowired
ServiceCodeing sc = new ServiceCodeing();
    //get employee
    @GetMapping("employees")
    public List<Employee> getAllEmployee() {
       // ServiceCodeing sc = new ServiceCodeing();
        return sc.getDetails();
    }


    //get employee by id
    @GetMapping("/employee/{id}")
    public Object getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        //ServiceCodeing sc = new ServiceCodeing();
        return sc.get(employeeId);
    }


    //save employee
    @PostMapping("employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        //ServiceCodeing sc = new ServiceCodeing();

        return sc.SaveEmployee(employee);
    }


    //update employee
    @PutMapping("/employee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                            @Validated @RequestBody Employee employeeDetails) {
        //ServiceCodeing sc = new ServiceCodeing();
        return sc.update(employeeId, employeeDetails);
    }


    //delete employee
    @DeleteMapping("employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
       // ServiceCodeing sc = new ServiceCodeing();
        return sc.delete(employeeId);

    }
}
