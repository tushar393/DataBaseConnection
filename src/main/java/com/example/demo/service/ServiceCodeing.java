package com.example.demo.service;

import com.example.demo.exception.ErrorMessage;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.ViewEntity;
import com.example.demo.repositary.EmployeeRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCodeing {
    @Autowired
   public EmployeeRepositary employeeRepositary;

    public List<Employee> getDetails(){
        return employeeRepositary.findAll();
    }

public List<ViewEntity> get(Long employeeId) {
    int saveFlag = 0;
    int k = 0;
    ErrorMessage em = new ErrorMessage();
    Employee employee;

    List<Employee> list = employeeRepositary.findAll();
    List<ViewEntity> list1 = new ArrayList<>();
    List<ViewEntity> list2 = new ArrayList<>();
    ViewEntity ve = new ViewEntity();

    for (int i = 0; i < list.size(); i++) {
        if (employeeId.equals(list.get(i).getId())) {
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

    //else{
      //  em.setStatus(0);
       // em.setMessage("ID not found");
      //  return ;
        //return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
   // }

public ResponseEntity<?> SaveEmployee(Employee employee){
    int saveFlag=0;
    ErrorMessage em = new ErrorMessage();
    //findbyEmail
    List<Employee> list= employeeRepositary.findAll();
    for(int i=0;i<list.size();i++){
        if(employee.getEmail().equals(list.get(i).getEmail())){
            saveFlag=1;
            break;
        }
    }
    if(saveFlag==0){
        employeeRepositary.save(employee);
        em.setStatus(1);
        em.setMessage("Succesfull");
        return new ResponseEntity<>(em, HttpStatus.ACCEPTED);
    }
    else{
        //ErrorMessage em = new ErrorMessage();
        em.setStatus(0);
        em.setMessage("Email Already registered");
        //throw(new ResourceNotFoundException("Already exits by email  ::"+ employee.getEmail()));
        return new ResponseEntity<>(em, HttpStatus.BAD_REQUEST);
    }
}

    public ResponseEntity<?> update(Long employeeId,Employee employeeDetails){
        ErrorMessage em = new ErrorMessage();
        Optional<Employee> employee;
        Long c = employeeId;
        employee = employeeRepositary.findById(employeeId);
        if(employee==null){
            em.setStatus(0);
            em.setMessage("ID not found");
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

