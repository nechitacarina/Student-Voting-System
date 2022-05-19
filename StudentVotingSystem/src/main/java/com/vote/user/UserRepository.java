package com.vote.user;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String>{

	 @Query(
	            value = "select CNP from utilizator where CNP LIKE %:term%",
	            nativeQuery = true
	    )
	    List<String> getCNPSuggestion(String term);

	 @Query("SELECT u FROM User u WHERE " 
			 +"CONCAT(u.nume, u.prenume, u.CNP, u.telefon, u.email)" 
			 +"LIKE %?1%")
	public Page<User> findAll(String keyword, Pageable pageable);
}
