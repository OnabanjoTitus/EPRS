package com.payroll.services.employee;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.data.repository.EmployeeRepository;
import com.payroll.services.util.EmployeeMapper;
import com.payroll.web.Exceptions.EmployeeCannotBeNullException;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ModelMapper modelMapper;

    public EmployeeServiceImpl() {
        this.employeeMapper = Mappers.getMapper(EmployeeMapper.class);
    }

    EmployeeMapper employeeMapper;

    @Override
    public Employee save(EmployeeDto employeeDto) throws EmployeeCannotBeNullException {
        if(employeeDto==null){

            throw new EmployeeCannotBeNullException("Employee cannot be empty");
        }
        Employee employee= new Employee();
        employeeMapper.updateEmployeeFromDto(employeeDto,employee);
        log.info("Employee after mapping is -->{}",employee);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee findById(Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        if(id==null){
            throw new IdCannotBeNullException("The Employee Id Cannot be Null");
        }
        Employee employeeFound=employeeRepository.findById(id).orElse(null);
        if(employeeFound!=null){
            return employeeFound;
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
            throw new EmployeeNotFoundException("No employee meets the id");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Integer id,EmployeeDto employeeDto) throws EmployeeNotFoundException {
          Employee employee= employeeRepository.findById(id).orElse(null);
          if(employee==null){
              throw new EmployeeNotFoundException("No employee meets the id");
          }
          log.info("Employee before mapping -->{}",employee);
          employeeMapper.updateEmployeeFromDto(employeeDto,employee);
          log.info("Employee after mapping -->{}",employee);
        return employeeRepository.save(employee);
    }
}
