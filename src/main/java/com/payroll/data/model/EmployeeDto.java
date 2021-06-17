package com.payroll.data.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EmployeeDto {
    @NotNull(message = "firstname must have a value")
    private String firstName;
    @NotNull(message = "lastname must have a value")
    private String lastName;
    @NotNull(message = "role must have a value")
    private String role;
}
