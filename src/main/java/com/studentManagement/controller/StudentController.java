package com.studentManagement.controller;

import java.io.IOException;
import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.*;
import com.studentManagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.studentManagement.entity.User;
import com.studentManagement.repository.UserRepo;
import org.springframework.web.multipart.MultipartFile;

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


	@RequestMapping("/getFirstSemester")
	public String getFirstSemester(Model model)
	{
		try
		{
			List<first> entities = studentRepository.getFirstSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "First Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	@RequestMapping("/getSecondSemester")
	public String getSecondSemester(Model model)
	{
		try
		{
			List<second> entities = studentRepository.getSecondSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Second Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	@RequestMapping("/getThirdSemester")
	public String getThirdSemester(Model model)
	{
		try
		{
			List<third> entities = studentRepository.getThirdSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Third Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	@RequestMapping("/getFourthSemester")
	public String getFourthSemester(Model model)
	{
		try
		{
			List<fourth> entities = studentRepository.getFourthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Fourth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	@RequestMapping("/getFifthSemester")
	public String getFifthSemester(Model model)
	{
		try
		{
			List<fifth> entities = studentRepository.getFifthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Fifth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	@RequestMapping("/getSixthSemester")
	public String getSixthSemester(Model model)
	{
		try
		{
			List<sixth> entities = studentRepository.getSixthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Sixth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	@RequestMapping("/getSeventhSemester")
	public String getSeventhSemester(Model model)
	{
		try
		{
			List<seventh> entities = studentRepository.getSeventhSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Seventh Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");

		}
		return "viewMarks";
	}

	@RequestMapping("/getEighthSemester")
	public String getEighthSemester(Model model)
	{
		try
		{
			List<eighth> entities = studentRepository.getEighthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Eighth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewMarks";
	}

	//===========================================================================================================================================================
	//======================================================================================================================================================================

	@RequestMapping("/getFirstSemesterAttendance")
	public String getFirstSemesterAttendance(Model model)
	{
		try
		{
			List<first> entities = studentRepository.getFirstSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "First Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}

	@RequestMapping("/getSecondSemesterAttendance")
	public String getSecondSemesterAttendance(Model model)
	{
		try
		{
			List<second> entities = studentRepository.getSecondSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Second Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}

	@RequestMapping("/getThirdSemesterAttendance")
	public String getThirdSemesterAttendance(Model model)
	{
		try
		{
			List<third> entities = studentRepository.getThirdSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Third Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}

	@RequestMapping("/getFourthSemesterAttendance")
	public String getFourthSemesterAttendance(Model model)
	{
		try
		{
			List<fourth> entities = studentRepository.getFourthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Fourth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}

	@RequestMapping("/getFifthSemesterAttendance")
	public String getFifthSemesterAttendance(Model model)
	{
		try
		{
			List<fifth> entities = studentRepository.getFifthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Fifth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}

	@RequestMapping("/getSixthSemesterAttendance")
	public String getSixthSemesterAttendance(Model model)
	{
		try
		{
			List<sixth> entities = studentRepository.getSixthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Sixth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}

	@RequestMapping("/getSeventhSemesterAttendance")
	public String getSeventhSemesterAttendance(Model model)
	{
		try
		{
			List<seventh> entities = studentRepository.getSeventhSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Seventh Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");

		}
		return "viewAttendance";
	}

	@RequestMapping("/getEighthSemesterAttendance")
	public String getEighthSemesterAttendance(Model model)
	{
		try
		{
			List<eighth> entities = studentRepository.getEighthSemester(user.getUserId());
			model.addAttribute("entities", entities);
			model.addAttribute("semester", "Eighth Semester");
		} catch (Exception e)
		{
			e.printStackTrace();
			model.addAttribute("error", "Error processing the file.");
		}
		return "viewAttendance";
	}


}
