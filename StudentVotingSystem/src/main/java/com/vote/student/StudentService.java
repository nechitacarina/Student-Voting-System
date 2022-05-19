package com.vote.student;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	public Page<Student> listAll(int pageNumber, String keyword){
		Pageable pageable = PageRequest.of(pageNumber - 1, 3);
		if(keyword != null) {
			return studentRepo.findAll(keyword, pageable);
		}
		return studentRepo.findAll(pageable);
	}
	
	public void save(Student student) {
		studentRepo.save(student);
	}
	
	public Student get(Integer id_student){
		Optional<Student> result = studentRepo.findById(id_student);
		return result.get();
	}
	
	public void delete(Integer id_student) {		
		studentRepo.deleteById(id_student);
	}
}
