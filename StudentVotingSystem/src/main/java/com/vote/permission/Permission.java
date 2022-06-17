package com.vote.permission;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="permisiune")
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id_permisiune;
	
	@Size(min = 3, max = 50)
	String descriere;

	public Permission() {}
	
	public Permission(String descriere) {
		this.descriere = descriere;
	}

	public Integer getId_permisiune() {
		return id_permisiune;
	}


	public void setId_permisiune(Integer id_permisiune) {
		this.id_permisiune = id_permisiune;
	}


	public String getDescriere() {
		return descriere;
	}


	public void setDescriere(String descriere) {
		this.descriere = descriere;
	}


	@Override
	public String toString() {
		return "" + descriere + "";
	}	
}
