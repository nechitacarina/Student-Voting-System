package com.vote.option;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vote.election.Election;

@Entity
@Table(name="optiune")
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //primary key auto-increment
	Integer id_optiune;
	
	String denumire;
	Integer nr_voturi_atrase = 0;
	
	@ManyToOne //One election can have many options
	@JoinColumn(name = "id_chestionar") 
	private Election election;

	public boolean forElection(String electionName) {
		
		if(election.getDenumire().equals(electionName)) {
			return true;
		}
		return false;
	}
	
	
	public Integer getId_chestionar() {
		return election.getId_chestionar();
	}
	
	public Integer getId_optiune() {
		return id_optiune;
	}

	public void setId_optiune(Integer id_optiune) {
		this.id_optiune = id_optiune;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public Integer getNr_voturi_atrase() {
		return nr_voturi_atrase;
	}

	public void setNr_voturi_atrase(Integer nr_voturi_atrase) {
		this.nr_voturi_atrase = nr_voturi_atrase;
	}

	public Election getElection() {
		return election;
	}

	public void setElection(Election election) {
		this.election = election;
	}	
}
