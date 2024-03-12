package com.studentManagement.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;

import com.studentManagement.entity.Student;
import com.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studentManagement.entity.User;
import com.studentManagement.repository.UserRepo;

@Controller
@RequestMapping("/user")
public class StudentController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private StudentRepository studentRepository;

	private User user;

	@ModelAttribute
	public void commonUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			user = userRepo.findByUserId(email);
			m.addAttribute("user", user);
		}
	}
	@GetMapping("/profile")
	public String profile(Model m)
	{
		Student s = studentRepository.findByUserId(user.getUserId());
		m.addAttribute("student", s);
		System.out.println(s.getFirstName());
		return "user_profile";
	}
	@GetMapping("/display")
	public ResponseEntity<byte[]> displayImage(@RequestParam("id") String id) throws IOException, SQLException
	{
		Student s = studentRepository.findById(id).get();
		byte [] imageBytes = s.getImage().getBytes(1,(int) s.getImage().length());
		System.out.println(imageBytes.length);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
	}

}
