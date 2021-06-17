package com.payroll.web.Controller;

import com.payroll.data.model.Employee;
import com.payroll.data.model.EmployeeDto;
import com.payroll.services.employee.EmployeeService;
import com.payroll.web.Exceptions.EmployeeCannotBeNullException;
import com.payroll.web.Exceptions.EmployeeNotFoundException;
import com.payroll.web.Exceptions.IdCannotBeNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public ResponseEntity<?>findAll(){
        List<Employee>employees=  employeeService.findAll()
                .stream()
                .map(employee -> employee.add(linkTo(methodOn(EmployeeController.class)).withSelfRel())).collect(Collectors.toList());
        return ResponseEntity.ok(employees);
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

        Employee employee= employeeService.findById(id);;
        EntityModel<Employee>employeeEntityModel=EntityModel.of(
                employee,linkTo(methodOn(EmployeeController.class)
                        .findById(employee.getId())).
                        withSelfRel(),linkTo(methodOn(EmployeeController.class).
                        findAll()).withRel("employees"));
        return new ResponseEntity<>(employeeEntityModel, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) throws IdCannotBeNullException, EmployeeNotFoundException {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();

    }
    @PatchMapping("/{id}")
    public  ResponseEntity<?> updateEmployee(@PathVariable("id")Integer id,@RequestBody EmployeeDto employeeDto) throws IdCannotBeNullException {
        Employee employee=null;
        EntityModel<Employee>employeeEntityModel=null;
       try{
           employee=  employeeService.updateEmployee(id,employeeDto);
           employeeEntityModel=EntityModel.of(
                employee,linkTo(methodOn(EmployeeController.class)
                        .findById(employee.getId())).
                        withSelfRel(),linkTo(methodOn(EmployeeController.class).
                        findAll()).withRel("employees"));}
       catch (EmployeeNotFoundException employeeNotFoundException){
           log.info("Error occurred -->{}",employeeNotFoundException.getMessage());
           ResponseEntity.badRequest().body(employeeNotFoundException.getMessage());
       }
        return ResponseEntity.ok().body(employeeEntityModel);
    }

}
