package com.vote.vote;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vote.election.Election;
import com.vote.user.User;


public interface VoteRepository extends CrudRepository<Vote, Integer>{

	Optional<Vote> findByElectionAndUser(Election election, User user);
	
}
