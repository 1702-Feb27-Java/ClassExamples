package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.revature.beans.Employee;
import com.revature.service.EmployeeService;

@Controller
@RequestMapping(value="/employee")
public class EmployeeController
{
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService)
	{
		this.employeeService=employeeService;
	}
	
	@RequestMapping(value="/show")
	public String getEmployee(@RequestParam(value="e",required=true) int i, ModelMap map)
	{
		map.addAttribute("employee", employeeService.getEmployee(i));
		return "showEmployee";
	}
	
	@RequestMapping(value="/addToDatabase", method=RequestMethod.POST)
	public String addEmployee(@RequestParam(value="first", required=true) String first,
			@RequestParam(value="last", required=true) String last,
			@RequestParam(value="email", required=true) String email,
			ModelMap map)
	{
		Employee e  = new Employee(0,first,last,email);
		employeeService.addEmployee(e);
		return "forward:show?e="+e.getId();
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addAnEmployee()
	{
		return "addEmployee";
	}
	
	@RequestMapping(value="/testing", method=RequestMethod.GET)
	public String test(ModelMap map)
	{
		map.addAttribute("employee",new Employee());
		return "showEmployee";
	}
}
