package com.vote.election;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vote.group.Group;
import com.vote.group.GroupService;

@Controller
public class ElectionController {

	@Autowired
	private ElectionService electionService;
	
	@Autowired private GroupService groupService;
	
	@GetMapping("/elections")
	public String showElectionList(Model model, @Param("keyword") String keyword) {
		return listByPage(model, 1, keyword);
	}
	
	@GetMapping("/elections/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
		Page<Election> page = electionService.listAll(currentPage, keyword);
		long totalElections = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Election> ElectionsList = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalElections", totalElections);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("ElectionsList", ElectionsList);
		model.addAttribute("keyword", keyword);
		return "elections";
	}
	
	@GetMapping("/elections/new")
	public String showNewElectionForm(Model model) {
		List<Group> GroupsList = groupService.listAll();
		model.addAttribute("GroupsList", GroupsList);
		model.addAttribute("election", new Election());
		model.addAttribute("pageTitle", "Add New Election");
		return "election_form";
	}
	
	@PostMapping("/elections/save")
	public String saveElection(Election election, RedirectAttributes ra) {
		electionService.save(election);
		ra.addFlashAttribute("message", "The election has been saved succesfully.");
		return "redirect:/elections";
	}
	
	@GetMapping("/elections/edit/{id_chestionar}")
	public String showElectionEditForm(@PathVariable("id_chestionar") Integer id_chestionar, Model model, RedirectAttributes ra) {
		try {	
			List<Group> GroupsList = groupService.listAll();
			model.addAttribute("GroupsList", GroupsList);
			Election election = electionService.get(id_chestionar);
			model.addAttribute("election", election);
			model.addAttribute("pageTitle", "Edit Election (ID: " + id_chestionar + ")");
			return "election_form";
		}
		catch (ElectionNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/elections";
		}	
	}
	
	@GetMapping("/elections/delete/{id_chestionar}")
	public String deleteElection(@PathVariable("id_chestionar") Integer id_chestionar, RedirectAttributes ra) {
		electionService.delete(id_chestionar);
		ra.addFlashAttribute("message","The election has been deleted");
		return "redirect:/elections";
	}
}
