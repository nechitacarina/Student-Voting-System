package com.vote.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.vote.role.Role;
import com.vote.user.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private User user;
	
	public MyUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //returns a set of roles to be used by Spring Security in the authorization process
		Set<Role> roles = user.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for(Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getDenumire()));
			role.getPermissions().stream()
	         .map(p -> new SimpleGrantedAuthority(p.getDescriere()))
	         .forEach(authorities::add);
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getCNP();
	}
	
	public String getNume() {
		return user.getNume();
	}
	
	public String getPrenume() {
		return user.getPrenume();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isEnabled();
	}
	
	public boolean hasRole(String roleName) {
		return user.hasRole(roleName);
	}

	public User getUser() {
		return this.user;
	}

}
