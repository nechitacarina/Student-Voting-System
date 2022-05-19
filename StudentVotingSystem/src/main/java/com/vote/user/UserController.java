package com.vote.user;

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

import com.vote.role.Role;
import com.vote.role.RoleService;


@Controller
public class UserController {

	@Autowired UserService userService;
	
	@Autowired private RoleService rolService;
	
	@GetMapping("/users")
	public String showUserList(Model model, @Param("keyword") String keyword) {
		return listByPage(model, 1, keyword);
	}
	
	@GetMapping("/users/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
		Page<User> page = userService.listAll(currentPage, keyword);
		long totalUsers = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<User> UsersList = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalUsers", totalUsers);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("UsersList", UsersList);
		model.addAttribute("keyword", keyword);
		return "users";
	}
	
	@GetMapping("/users/new")
	public String showNewForm(Model model) {
		List<Role> listRoles = rolService.listAll();
		model.addAttribute("user", new User());
		model.addAttribute("listRoles", listRoles);
		model.addAttribute("pageTitle", "Add New User");
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes ra) {
		userService.save(user);
		ra.addFlashAttribute("message", "The user has been saved succesfully.");
		return "redirect:/users";
	}
	
	@GetMapping("/users/edit/{CNP}")
	public String showEditForm(@PathVariable("CNP") String CNP, Model model, RedirectAttributes ra) {
			User user = userService.get(CNP);
			model.addAttribute("user", user);
			model.addAttribute("pageTitle", "Edit User (CNP: " + CNP + ")");
			List<Role> listRoles = rolService.listAll();
			model.addAttribute("listRoles", listRoles);
			return "user_form";
	}
	
	@GetMapping("/users/delete/{CNP}")
	public String deleteRole(@PathVariable("CNP") String CNP, RedirectAttributes ra) {
		userService.delete(CNP);
		ra.addFlashAttribute("message","The user has been deleted");
		return "redirect:/users";
	}
	
	@GetMapping("/403")
	public String error403() {
		return "403";
	}
}
