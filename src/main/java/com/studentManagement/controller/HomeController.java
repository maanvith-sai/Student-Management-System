package com.studentManagement.controller;
import java.security.Principal;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.TeacherRepository;
import com.studentManagement.service.StudentService;
import com.studentManagement.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.studentManagement.entity.User;
import com.studentManagement.repository.UserRepo;
import com.studentManagement.service.UserService;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentRepository studentRepository;

	User user;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			user = userRepo.findByUserId(email);
			m.addAttribute("user", user);
		}
	}

	@GetMapping("/")
	public String index()
	{
		if(user!=null && user.getRole().equals("ROLE_ADMIN"))
		{
			return "redirect:/admin/profile";
		}
		else if(user!=null && user.getRole().equals("ROLE_USER"))
		{
			return "redirect:/user/profile";
		}
		return "index";
	}
	@GetMapping("/signin")
	public String login()
	{
		return "login";
	}

}
