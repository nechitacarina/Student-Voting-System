package com.vote.group;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class GroupService {

	@Autowired
	private GroupRepository groupRepo;
	
	public Page<Group> listAll(int pageNumber){
		Pageable pageable = PageRequest.of(pageNumber - 1, 2);
		return groupRepo.findAll(pageable);
	}
	
	public List<Group> listAll(){
		return (List<Group>) groupRepo.findAll();
	}
	
	public void save(Group group) {
		groupRepo.save(group);
	}
	
	public Group get(Integer id_grup_tinta){
		Optional<Group> result = groupRepo.findById(id_grup_tinta);
		return result.get();
	}
	
	public void delete(Integer id_grup_tinta) {		
		groupRepo.deleteById(id_grup_tinta);
	}
}
