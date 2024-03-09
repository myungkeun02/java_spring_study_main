package org.myungkeun.imployee_study.service;

import org.myungkeun.imployee_study.payload.EmployeeDto;
import org.myungkeun.imployee_study.payload.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getAllEmployee();

    EmployeeDto getEmployeeById(String id);

    EmployeeDto updateEmployeeById(String id, EmployeeDto employeeDto);

    String deleteEmployeeById(String id);
}
