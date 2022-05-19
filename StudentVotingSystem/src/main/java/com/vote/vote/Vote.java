package com.vote.vote;

import java.sql.Time;
import java.util.Date;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.vote.election.Election;
import com.vote.option.Option;
import com.vote.user.User;

@Entity
@Table(name = "vot")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //primary key auto-increment
	Integer id_vot;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_chestionar")
	private Election election;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_student")
	private User user;
	
	@Temporal(TemporalType.DATE)
	Date data = new Date();
	
	@Temporal(TemporalType.TIME)
	Date ora = new Date();
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinTable(
			name="vot_optiune",
			joinColumns = @JoinColumn(name = "id_vot"),
			inverseJoinColumns = @JoinColumn(name = "id_optiune")
			)
	private Set<Option> options = new HashSet<>();
	
	public Integer getId_chestionar() {
		return election.getId_chestionar();
	}

	public Integer getId_vot() {
		return id_vot;
	}
	public void setId_vot(Integer id_vot) {
		this.id_vot = id_vot;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getOra() {
		return (Time) ora;
	}
	public void setOra(Time ora) {
		this.ora = ora;
	}

	public void setOra(Date ora) {
		this.ora = ora;
	}
	public Election getElection() {
		return election;
	}
	public void setElection(Election election) {
		this.election = election;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer setElection(Integer id_chestionar) {
		return election.getId_chestionar();
		
	}


}