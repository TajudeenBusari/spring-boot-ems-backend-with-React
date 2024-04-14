package com.TJTechy.ems.controller;

import com.TJTechy.ems.dto.EmployeeDto;
import com.TJTechy.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*") //allows all clients to call the Rest API
@AllArgsConstructor //this ensures all constructor is created for the dependency injected
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  //Build add employee Rest API
  @PostMapping
  public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
   EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
   return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
  }

  //build rest Employee Rest API
  @GetMapping("{id}")
  public ResponseEntity<EmployeeDto> getEmployeeWithId(@PathVariable("id") Long employeeId){
    EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
    return ResponseEntity.ok(employeeDto);
    /*because the pathVariable (id) and the variable (employeeId)
    names are different,the id has to be specified in the pathVariable
     */
  }

  //Build Get Employee Rest API
  @GetMapping
  public ResponseEntity<List<EmployeeDto>> getALLEmployees(){
    List <EmployeeDto> employees = employeeService.getAllEmployees();
    return ResponseEntity.ok(employees);
  }

  //Build Update Employee Rest API
  @PutMapping("{id}")
  public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                    @RequestBody EmployeeDto updatedEmployee){
    EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployee);
    return ResponseEntity.ok(employeeDto);
  }

  //build delete employee API
  @DeleteMapping("{id}") //id id url template variable
  public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId){
    employeeService.deleteEmployee(employeeId);
    return ResponseEntity.ok("Employee Deleted Successfully");
  }

}
