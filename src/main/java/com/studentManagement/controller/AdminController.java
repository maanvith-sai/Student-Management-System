package com.studentManagement.controller;
import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.Teacher;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.TeacherRepository;
import com.studentManagement.service.StudentService;
import com.studentManagement.service.TeacherService;
import com.studentManagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.studentManagement.entity.User;
import com.studentManagement.repository.UserRepo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.sql.rowset.serial.SerialException;

@Controller
@RequestMapping("admin")
public class AdminController
{

	@Autowired
	private UserService userService;
	@Autowired
	private TeacherService teacherService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	@ModelAttribute
	public void commonUser(Principal p, Model m)
	{
		if (p != null) {
			String email = p.getName();
			User user = userRepo.findByUserId(email);
			m.addAttribute("user", user);
		}
	}

	@GetMapping("/profile")
	public String profile()
	{
		return "admin_profile";
	}


	//list teachers
	@GetMapping("/teachersList")
	public String listTeachers(Model model, Principal p)
	{
		model.addAttribute("teachers", teacherService.getAllTeachers());
		return "teachers";
	}
	//list student
	@GetMapping("/studentsList")
	public String listStudents(Model model, Principal p)
	{
		model.addAttribute("students", studentService.getAllStudents());
//		System.out.println(p.getName());
		return "students";
	}

//------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
	//Create USERS
	@GetMapping("/register")
	public String registerAdmin(Model model)
	{
		model.addAttribute("student" ,new Student());
		return "create_student";
	}

	@GetMapping("/register1")
	public String registerAdmin1(Model model)
	{
		model.addAttribute("teacher",new Teacher());
		return "create_teacher";
	}

	//==========================================================================================================================
	//==========================================================================================================================

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User user, HttpSession session, Model m, RedirectAttributes redirectAttributes) {
		User u = userService.saveUser(user);
		String userId = user.getUserId();

		if (u != null) {
			session.setAttribute("msg", "Register successfully");
		} else {
			session.setAttribute("msg", "Something wrong server");
		}

		if (u != null) {
			redirectAttributes.addAttribute("userId", userId);
			redirectAttributes.addFlashAttribute("message", "Register successfully");

			if (u.getRole().equals("ROLE_ADMIN")) {
//				return createTeacherForm(userId, m, session, redirectAttributes);
				return "redirect:/admin/teachers/new";
			} else if (u.getRole().equals("ROLE_USER")) {
//				return createStudentForm(userId, m, session, redirectAttributes);
				return "redirect:/admin/students/new";
			}
		}

		// Handle the case where user creation was not successful
		// You might want to add some error handling logic here.
		return "redirect:/admin/profile"; // Redirect to home page or another appropriate URL
	}

	//	save teacher
	@PostMapping("/saveTeacher")
	public String saveTeacher(@ModelAttribute("teacher") Teacher teacher,@RequestParam("imageFile") MultipartFile imageFile, Model m, Principal principal)
	{
		User use1 = new User();
		use1.setUserId(teacher.getTeacherId().toUpperCase());
		use1.setName(teacher.getName());
		use1.setRole("ROLE_TEACHER");
		use1.setPassword(new BCryptPasswordEncoder().encode(teacher.getPassword()));
		userRepo.save(use1);
		Teacher teach1=new Teacher();
		if (!imageFile.isEmpty()) {
			byte[] imageBytes = new byte[0];
			try {
				imageBytes = imageFile.getBytes();
				Blob blob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
				teach1.setImage(blob);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		teach1.setName(teacher.getName());
		teach1.setEmail(teacher.getEmail());
		teach1.setTeacherId(teacher.getTeacherId());
		teach1.setClassId(teacher.getClassId());
		teach1.setPassword(teacher.getPassword());
		teacherRepository.save((teach1));
		return listTeachers(m,principal);
	}
	//save student
	@PostMapping("/saveStudent")
	public String saveStudent(HttpServletRequest request, @ModelAttribute("student") Student student,
							  @RequestParam("imageFile") MultipartFile imageFile,
							  Model m, Principal principal)throws IOException, SerialException, SQLException
	{
		User use1 = new User();
		use1.setUserId(student.getId().toUpperCase());
		String str1=student.getFirstName();
		String str2=student.getLastName();
		use1.setName(str1+" "+str2);
		use1.setRole("ROLE_USER");
		use1.setPassword(new BCryptPasswordEncoder().encode(student.getPassword()));
		userRepo.save(use1);
		Student std=new Student();
		try{
			if (!imageFile.isEmpty()) {
				byte[] imageBytes = imageFile.getBytes();
				Blob blob = new javax.sql.rowset.serial.SerialBlob(imageBytes);
				std.setImage(blob);
			}
			std.setId(student.getId());
			std.setFirstName(student.getFirstName());
			std.setLastName(student.getLastName());
			std.setEmail(student.getEmail());
			std.setPassword(student.getPassword());
			std.setTeacherId(student.getTeacherId());
			studentRepository.save(std);
			return listStudents(m,principal);
		}
		catch(Exception e){
			System.out.println(e);
			return "errorr";
		}
	}

	//=================================================================================================================================
	//==========================================================================================================================================

	@GetMapping("/students/edit/{id}")
	public String editStudent(@PathVariable String id, Model model) {
		Student existingStudent = studentService.getStudentById(id);
		model.addAttribute("student", existingStudent);
		return "edit_student";
	}

	@GetMapping("/teachers/edit/{id}")
	public String editTeacher(@PathVariable String id, Model model)
	{
		Teacher existingTeacher = teacherService.getTeacherById(id);
		model.addAttribute("teacher", existingTeacher);
		return "edit_teacher";
	}

	//=======================================================================================================================================
	//==================================================================================================================================================
	//update student by ID
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable String id,
								@ModelAttribute("student") Student student,
								Model model) throws SQLException, IOException {
		// Get Student details from database
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(student.getId());
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		existingStudent.setId(student.getId());
		existingStudent.setTeacherId(student.getTeacherId());

		// save updated student object
		studentService.updateStudent(existingStudent);

		return "redirect:/admin/studentsList";
	}

	//	update teacher by ID
	@PostMapping("/teachers/{id}")
	public String updateStudent(@PathVariable String id,
								@ModelAttribute("teacher") Teacher teacher,
								Model model)
	{
		// Get Student details from database
		Teacher existingTeacher= teacherService.getTeacherById(id);
		existingTeacher.setName(teacher.getName());
		existingTeacher.setEmail(teacher.getEmail());
		existingTeacher.setClassId(teacher.getClassId());
		existingTeacher.setTeacherId(teacher.getTeacherId());
		// save updated student object
		teacherService.updateTeacher(existingTeacher);
		return "redirect:/admin/teachersList";
	}


	//=================================================================================================================================
	//========================================================================================================================================

	@GetMapping("/teachers/{id}")
	public String deleteTeacher(@PathVariable String id) {
		teacherService.deleteTeacher(id);
		userService.deleteByUserId(id);
		return "redirect:/admin/teachersList";
	}

	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable String id)
	{
		studentService.deleteStudent(id);
		userService.deleteByUserId(id);
		return "redirect:/admin/studentsList";
	}

}
