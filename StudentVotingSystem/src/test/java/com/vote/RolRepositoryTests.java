package com.vote;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.vote.role.Role;
import com.vote.role.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RolRepositoryTests {

	@Autowired private RoleRepository repo;
	
	@Test
	public void testAddNew() {
		Role rol = new Role();
		rol.setId_rol(1);
		rol.setDenumire("test springboot add method1");
		Role savedRol = repo.save(rol);
		Assertions.assertThat(savedRol).isNotNull();
	}
	
	@Test 
	public void testListAll() {
		Iterable<Role> roluri = repo.findAll();
		Assertions.assertThat(roluri).hasSizeGreaterThan(0);
		for(Role rol : roluri) {
			System.out.println(rol);
		}
	}
	
	@Test
	public void testUpdate() {
		
		Integer rolid = 10;
		Optional<Role> optionalRol = repo.findById(rolid);
		Role rol = optionalRol.get();
		rol.setDenumire("test method updated with springboot");
		repo.save(rol);
		
		Role updatedRol = repo.findById(rolid).get();
		Assertions.assertThat(updatedRol.getDenumire()).isEqualTo("test method updated with springboot");
	}
	
	@Test
	public void testGet() {
		
		Integer rolid = 10;
		Optional<Role> optionalRol = repo.findById(rolid);
		Assertions.assertThat(optionalRol).isPresent();
		System.out.println(optionalRol.get());
	}
	
	@Test
	public void testDelete() {
		Integer rolid = 10;
		repo.deleteById(rolid);
		Optional<Role> optionalRol = repo.findById(rolid);
		Assertions.assertThat(optionalRol).isNotPresent();
	}
}
