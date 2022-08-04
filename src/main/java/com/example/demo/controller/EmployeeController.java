package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/employee")
	public void insertEmployee(@RequestBody Employee employee) {
		employeeRepository.save(employee);
	}
	
	@GetMapping("/employee")
	public List<Employee> getAllEmployee() {
		List<Employee> list=employeeRepository.findAll();
		return list;
	}
	
	@DeleteMapping("/employee/{eid}")
	public void deleteEmployee(@PathVariable("eid") Long eid) {
		employeeRepository.deleteById(eid);
	}
	
	@PutMapping("/employee/edit/{id}")
	public Employee editEmployee(@PathVariable("id") Long id,@RequestBody Employee newEmployee) {
		Optional<Employee> optionalEid = employeeRepository.findById(id);
		if(!optionalEid.isPresent())
			throw new RuntimeException("Invalid id");
		Employee oldEmployee=optionalEid.get();
		
		oldEmployee.setName(newEmployee.getName());
		oldEmployee.setSalary(newEmployee.getSalary());
		oldEmployee.setCity(newEmployee.getCity());
		
		return employeeRepository.save(oldEmployee);
	}
}
