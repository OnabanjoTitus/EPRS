package com.payroll.data.repository;

import com.payroll.data.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
//@Sql(scripts = {"classpath:db/insert.sql"})
class EmployeeRepositoryTest {
    @Autowired
    EmployeeRepository employeeRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }
    @Test
    void updateEmployeeRecordTest(){
        Employee employee= employeeRepository.findById(12).orElse(null);
        assertThat(employee).isNotNull();
        log.info("Employee before save -->{}",employee);
        employee.setFirstName("JohnXavier");
        employeeRepository.save(employee);
        assertThat(employee.getFirstName()).isEqualTo("JohnXavier");
        assertThat(employee.getLastName()).isEqualTo("Dan");
        assertThat(employee.getRole()).isEqualTo("HR");
        log.info("Employee after save -->{}",employee);

    }
}