package com.vote.vote;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vote.election.ElectionRepository;
import com.vote.option.OptionRepository;

import com.vote.user.UserRepository;
import com.vote.user.UserService;

@Service
public class VoteService {

	@Autowired VoteRepository voteRepo;
	
	@Autowired OptionRepository optionRepo;
	
	@Autowired ElectionRepository electionRepo;
	
	@Autowired UserService userService;
	
	@Autowired UserRepository userRepo;
	
	public void save(Vote vote) {
		voteRepo.save(vote);
	}
	
	public Vote get(Integer id_vot){
		Optional<Vote> result = voteRepo.findById(id_vot);
		return result.get();
	}

}
