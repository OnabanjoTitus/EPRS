package com.payroll.services.employee;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.data.repository.EmployeeRepository;
import com.payroll.web.Exceptions.EmployeeCannotBeNullException;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;
    @Override
    public Employee save(EmployeeDto employeedto) throws EmployeeCannotBeNullException {
        if(employeedto==null){
            throw new EmployeeCannotBeNullException("Employee cannot be empty");
        }
        Employee employee= new Employee();
        modelMapper.map(employeedto,employee);

        log.info("Employee after mapping is -->{}",employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        if(id==null){
            throw new IdCannotBeNullException("The Employee Id Cannot be Null");
        }
        Optional<Employee> employeeFound=employeeRepository.findById(id);
        if(employeeFound.isPresent()){
            return employeeFound.get();
        }else {
            throw new EmployeeNotFoundException("No employee meets the id -->{}");
        }

    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        if(id==null){
            throw new IdCannotBeNullException("The Employee Id Cannot be Null");
        }
        if(employeeRepository.findById(id).isEmpty()){
            throw new EmployeeNotFoundException("No employee meets the id -->{}");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Integer id,EmployeeDto employeeDto) {

        return null;
    }
}
