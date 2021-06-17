package com.payroll.web.Controller;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.services.employee.EmployeeService;
import com.payroll.web.Exceptions.EmployeeCannotBeNullException;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @PostMapping()
    public Employee save(@Valid @RequestBody EmployeeDto employee ) throws EmployeeCannotBeNullException {
        return employeeService.save(employee);
    }
    @GetMapping("/{id}")
    public Employee findById(@PathVariable("id") Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        return employeeService.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        employeeService.deleteById(id);
    }
    @PatchMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id")Integer id,@RequestBody EmployeeDto employeeDto) throws EmployeeNotFoundException {
        return employeeService.updateEmployee(id,employeeDto);
    }

}
