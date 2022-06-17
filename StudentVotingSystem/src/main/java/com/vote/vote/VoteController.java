package com.vote.vote;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vote.election.Election;
import com.vote.election.ElectionRepository;
import com.vote.election.ElectionService;
import com.vote.option.Option;
import com.vote.option.OptionService;
import com.vote.user.User;
import com.vote.user.UserRepository;

@Controller
public class VoteController {
	
	@Autowired
	private ElectionService electionService;
	
	@Autowired
	private ElectionRepository electionRepo;
	
	@Autowired
	private OptionService optionService;
	
	@Autowired
	private VoteService voteService;
	
	@Autowired
	private VoteRepository voteRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/velectionsList")
	public String  findElectionByStudent(Model model) {
		List<Election> ElectionsList = electionService.findElectionByStudent();
		model.addAttribute("ElectionsList", ElectionsList);
		return "velectionsList";
	}

	@GetMapping("/voteFor/{id_chestionar}")
	public String showVotingForm(@PathVariable("id_chestionar") Integer id_chestionar, Model model,Vote vote,Election election) {		
		electionRepo.findById(id_chestionar).get();
		
		List<Option> OptionsList = optionService.findOptionByElection(id_chestionar);
		model.addAttribute("OptionsList", OptionsList);
		
		return "voteFor";
	
	}
	
	@PostMapping("/voteFor/save")
	public String voteFor(Vote vote, @RequestParam("id_chestionar") Integer id_chestionar,  @RequestParam("options") Integer[] id_optiune,  RedirectAttributes ra){
		Authentication loggedInUser  = SecurityContextHolder.getContext().getAuthentication();
		String cnp = loggedInUser.getName();
		User user = userRepo.findById(cnp).get();
		vote.setUser(user);
		Election election = electionRepo.findById(id_chestionar).get();
		vote.setElection(election);
		Optional<Vote> result = voteRepo.findByElectionAndUser(election, user);

		if(result.isPresent()) {
			ra.addFlashAttribute("message_error", "Ai votat deja pentru acest chestionar!");
			return "redirect:/velectionsList";
		}
		else
		{
			if(id_optiune!=null) {
				for(Integer id_opt: id_optiune) {
					Option option = optionService.get(id_opt);
					option.setNr_voturi_atrase(option.getNr_voturi_atrase()+1);
					optionService.save(option);
				}
			}
			voteService.save(vote);
			ra.addFlashAttribute("message_success", "Votul tau a fost inregistrat! Multumim!");
			return "redirect:/velectionsList";
		}
		
	}
}
	