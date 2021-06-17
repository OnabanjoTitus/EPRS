package com.payroll.services.employee;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class EmployeeServiceImplTest {
    @Autowired
    EmployeeService employeeService;

    @BeforeEach
    void setUp() {
    }



    @Test
    @Rollback(value = false)
    void updateEmployee() throws EmployeeNotFoundException, IdCannotBeNullException {
        EmployeeDto employeeDto= new EmployeeDto();
        employeeDto.setRole("Agba Agba");

        Employee employee=employeeService.findById(12);
        assertThat(employee.getFirstName()).isEqualTo("JohnXavier");
        assertThat(employee.getLastName()).isEqualTo("Dan");
        assertThat(employee.getRole()).isEqualTo("HR");


        employee=employeeService.updateEmployee(12,employeeDto);

        assertThat(employee.getFirstName()).isEqualTo("JohnXavier");
        assertThat(employee.getLastName()).isEqualTo("Dan");
        assertThat(employee.getRole()).isEqualTo("Agba Agba");
    }
}