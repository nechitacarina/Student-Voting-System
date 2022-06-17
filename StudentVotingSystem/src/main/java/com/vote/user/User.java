package com.vote.user;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.vote.permission.Permission;
import com.vote.role.Role;

@Entity
@Table(name="utilizator")
public class User {

	@Id
	@Size(min=13, max = 13, message="CNP incorect!")
	private String CNP;
	
	@Size(min = 3, max = 50)
	private String nume;
	
	@Size(min = 3, max = 50)
	private String prenume;
	
	@Size(max = 10, min = 10, message = "Numarul de telefon trebuie sa aiba 10 cifre!")
    @Pattern(regexp = "[0-9][0-9]{9}", message = "Numar invalid!")
	private String telefon;
	
	@Email(message = "Introduceti o adresa de email valida!")
	private String email;
	
	private String password;
	
	private boolean enabled = true;
	
	
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) //The CascadeType will allow to delete the user but not the role and vice versa; The FetchType will load the entity with the rest of the fields not on demand
	@JoinTable( 
	        name = "utilizator_rol", 
	        joinColumns = @JoinColumn(name = "CNP"), 
	        inverseJoinColumns = @JoinColumn(name = "id_rol")
	        )
	private Set<Role> roles = new HashSet<>();
	
	public boolean hasRole(String roleName) { // check if the logged-in user has a specific role or not
		Iterator<Role> iterator = roles.iterator();
		while(iterator.hasNext()) {
			Role role = iterator.next();
			if(role.getDenumire().equals(roleName)) {
			return true;
			}
		}
		return false;
	}
	
	public String getCNP() {
		return CNP;
	}

	public void setCNP(String cNP) {
		CNP = cNP;
	}

	public String getNume() {
		return nume;
	}

	public void setNume(String nume) {
		this.nume = nume;
	}

	public String getPrenume() {
		return prenume;
	}

	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
}
