package com.example.phonebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.phonebook.entity.PhoneBook;

@Repository
public interface PhoneBookRepository extends JpaRepository<PhoneBook, Integer>{

	@Query("SELECT p FROM PhoneBook p WHERE " // e.firstName LIKE %?1%"
			//	+ " OR p.lastName LIKE %?1%"
			//	+ " OR p.email LIKE %?1%"
		    //    + " OR p.company LIKE %?1%"
		    //    + " OR p.salary LIKE %?1%"
		+ "CONCAT(p.id, p.firstName, p.lastName, p.address, p.phone)"
		+ "LIKE %?1%")
	List<PhoneBook> findAll(String keyword);



}
