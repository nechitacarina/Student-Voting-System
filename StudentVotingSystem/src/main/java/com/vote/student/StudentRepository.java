package com.vote.student;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.vote.user.User;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends PagingAndSortingRepository<Student, Integer>{

	@Query ("SELECT s FROM Student s WHERE "
			+"CONCAT(s.user.CNP, s.user.nume, s.user.prenume, s.user.telefon, s.user.email)"
			+ "LIKE %?1%")
	public  Page<Student> findAll(String keyword, Pageable pageable);

	//public Optional<Student> findById(Integer id_student);
	
	public Student findByUser(User user);
}

