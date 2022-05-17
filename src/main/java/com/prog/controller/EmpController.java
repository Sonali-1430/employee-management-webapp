package com.prog.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prog.entity.Employee;
import com.prog.service.EmpService;

@Controller
public class EmpController {

	@Autowired
	private EmpService service;

	@GetMapping
	public String home(Model m) {

		List<Employee> emp = service.getAllEmp();
		m.addAttribute("emp", emp);

		return "index";
	}

	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}

	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee e, HttpSession session) {
		System.out.println(e);

		service.addEmp(e);
		session.setAttribute("msg", "Employee Added Successfully..");
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
		Employee e = service.getEMpByid(id);
		m.addAttribute("emp", e);
		return "edit";

	}

	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session) {
		 
		session.setAttribute("msg", "Emp Data Updated Sucessfully..");
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteEMp(@PathVariable int id, HttpSession session) {

		service.deleteEMp(id);
		session.setAttribute("msg", "Emp Data Deleted Sucessfully..");
		return "redirect:/";
	}
	
	@RequestMapping("/updatesalary/{id}/{salary}")
	@ResponseBody
	public Employee updateAndGetSalary(@PathVariable int id, @PathVariable int salary)
	{
		Employee e = service.getEMpByid(id);
		e.setSalary(salary);
		service.addEmp(e);
		return e;
	}
}
