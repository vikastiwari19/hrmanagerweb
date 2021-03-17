package com.sts.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sts.entities.Employee;
import com.sts.exception.ResourceNotFoundException;
import com.sts.dao.EmpRepository;


@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	private EmpRepository empRepository;
	
	
	//Get All Employees
	
	@GetMapping("/getAll")
	public List<Employee> getAllEmployees()
	{
		return this.empRepository.findAll();
	}
	
	// Get Employee by ID
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable (value="id") int empCode)
	{
		return this.empRepository.findById(empCode)
				.orElseThrow(()->new ResourceNotFoundException("User Not Found with EmpCode :" + empCode));
	}
	
	//Create Employee
	
	@PostMapping("/create")
	public Employee createEmployee(@Valid @RequestBody Employee emp)
	{
		return this.empRepository.save(emp);
		
	}
	
	// Update User
	@PutMapping("/update")
	public Employee updateEmployee(@RequestBody Employee emp)
	{

		return this.empRepository.save(emp);
	}
	
	// Delete employee by id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int empCode)
	{
		Employee existingEmp=this.empRepository.findById(empCode)
				.orElseThrow(()->new ResourceNotFoundException("User Not Found with EmpCode :" + empCode));
		this.empRepository.delete(existingEmp);
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
}
