package com.TJTechy.ems.service.implementation;

import com.TJTechy.ems.dto.EmployeeDto;
import com.TJTechy.ems.entity.Employee;
import com.TJTechy.ems.exception.ResourceNotFoundException;
import com.TJTechy.ems.mapper.EmployeeMapper;
import com.TJTechy.ems.repository.EmployeeRepository;
import com.TJTechy.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private EmployeeRepository employeeRepository;


  @Override
  public EmployeeDto createEmployee(EmployeeDto employeeDto) {
    Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
    Employee savedEmployee = employeeRepository.save(employee);
    return EmployeeMapper.mapToEmployeeDto(savedEmployee);
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {

    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(()->
                    new ResourceNotFoundException("Employee with the given id does not exist: " + employeeId));
    return EmployeeMapper.mapToEmployeeDto(employee);
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream()
            .map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
            .collect(Collectors.toList());
  }

  @Override
  public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {


   Employee employee = employeeRepository.findById(employeeId)
           .orElseThrow(() ->
            new ResourceNotFoundException("Employee with the given id does not exist :" + employeeId)
   );
    employee.setFirstName(updatedEmployee.getFirstName());
    employee.setLastName(updatedEmployee.getLastName());
    employee.setEmail(updatedEmployee.getEmail());
    Employee updatedEmployeeObj = employeeRepository.save(employee);
    return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
  }

  @Override
  public void deleteEmployee(Long employeeId) {

    Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("Employee with the given id does not exist :" + employeeId)
            );
    employeeRepository.deleteById(employeeId);
  }
}