package com.vote.permission;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PermissionController {

	@Autowired private PermissionService service;
	
	@GetMapping("/permissions")
	public String showPermissionList(Model model) {
		List<Permission> listPermissions = service.listAll();
		model.addAttribute("listPermissions", listPermissions);
		return "permissions";
	}
	
	@GetMapping("/permissions/new")
	public String showNewForm(Model model) {
		model.addAttribute("permission", new Permission());
		model.addAttribute("pageTitle", "Adauga Permisiune");
		return "permission_form";
	}
	
	@PostMapping("/permissions/save")
	public String savePermission(@Valid Permission permission, BindingResult bindingResult, RedirectAttributes ra, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", "Permisiune");
			return "permission_form";
		}
		else {
		service.save(permission);
		ra.addFlashAttribute("message", "Permisiunea a fost salvata cu succes!");
		return "redirect:/permissions";}
	}
	
	@GetMapping("/permissions/edit/{id_permisiune}")
	public String showEditForm(@PathVariable("id_permisiune") Integer id_permisiune, Model model, RedirectAttributes ra) {
		try {
			Permission permission = service.get(id_permisiune);
			model.addAttribute("permission", permission);
			model.addAttribute("pageTitle", "Modificare permisiune (ID: " + id_permisiune + ")");
			return "permission_form";
		} catch (PermissionNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/permissions";
		}
	}
	
	@GetMapping("/permissions/delete/{id_permisiune}")
	public String deletePermission(@PathVariable("id_permisiune") Integer id_permisiune, RedirectAttributes ra) {
		service.delete(id_permisiune);
		ra.addFlashAttribute("message","Permisiunea a fost stearsa cu succes!");
		return "redirect:/permissions";
	}
}
