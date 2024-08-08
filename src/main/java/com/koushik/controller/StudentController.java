package com.koushik.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koushik.binding.Student;
import com.koushik.entity.StudentEntity;
import com.koushik.repo.StudentRepository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@Controller
public class StudentController {
	
	@Autowired
	private StudentRepository repo;
	//Method to load form
	
	@GetMapping("/")
	public String loadForm(Model model)	
	{
		loadFormData(model);
		return "index";
	}

	private void loadFormData(Model model) {
		List<String> coursesList = new ArrayList<>();
		coursesList.add("java");
		coursesList.add("Devops");
		coursesList.add("Python");
		coursesList.add("AWS");
		
		ArrayList<String> timingsList = new ArrayList<>();
		timingsList.add("Morning");
		timingsList.add("Afternoon");
		timingsList.add("Evening");
		
		
		model.addAttribute("courses", coursesList);
		model.addAttribute("timings", timingsList);
		Student student = new Student();
		model.addAttribute("student", student);
	}
	
	//Method to save Student form data
	@PostMapping("/save")
	public String handleSubmit(Student s, Model model)
	{
		System.out.println(s);
		
		//Logic to save
		StudentEntity entity = new StudentEntity();
		
		//Copy data from binding object to entity object
		BeanUtils.copyProperties(s, entity);
		entity.setTimings(Arrays.toString(s.getTimings()));
		repo.save(entity);
		model.addAttribute("msg", "Student Saved");
		loadFormData(model);
		
		return "index";
	}
	
	//method to display save student data
	
	@GetMapping("/viewStudents")
	public String getStudentsData(Model model)
	{
		List<StudentEntity> studentsList = repo.findAll();
		model.addAttribute("studentsList", studentsList);
		return "data";
	}
	

}
