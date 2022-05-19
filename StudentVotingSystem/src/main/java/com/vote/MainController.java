package com.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/system_admin_home")
	public String showAdminSystemHomePage() {
		return "system_admin_home";
	}
	@GetMapping("/voting_admin_home")
	public String showVotingAdminHomePage() {
		return "voting_admin_home";
	}
	@GetMapping("/voter_home")
	public String showVoterHomePage() {
		return "voter_home";
	}
}
