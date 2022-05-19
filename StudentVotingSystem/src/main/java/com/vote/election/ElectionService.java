package com.vote.election;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vote.student.Student;
import com.vote.student.StudentRepository;
import com.vote.user.User;
import com.vote.user.UserRepository;

@Service
public class ElectionService {

	@Autowired
	private ElectionRepository electionRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Page<Election> listAll(int pageNumber, String keyword){
		Pageable pageable = PageRequest.of(pageNumber - 1, 2);
		if(keyword != null) {
			return  electionRepo.findAll(keyword, pageable);
		}
		return electionRepo.findAll(pageable);
	}
	
	public List<Election> findElectionByStudent(){
		Authentication loggedInUser  = SecurityContextHolder.getContext().getAuthentication();
		String cnp = loggedInUser.getName();
		User user = userRepo.findById(cnp).get();
		Student student = studentRepo.findByUser(user); 
		Integer id_student = student.getId_student();
		return (List<Election>) electionRepo.findElectionByStudent(id_student);
	}
	
	public void save(Election election) {
		electionRepo.save(election);
	}
	
	public Election get(Integer id_chestionar) throws ElectionNotFoundException{
		Optional<Election> result = electionRepo.findById(id_chestionar);
		if(result.isPresent()) {
			return result.get();
		}
		throw new ElectionNotFoundException("Could not find any permissions with ID " + id_chestionar);
	}
	
	public void delete(Integer id_chestionar) {
		electionRepo.deleteById(id_chestionar);
	}

	public List<Election> listAll() {
		return (List<Election>) electionRepo.findAll();
	}
}
