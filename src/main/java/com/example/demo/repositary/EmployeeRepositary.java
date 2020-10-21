package com.example.demo.repositary;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepositary extends JpaRepository<Employee, Long> {

   Employee findByEmail(String Email);


}
