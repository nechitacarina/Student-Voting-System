package com.vote.group;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GroupController {
	
	@Autowired GroupService groupService;
	
	@GetMapping("/groups")
	public String showGroupsList(Model model) {
		return listByPage(model, 1);
	}
	
	@GetMapping("/groups/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage) {
		Page<Group> page = groupService.listAll(currentPage);
		long totalGroups = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Group> GroupsList = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalGroups", totalGroups);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("GroupsList", GroupsList);
		return "groups";
	}
	
	@GetMapping("/groups/new")
	public String showGroupsNewForm(Model model) {
		model.addAttribute("group", new Group());
		model.addAttribute("pageTitle", "Add New Group");
		return "group_form";
	}
	
	@PostMapping("/groups/save")
	public String saveGroup(Group group, RedirectAttributes ra) {
		groupService.save(group);
		ra.addFlashAttribute("message", "The group has been saved succesfully.");
		return "redirect:/groups";
	}
	
	@GetMapping("/groups/edit/{id_grup}")
	public String showGroupEditForm(@PathVariable("id_grup") Integer id_grup, Model model, RedirectAttributes ra) {
			Group group = groupService.get(id_grup);
			model.addAttribute("group", group);
			model.addAttribute("pageTitle", "Edit Group (ID: " + id_grup + ")");
			return "group_form";
	}
	
	@GetMapping("/groups/delete/{id_grup}")
	public String deleteGroup(@PathVariable("id_grup") Integer id_grup, RedirectAttributes ra) {
		groupService.delete(id_grup);
		ra.addFlashAttribute("message","The group has been deleted");
		return "redirect:/groups";
	}

}
