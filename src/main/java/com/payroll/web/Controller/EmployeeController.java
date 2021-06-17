package com.payroll.web.Controller;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.services.employee.EmployeeService;
import com.payroll.web.Exceptions.EmployeeCannotBeNullException;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?>findAll(){
//        EntityModel<Employee>employeeEntityModel=EntityModel
//        return employeeService.findAll();
        return null;
    }
    @PostMapping()
    public  ResponseEntity<?> save(@Valid @RequestBody EmployeeDto employeeDto ) throws EmployeeCannotBeNullException, IdCannotBeNullException, EmployeeNotFoundException {
        Employee employee= employeeService.save(employeeDto);
        EntityModel<Employee>employeeEntityModel=EntityModel.of(
               employee,linkTo(methodOn(EmployeeController.class)
                        .findById(employee.getId())).
                        withSelfRel(),linkTo(methodOn(EmployeeController.class).
                        findAll()).withRel("employees"));
        return ResponseEntity.created(employeeEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(employeeEntityModel);
    }
    @GetMapping("/{id}")
    public  ResponseEntity<?> findById(@PathVariable("id") Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        employeeService.findById(id);
        return null;
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        employeeService.deleteById(id);
    }
    @PatchMapping("/{id}")
    public  ResponseEntity<?> updateEmployee(@PathVariable("id")Integer id,@RequestBody EmployeeDto employeeDto) throws EmployeeNotFoundException {
        employeeService.updateEmployee(id,employeeDto);
        return null;
    }

}
