package com.vote.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	
	@Autowired private RoleRepository roleRepo;
	
	public List<Role> listAll(){
		return (List<Role>) roleRepo.findAll();
	}

	public void save(Role role) {
		roleRepo.save(role);
	}
	
	public Role get(Integer id_rol) throws RoleNotFoundException{
		Optional<Role> result = roleRepo.findById(id_rol);
		if(result.isPresent()) {
			return result.get();
		}
		throw new RoleNotFoundException("Could not find any roles with ID " + id_rol);
	}
	
	public void delete(Integer id_rol) {		
		roleRepo.deleteById(id_rol);
	}
}
