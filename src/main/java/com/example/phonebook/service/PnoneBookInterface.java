package com.example.phonebook.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.phonebook.entity.PhoneBook;

public interface PnoneBookInterface {
	
	List<PhoneBook> getAllPhoneBook();
	void savePhoneBook(PhoneBook phonebook);
	PhoneBook getPhoneBookById(Integer id);
	void deletePhoneBookById(Integer id);
	Page<PhoneBook> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

}
