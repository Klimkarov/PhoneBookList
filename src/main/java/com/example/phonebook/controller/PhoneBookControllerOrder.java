package com.example.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.phonebook.entity.PhoneBook;
import com.example.phonebook.repository.PhoneBookRepository;
import com.example.phonebook.service.PnoneBookInterface;

@Controller
public class PhoneBookControllerOrder {
	
	@Autowired
	PhoneBookRepository pbRepo;
	
	@Autowired
	PnoneBookInterface phonebookInterface;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		PhoneBook phonebook = new PhoneBook();
		model.addAttribute("phonebook", phonebook);
		List<PhoneBook> listPB = pbRepo.findAll();

		model.addAttribute("listPB", listPB);
		return findPaginated(1, "firstName", "asc", model);		
	}
	


	@GetMapping("/showNewPhoneBookOrderPage")
	public String showNewPhoneBookPageOrder(Model model) {

		PhoneBook phonebook = new PhoneBook();
		model.addAttribute("phonebook", phonebook);
		return "newPhoneBookOrder";
	}

	@PostMapping("/savePhoneBookOred")
	public String savePhoneBookOrder(@ModelAttribute("phonebook") PhoneBook phonebook) {
		pbRepo.save(phonebook);
		return "redirect:/";
	}
	
	@GetMapping("/deletePhoneBookOrder/{id}")
	public String deletePhoneBook(@PathVariable("id")Integer id) {
		pbRepo.deleteById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<PhoneBook> page = phonebookInterface.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<PhoneBook> listPB = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listPB", listPB);
		return "index";
	}
	

}
