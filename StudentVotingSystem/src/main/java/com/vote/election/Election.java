package com.vote.election;

import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.vote.group.Group;
import com.vote.option.Option;
import com.vote.vote.Vote;

@Entity
@Table(name = "chestionar")
public class Election {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id_chestionar;
	
	@Size(min = 3, max = 50)
	String denumire;
	
	@Min(value=1,  message="Introduceti 1 sau 2")
	@Max(value=2,  message="Introduceti 1 sau 2")
	Integer nr_max_optiuni;

	Date data_start;
	
	String ora_start;
	
	Date data_final;
	
	String ora_final;
	
	String status = "in desfasurare";
	

	@ManyToMany(cascade = CascadeType.PERSIST) //The CascadeType will allow to delete the election but not the group and vice versa
	@JoinTable(
			name = "grup_chestionar",
			joinColumns = @JoinColumn(name = "id_chestionar"),
			inverseJoinColumns = @JoinColumn(name = "id_grup")
			)
	private Set<Group> groups = new HashSet<>();

	
	@OneToMany(mappedBy = "election", cascade = CascadeType.PERSIST, fetch=FetchType.LAZY)
	private Set<Vote> vote = new HashSet<>();
	
	@OneToMany(mappedBy = "election", cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	private Set<Option> options = new HashSet<>();
	
	public Integer getId_chestionar() {
		return id_chestionar;
	}

	public void setId_chestionar(Integer id_chestionar) {
		this.id_chestionar = id_chestionar;
	}

	public String getDenumire() {
		return denumire;
	}

	public void setDenumire(String denumire) {
		this.denumire = denumire;
	}

	public Integer getNr_max_optiuni() {
		return nr_max_optiuni;
	}

	public void setNr_max_optiuni(Integer nr_max_optiuni) {
		this.nr_max_optiuni = nr_max_optiuni;
	}

	public Date getData_start() {
		return data_start;
	}

	public void setData_start(Date data_start) {
		this.data_start = data_start;
	}

	public String getOra_start() {
		return ora_start;
	}

	public void setOra_start(String ora_start) {
		this.ora_start = ora_start;
	}

	public Date getData_final() {
		return data_final;
	}

	public void setData_final(Date data_final) {
		this.data_final = data_final;
	}

	public String getOra_final() {
		return ora_final;
	}

	public void setOra_final(String ora_final) {
		this.ora_final = ora_final;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Vote> getVote() {
		return vote;
	}

	public void setVote(Set<Vote> vote) {
		this.vote = vote;
	}

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}
	

}
