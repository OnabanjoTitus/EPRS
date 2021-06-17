package com.payroll.services.employee;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.web.Exceptions.EmployeeCannotBeNullException;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;

import java.util.List;

public interface EmployeeService {
    Employee save(EmployeeDto employee) throws EmployeeCannotBeNullException;
    Employee findById(Integer id) throws IdCannotBeNullException, EmployeeNotFoundException;
    List<Employee>findAll();
    void deleteById(Integer id) throws IdCannotBeNullException, EmployeeNotFoundException;
    Employee updateEmployee(Integer id,EmployeeDto employeeDto);
}
