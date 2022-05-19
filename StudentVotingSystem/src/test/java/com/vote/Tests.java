package com.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.vote.permission.Permission;
import com.vote.permission.PermissionRepository;
import com.vote.role.Role;
import com.vote.role.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class Tests {

	@Autowired private RoleRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testCreatePermission() {
		Permission createElection = new Permission("Create Election");
		Permission manageUser = new Permission("Manage User");
		Permission manageVoter = new Permission("Manage Voter");
		entityManager.persist(createElection);
		entityManager.persist(manageUser);
		entityManager.persist(manageVoter);
	}
	@Test
	public void testCreateNewRoleWithOnePermission() {
		Permission createElection = entityManager.find(Permission.class, 2);
		Role role = new Role("admin alegeri");
		role.addPermission(createElection);
		repo.save(role);
	}
	
	@Test
	public void testCreateNewRoleWithTwoPermissions() {
		Permission manageVoter = entityManager.find(Permission.class, 3);
		Permission manageUser = entityManager.find(Permission.class, 4);
		Role role = new Role("admin");
		role.addPermission(manageUser);
		role.addPermission(manageVoter);
		repo.save(role);
	}
	
}
