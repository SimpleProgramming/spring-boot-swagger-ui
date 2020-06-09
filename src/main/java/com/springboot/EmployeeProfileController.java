package com.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiOperation(value = "/profile/v1/employee", tags = "Employee Profile Controller", notes = "Employee Profile API")
@RestController
@RequestMapping("/profile/v1/employee")
public class EmployeeProfileController {

	List<Employee> employees = Arrays.asList(
					new Employee(12345L, "John", "Electrical", 23023456788L),
					new Employee(22345L, "Sam", "Computer", 33023456788L),
					new Employee(32345L, "Jack", "Electronics", 43023456788L),
					new Employee(42345L, "Mark", "Mechanical", 53023456788L)
	);

	@ApiOperation(value = "Fetch All Employees")
	@ApiResponses(value = {
					@ApiResponse(code = 200, message = "SUCCESS", response = Employee.class),
					@ApiResponse(code = 401, message = "UNAUTHORIZED!", response = ErrorResponse.class),
					@ApiResponse(code = 403, message = "FORBIDDEN!", response = ErrorResponse.class),
					@ApiResponse(code = 404, message = "NOT FOUND")
	})
	@GetMapping(value = "/fetch/all")
	public List<Employee> fetchAllEmployees() {
		return employees;
	}

	@ApiOperation(value = "Fetch Employee by Name", response = Employee.class)
	@GetMapping(value = "/fetch/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee fetchEmployeeByName(@ApiParam(value = "Employee Name") @PathVariable(value = "name") String name) {
		return employees.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().get();
	}

	@ApiOperation(value = "Get Employee By Department", response = Employee.class)
	@GetMapping(value = "/fetch/{department}")
	public List<Employee> fetchEmployeeByDepartment(@ApiParam(value = "Department Name", required = true) @PathVariable(value = "department") String department) {
		return employees.stream().filter(x -> x.getDepartment().equalsIgnoreCase(department)).collect(Collectors.toList());
	}

	@ApiOperation(value = "Insert Employee Record", response = Employee.class)
	@PostMapping
	public Employee insertEmployee(@ApiParam(value = "Employee") @RequestBody Employee employee) {
		return employee;
	}

	@ApiOperation(value = "Update Employee Details", response = Employee.class)
	@PutMapping
	public Employee updateEmployee(@ApiParam(value = "Employee") @RequestBody Employee employee) {
		return employee;
	}

	@ApiOperation(value = "Delete an Employee", response = Employee.class)
	@DeleteMapping
	public Employee deleteEmployee(@ApiParam(value = "Employee") @RequestBody Employee employee) {
		return employee;
	}

	@ApiOperation(value = "Partial Update a specific Student in the System ", response = Employee.class)
	@PatchMapping
	public Object patchEmployee(@ApiParam(value = "Employee") @RequestBody Map<String, Object> partialUpdate) {
		return partialUpdate;
	}
}
