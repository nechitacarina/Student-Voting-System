package com.vote.role;

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

import com.vote.permission.Permission;
import com.vote.permission.PermissionService;

@Controller
public class RoleController {

	@Autowired private RoleService roleService;
	
	@Autowired private PermissionService permissionService;
	
	@GetMapping("/roles")
	public String showRolList(Model model) {
		List<Role> listRoles = roleService.listAll();
		model.addAttribute("listRoles", listRoles);
		return "roles";
	}
	
	@GetMapping("/roles/new")
	public String showNewForm(Model model) {
		List<Permission> listPermissions = permissionService.listAll();
		model.addAttribute("listPermissions", listPermissions);
		model.addAttribute("role", new Role());
		model.addAttribute("pageTitle", "Adauga un rol");
		return "role_form";
	}
	
	@PostMapping("/roles/save")
	public String saveRole(@Valid Role role,  BindingResult bindingResult, RedirectAttributes ra, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("pageTitle", "Formular rol");
			List<Permission> listPermissions = permissionService.listAll();
			model.addAttribute("listPermissions", listPermissions);
			return "role_form";
		}
		else {
			roleService.save(role);
			ra.addFlashAttribute("message", "Rolul a fost salvat cu succes!");
			return "redirect:/roles";
		}
	}
	
	@GetMapping("/roles/edit/{id_rol}")
	public String showEditForm(@PathVariable("id_rol") Integer id_rol, Model model, RedirectAttributes ra) {
		try {
			Role role = roleService.get(id_rol);
			model.addAttribute("role", role);
			List<Permission> listPermissions = permissionService.listAll();
			model.addAttribute("listPermissions", listPermissions);
			model.addAttribute("pageTitle", "Modificare Rol (ID: " + id_rol + ")");
			return "role_form";
		} catch (RoleNotFoundException e) {
			ra.addFlashAttribute("message", e.getMessage());
			return "redirect:/roles";
		}
	}
	
	@GetMapping("/roles/delete/{id_rol}")
	public String deleteRole(@PathVariable("id_rol") Integer id_rol, RedirectAttributes ra) {
		roleService.delete(id_rol);
		ra.addFlashAttribute("message","Rolul a fost sters cu succes!");
		return "redirect:/roles";
	}
}
