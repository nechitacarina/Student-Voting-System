package com.vote.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vote.group.Group;
import com.vote.group.GroupService;
import com.vote.user.User;
import com.vote.user.UserService;


@Controller
public class StudentController {
	
	@Autowired StudentService studentService;
	
	@Autowired private UserService userService;
	
	@Autowired private GroupService groupService;
	
	@GetMapping("/students")
	public String showStudentsList(Model model, @Param("keyword") String keyword) {
		return listByPage(model, 1, keyword);
	}
	
	@GetMapping("/students/page/{pageNumber}")
	public String listByPage(Model model, @PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
		
		Page<Student> page = studentService.listAll(currentPage, keyword);
		long totalStudents = page.getTotalElements();
		int totalPages = page.getTotalPages();
		List<Student> StudentsList = page.getContent();
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalStudents", totalStudents);
		model.addAttribute("totalPages", totalPages);
		model.addAttribute("StudentsList", StudentsList);
		model.addAttribute("keyword", keyword);
		return "students";
	}
	
	@GetMapping("/students/new")
	public String showStudentsNewForm(Model model) {
		List<User> UsersList = userService.listAll();
		List<Group> GroupsList = groupService.listAll();
		model.addAttribute("student", new Student());
		model.addAttribute("UsersList", UsersList);
		model.addAttribute("GroupsList", GroupsList);
		model.addAttribute("pageTitle", "Adauga Student");
		return "student_form";
	}
	
	@PostMapping("/students/save")
	public String saveStudent(Student student, RedirectAttributes ra) {
		studentService.save(student);
		ra.addFlashAttribute("message", "Studentul a fost salvat cu succes!");
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id_student}")
	public String showStudentEditForm(@PathVariable("id_student") Integer id_student, Model model, RedirectAttributes ra) {
			Student student = studentService.get(id_student);
			model.addAttribute("student", student);
			model.addAttribute("pageTitle", "Modificare student (ID: " + id_student + ")");
			List<User> UsersList = userService.listAll();
			List<Group> GroupsList = groupService.listAll();
			model.addAttribute("UsersList", UsersList);
			model.addAttribute("GroupsList", GroupsList);
			return "student_form";
	}
	
	@GetMapping("/students/delete/{id_student}")
	public String deleteStudent(@PathVariable("id_student") Integer id_student, RedirectAttributes ra) {
		studentService.delete(id_student);
		ra.addFlashAttribute("message","Studentul a fost sters cu succes!");
		return "redirect:/students";
	}

	@RequestMapping("/studentCNPAutocomplete")
	@ResponseBody
	public List<String> studentCNPAutocomplete(@RequestParam(value="term", required = false, defaultValue="") String term){
		List<String> suggestions = userService.getCNPSuggestion(term);
		return suggestions;
	}
}
