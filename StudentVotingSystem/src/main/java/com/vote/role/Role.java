package com.vote.role;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.vote.permission.Permission;

@Entity 
@Table(name="rol")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_rol;
	
	@Size(min = 3, max = 50)
	private String denumire;

	public Role() {}
	
	public Role(String denumire) {
		this.denumire = denumire;
	}

	@ManyToMany(cascade = CascadeType.PERSIST) //The CascadeType will allow to delete the permission but not the role and vice versa
	@JoinTable(
			name = "permisiune_rol",
			joinColumns = @JoinColumn(name = "id_permisiune"),
			inverseJoinColumns = @JoinColumn(name = "id_rol")
			)
	private Set<Permission> permissions = new HashSet<>();
	
	public Integer getId_rol() {
		return id_rol;
	}


	public void setId_rol(Integer id_rol) {
		this.id_rol = id_rol;
	}


	public String getDenumire() {
		return denumire;
	}


	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}


	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	
	@Override
	public String toString() {
		return "" + denumire + "";
	}
	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}
	
}
