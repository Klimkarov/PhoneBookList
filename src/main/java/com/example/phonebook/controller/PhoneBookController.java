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
import com.example.phonebook.service.PhoneBookService;
import com.example.phonebook.service.PnoneBookInterface;

@Controller
public class PhoneBookController {

	@Autowired
	PhoneBookRepository pbRepo;
	
	@Autowired
	PhoneBookService pbService;
	
	@Autowired
	PnoneBookInterface phonebookInterface;
	
//	@GetMapping("/")
//	public String viewHomePage(Model model) {
//		return findPaginated(1, "firstName", "asc", model);		
//	}

	@GetMapping("/filter")
	public String welcomePage(Model model, @Param("keyword") String keyword) {

		PhoneBook phonebook = new PhoneBook();
		List<PhoneBook> listPB = pbService.listAll(keyword);

		model.addAttribute("listPB", listPB);
		model.addAttribute("keyword", keyword);
		model.addAttribute("phonebook", phonebook);
	
		

		return ("welcomePage");
	}

	@GetMapping("/showNewPhoneBookPage")
	public String showNewPhoneBookPage(Model model) {

		PhoneBook phonebook = new PhoneBook();
		model.addAttribute("phonebook", phonebook);
		return "newPhoneBook";
	}

	@PostMapping("/savePhoneBook")
	public String savePhoneBook(@ModelAttribute("phonebook") PhoneBook phonebook) {
		pbRepo.save(phonebook);
		return "redirect:/filter";
	}
	
	@GetMapping("/deletePhoneBook/{id}")
	public String deletePhoneBook(@PathVariable("id")Integer id) {
		pbRepo.deleteById(id);
		return "redirect:/filter";
	}
	
//	@GetMapping("/page/{pageNo}")
//	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
//			@RequestParam("sortField") String sortField,
//			@RequestParam("sortDir") String sortDir,
//			Model model) {
//		int pageSize = 5;
//		
//		Page<PhoneBook> page = phonebookInterface.findPaginated(pageNo, pageSize, sortField, sortDir);
//		List<PhoneBook> listPB = page.getContent();
//		
//		model.addAttribute("currentPage", pageNo);
//		model.addAttribute("totalPages", page.getTotalPages());
//		model.addAttribute("totalItems", page.getTotalElements());
//		
//		model.addAttribute("sortField", sortField);
//		model.addAttribute("sortDir", sortDir);
//		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
//		
//		model.addAttribute("listPB", listPB);
//		return "index";
//	}
//	
}
