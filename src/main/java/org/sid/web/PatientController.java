package org.sid.web;


import java.util.List;

import javax.validation.Valid;

import org.sid.DAO.PatientRepository;
import org.sid.ENTITIES.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PatientController {
	@Autowired
	private PatientRepository patientRepository;
	@GetMapping(path="/")
	
public String index(Model model,	  
		@RequestParam(name="page",defaultValue="0")int page,
		@RequestParam(name="size",defaultValue = "5")int size,
		@RequestParam(name="motCle",defaultValue = "")String motCle) {
		Page<Patient>pagepatients=patientRepository.
				   findByNameContains(motCle, PageRequest.of(page, size));
		   model.addAttribute("pagePatients",pagepatients);
		   model.addAttribute("currentPage", page);
		   model.addAttribute("size", size);
		   model.addAttribute("motCle", motCle);
		   model.addAttribute("pages",new int[pagepatients.getTotalPages()]);	
		   System.out.println(pagepatients.getTotalPages());
	return "index";
}
   @GetMapping(path="/patients")
	public String patients(Model model,	  
@RequestParam(name="page",defaultValue="0")int page,
@RequestParam(name="size",defaultValue = "5")int size,
@RequestParam(name="motCle",defaultValue = "")String motCle) {
	   Page<Patient>pagepatients=patientRepository.
			   findByNameContains(motCle, PageRequest.of(page, size));
	   model.addAttribute("pagePatients",pagepatients);
	   model.addAttribute("currentPage", page);
	   model.addAttribute("size", size);
	   model.addAttribute("motCle", motCle);
	   model.addAttribute("pages",new int[pagepatients.getTotalPages()]);	
	   System.out.println(pagepatients.getTotalPages());
	   return "patients";
	}
	@GetMapping(path="/deletePatient")
public String delete(Long id,String motCle,String page,String size) {
    patientRepository.deleteById(id);
	return "redirect:/patients?page="+page+"&motCle="+motCle+"&size="+size;}
	@RequestMapping(path="/formPatient", method=RequestMethod.GET) public String formPatient(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("mode", "new");
		return "formPatient";}
		@PostMapping("/save")
		public String savePatient(Model model,@Valid Patient patient, BindingResult bindingResult) 
		{ if(bindingResult.hasErrors()) return "formPatient";
		patientRepository.save(patient);
		model.addAttribute("patient", patient);
		return "confirmation";}
		@GetMapping(path="/editPatient")
		public String edit(Model model,Long id ) { Patient p=patientRepository.findById(id).get();
		model.addAttribute("patient", p);
		model.addAttribute("mode", "edit");
		return "formpatient";}
		@GetMapping("/listPatients")
		@ResponseBody
		public List<Patient> listPatient() {
		return patientRepository.findAll();}
		@GetMapping("/patients/{id}")
		@ResponseBody
		public Patient getOne(@PathVariable Long id) {
		return patientRepository.findById(id).get();}}
