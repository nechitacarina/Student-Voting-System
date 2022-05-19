package com.vote.student;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.vote.group.Group;
import com.vote.user.User;

@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id_student;
	
	@OneToOne
	@JoinColumn(name = "CNP")
	private User user;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER) //The CascadeType will allow to delete the student but not the group and vice versa; The FetchType will load the entity with the rest of the fields not on demand
	@JoinTable( 
	        name = "grup_student", 
	        joinColumns = @JoinColumn(name = "id_student"), 
	        inverseJoinColumns = @JoinColumn(name = "id_grup")
	        )
	private Set<Group> groups = new HashSet<>();


	public Integer getId_student() {
		return id_student;
	}


	public void setId_student(Integer id_student) {
		this.id_student = id_student;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Set<Group> getGroups() {
		return groups;
	}


	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
