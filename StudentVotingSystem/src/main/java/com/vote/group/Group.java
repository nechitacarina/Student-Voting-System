package com.vote.group;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="grup")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id_grup;
	
	String facultate;
	String specializare;
	Integer an;
	Integer grupa;
	String universitate;
	
	public Integer getId_grup() {
		return id_grup;
	}
	public void setId_grup(Integer id_grup) {
		this.id_grup = id_grup;
	}
	public String getFacultate() {
		return facultate;
	}
	public void setFacultate(String facultate) {
		this.facultate = facultate;
	}
	public String getSpecializare() {
		return specializare;
	}
	public void setSpecializare(String specializare) {
		this.specializare = specializare;
	}
	public Integer getAn() {
		return an;
	}
	public void setAn(Integer an) {
		this.an = an;
	}
	public Integer getGrupa() {
		return grupa;
	}
	public void setGrupa(Integer grupa) {
		this.grupa = grupa;
	}
	public String getUniversitate() {
		return universitate;
	}
	public void setUniversitate(String universitate) {
		this.universitate = universitate;
	}
	@Override
	public String toString() {
		return " facultate: " + facultate + ", specializare: " + specializare + ", an: "
				+ an + ", grupa: " + grupa + ", universitate: " + universitate + "";
	}
	
}
