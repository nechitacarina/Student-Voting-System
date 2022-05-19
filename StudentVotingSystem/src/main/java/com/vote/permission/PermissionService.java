package com.vote.permission;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository repo;
	
	public List<Permission> listAll(){
		return (List<Permission>) repo.findAll();
	}
	
	public void save(Permission permission) {
		repo.save(permission);
	}
	
	public Permission get(Integer id_permisiune) throws PermissionNotFoundException{
		Optional<Permission> result = repo.findById(id_permisiune);
		if(result.isPresent()) {
			return result.get();
		}
		throw new PermissionNotFoundException("Could not find any permissions with ID " + id_permisiune);
	}

	public void delete(Integer id_permisiune) {
		repo.deleteById(id_permisiune);
	}
}
