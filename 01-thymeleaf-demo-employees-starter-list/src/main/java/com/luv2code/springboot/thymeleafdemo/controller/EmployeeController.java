package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService){
		this.employeeService = employeeService;
	}

//	private List<Employee> theEmployees;

//	@PostConstruct
//	private void loadData() {
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "list-employees";
	}


	@GetMapping("/showFormForAdd")
	public String formForAdd(Model theModel){
		Employee employeeToAdd = new Employee();

		theModel.addAttribute("employees",employeeToAdd);

		return "employee-form";
	}


	//to update the employee form
	@GetMapping("/showFormForUpdate")
	public String formUpdate(@RequestParam("employeeId") int employeeId,Model theModel){
		theModel.addAttribute("employees",employeeService.findById(employeeId));

		return "employee-form";

	}


	//to delete form
	@GetMapping("/delete")
	public String deleteForm(@RequestParam("employeeId") int employeeId){
		employeeService.deleteById(employeeId);

		return "redirect:/employees/list";
	}

	//to save employee details
	@PostMapping("/save")
	public String saveEmployeeForm(@ModelAttribute("employees") Employee fetchEmployee){
		/*
		Using modelAttribute we are fetching the object Employees whole together to our fetchEmployee model attribute.
		 */
		employeeService.save(fetchEmployee);

		return "redirect:/employees/list";
	}


}









