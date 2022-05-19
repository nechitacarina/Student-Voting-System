package com.vote.user;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired UserRepository repo;
	PasswordEncoder passwordEncoder;
	
	public Page<User> listAll(int pageNumber, String keyword){
		Pageable pageable = PageRequest.of(pageNumber - 1, 3);
		if(keyword != null) {
			return repo.findAll(keyword, pageable);
		}
		return repo.findAll(pageable);
	}
	
	public List<User> listAll(){
		return (List<User>) repo.findAll();
	}
	
	public void save(User user) {
		this.passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = this.passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
	}
	
	public User get(String cNP){
		Optional<User> result = repo.findById(cNP);
		return result.get();
	}
	
	@Transactional
	public List<String> getCNPSuggestion(String term){
		return repo.getCNPSuggestion(term);
	}
	
	public void delete(String CNP) {		
		repo.deleteById(CNP);
	}
	
}
