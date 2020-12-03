package com.example.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.phonebook.entity.PhoneBook;
import com.example.phonebook.repository.PhoneBookRepository;

@Service
public class PhoneBookService implements PnoneBookInterface {
	
	@Autowired
	PhoneBookRepository pbRepo;
	
	public List<PhoneBook> listAll(String keyword){
		if(keyword != null) {
			return pbRepo.findAll(keyword);
		}
		return pbRepo.findAll();
	}

	@Override
	public List<PhoneBook> getAllPhoneBook() {
		
		return pbRepo.findAll();
	}

	@Override
	public void savePhoneBook(PhoneBook phonebook) {
		this.pbRepo.save(phonebook);
		
	}

	@Override
	public PhoneBook getPhoneBookById(Integer id) {
		Optional<PhoneBook> optional = pbRepo.findById(id);
		PhoneBook phonebook = null;
		if (optional.isPresent()) {
			phonebook = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return phonebook;
	}

	@Override
	public void deletePhoneBookById(Integer id) {
		
		this.pbRepo.deleteById(id);
	}

	@Override
	public Page<PhoneBook> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.pbRepo.findAll(pageable);
	}
	

}
