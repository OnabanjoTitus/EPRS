package com.payroll.services.util;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;


import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class EmployeeMapperTest {

    EmployeeMapper employeeMapper;

    @BeforeEach
    void setUp(){
        employeeMapper= Mappers.getMapper(EmployeeMapper.class);
    }
    @Test
    void givenEmployeeDtoSourceWhenMappedThenMapCorrectly(){
        EmployeeDto employeeDto= new EmployeeDto();
        employeeDto.setFirstName("Juan");
        employeeDto.setLastName("Mike");
        employeeDto.setRole("Accountant");

        Employee employee= new Employee();

        employeeMapper.updateEmployeeFromDto(employeeDto,employee);
        log.info("Employee object --> {}", employee);

        assertThat(employee.getFirstName()).isEqualTo(employeeDto.getFirstName());
        assertThat(employee.getLastName()).isEqualTo(employeeDto.getLastName());
        assertThat(employee.getRole()).isEqualTo(employeeDto.getRole());
    }

    @Test
    void givenEmployeeDtoDoNotMapToNullValues(){
        EmployeeDto employeeDto= new EmployeeDto();
        employeeDto.setFirstName("Juan");
        employeeDto.setLastName(null);
        employeeDto.setRole(null);

        Employee employee= new Employee();
        employee.setFirstName("Bob");
        employee.setLastName("Marley");
        employee.setRole("agba");

        log.info("Employee object --> {}", employee);
        employeeMapper.updateEmployeeFromDto(employeeDto,employee);
        log.info("Employee object --> {}", employee);

        assertThat(employee.getFirstName()).isEqualTo("Juan");
        assertThat(employee.getLastName()).isEqualTo("Marley");
        assertThat(employee.getRole()).isEqualTo("agba");
    }

}