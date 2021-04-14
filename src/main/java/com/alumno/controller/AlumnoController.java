package com.alumno.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alumno.model.Alumno;
import com.alumno.repository.AlumnoRepository;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {
	
	@Autowired
	private AlumnoRepository alumnoRepository;
	
	
	@GetMapping("") 
	public String home(Model model) {
		model.addAttribute("alumnos", this.alumnoRepository.findAll());
		
		return"home";
	}
	
	@GetMapping("/create")
	public String add() {
		
		return"create";
	}
	
	@PostMapping("/add")
	public String addAlumno(Model model, Alumno alumno) {		
		model.addAttribute("alumnos", this.alumnoRepository.save(alumno));
		return"redirect:/alumnos";		
	}
	
	@GetMapping("/edit/{id}")
	public String editAlumno(@PathVariable ("id") Long id, Model model) {
		
		Alumno alumno = this.alumnoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));
		
		model.addAttribute("alumno", alumno);
		return"edit";
		
		
	}
	
	@PostMapping("/update/{id}")
	public String edit(@PathVariable ("id") Long id,@Valid Alumno alumno, BindingResult result, Model model) {
		if(result.hasErrors()) {
			alumno.setId(id);
			return"edit";
		}
		
		 alumnoRepository.save(alumno);
		 
		 model.addAttribute("alumnos", this.alumnoRepository.findAll());
		return"redirect:/alumnos";
		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable ("id") Long id, Model model) {

		Alumno alumno = this.alumnoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid student id : " + id));
		
		this.alumnoRepository.delete(alumno);
		model.addAttribute("alumno", this.alumnoRepository.findAll());
		return"redirect:/alumnos";
		
	}
	
	
	

}
