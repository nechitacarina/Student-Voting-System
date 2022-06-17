package com.vote.option;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vote.election.Election;
import com.vote.election.ElectionService;

@Controller
public class OptionController {

	@Autowired OptionService optionService;
	
	@Autowired private ElectionService electionService;
	
	@GetMapping("/options")
	public String showOptionsList(Model model, @Param("keyword") String keyword) {
		return listByPage(model, 1, keyword);
	}
	
	@GetMapping("/options/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
		Page<Option> page = optionService.listAll(currentPage, keyword);
		long totalOptions = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Option> OptionsList = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalOptions", totalOptions);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("OptionsList", OptionsList);
		model.addAttribute("keyword", keyword);
		return "options";
	}
	
	@GetMapping("/options/new")
	public String showOptionsNewForm(Model model) {
		List<Election> ElectionsList = electionService.listAll();
		model.addAttribute("option", new Option());
		model.addAttribute("ElectionsList", ElectionsList);
		model.addAttribute("pageTitle", "Adauga optiune");
		return "option_form";
	}
	
	@PostMapping("/options/save")
	public String saveOption(@Valid Option option, BindingResult bindingResult, RedirectAttributes ra, Model model) {
		if(bindingResult.hasErrors()) {
			List<Election> ElectionsList = electionService.listAll();
			model.addAttribute("ElectionsList", ElectionsList);
			return "option_form";
		}
		else {
		optionService.save(option);
		ra.addFlashAttribute("message", "Optiunea a fost salvata cu succes!");
		return "redirect:/options";
		}
	}
	
	@GetMapping("/options/edit/{id_optiune}")
	public String showOptionEditForm(@PathVariable("id_optiune") Integer id_optiune, Model model, RedirectAttributes ra) {
			Option option = optionService.get(id_optiune);
			model.addAttribute("option", option);
			model.addAttribute("pageTitle", "Modificare optiune (ID: " + id_optiune + ")");
			List<Election> ElectionsList = electionService.listAll();
			model.addAttribute("ElectionsList", ElectionsList);
			return "option_form";
	}
	
	@GetMapping("/options/delete/{id_optiune}")
	public String deleteOption(@PathVariable("id_optiune") Integer id_optiune, RedirectAttributes ra) {
		optionService.delete(id_optiune);
		ra.addFlashAttribute("message","Optiunea a fost stearsa cu succes!");
		return "redirect:/options";
	}
}
