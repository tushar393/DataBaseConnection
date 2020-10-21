package com.example.demo.service;

import com.example.demo.exception.ErrorMessage;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.ViewEntity;
import com.example.demo.repositary.EmployeeRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCodeing {
    @Autowired
    public EmployeeRepositary employeeRepositary;

    public List<Employee> getDetails() {
        return employeeRepositary.findAll();
    }

    public List<ViewEntity> get(Long employeeId) {
        int saveFlag = 0;
        ErrorMessage em = new ErrorMessage();

        List<Employee> list = employeeRepositary.findAll();
        List<ViewEntity> list1 = new ArrayList<>();
       // List<ViewEntity> list2 = new ArrayList<>();
        ViewEntity ve = new ViewEntity();

       Optional<Employee> employee;
        // Long c = employeeId;
        employee = employeeRepositary.findById(employeeId);
        //k=Integer.parseInt(employee);
        // if(employee==null){
        ////ve.setEmail(Employee.getId(employee).getEmail());
        //  ve.setEmail(Employee.employee.getEmail());
        //}
        //if(employee != null){
          //  ve.setFirst_name(employeeRepositary.getFirstName(employeeRepositary.findById(employee)));
        //}
        for (int i = 0; i < list.size(); i++) {
            if (employeeId.equals(list.get(i).getId())){
                ve.setFirst_name(list.get(i).getFirstName());
                ve.setLast_name(list.get(i).getLastName());
                ve.setEmail(list.get(i).getEmail());
                list1.add(ve);
                saveFlag = 1;
                break;
        }
    }

    if (saveFlag == 1) {
        return list1;
    } else {
        ve.setFirst_name("NULL");
        ve.setLast_name("NULL");
        ve.setEmail("NULL");
        list1.add(ve);
        return list1;
    }
}



public ResponseEntity<?> SaveEmployee(Employee employee){
    int saveFlag=0;
    String name = employee.getEmail();
    String name1;
    ErrorMessage em = new ErrorMessage();
    //final Employee user = employeeRepositary.findByEmail(employee).orElse(null);;
    //Employee emp= new Employee();
      Employee user= employeeRepositary.findByEmail(name);


   //@Query("SELECT email from Employee  where email =:name ");
    //List<Employee> findByEmail(@Param(value = "name") String name);

    if(user != null){
        //ErrorMessage em = new ErrorMessage();
        em.setStatus(0);
        em.setMessage("Email Already registered");
        //throw(new ResourceNotFoundException("Already exits by email  ::"+ employee.getEmail()));
        return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
    }
    else{
        employeeRepositary.save(employee);
        em.setStatus(1);
        em.setMessage("Succesfull");
        return new ResponseEntity<>(em, HttpStatus.ACCEPTED);

    }
}

    public ResponseEntity<?> update(Long employeeId,Employee employeeDetails){
        ErrorMessage em = new ErrorMessage();
        Optional<Employee> employee;
        Long c = employeeId;
        employee = employeeRepositary.findById(employeeId);
        if(employee==null){
            em.setStatus(0);
            em.setMessage("This ID is not found in the database");
            return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
        }
        else{
            employeeDetails.setEmail(employeeDetails.getEmail());
            employeeDetails.setFirstName(employeeDetails.getFirstName());
            employeeDetails.setLastName(employeeDetails.getLastName());
            return ResponseEntity.ok(this.employeeRepositary.save(employeeDetails));
        }
    }



    public ResponseEntity<?> delete(Long employeeId){
        ErrorMessage em = new ErrorMessage();   //common msg
        Optional<Employee> employee;
        Long c = employeeId;
        employee = employeeRepositary.findById(employeeId);
        //employee = employeeRepositary.findById(employeeId);
        if(employee != null){
            employeeRepositary.deleteById(employeeId);
            em.setStatus(1);
            em.setMessage("deleted Succesfully");
            return new ResponseEntity<>(em, HttpStatus.ACCEPTED);
        }
        else{
            em.setStatus(0);
            em.setMessage("ID not found");
            return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
        }


    }
}

