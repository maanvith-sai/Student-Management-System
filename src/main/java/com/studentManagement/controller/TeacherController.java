package com.studentManagement.controller;

import com.studentManagement.controller.semester.*;
import com.studentManagement.entity.Student;
import com.studentManagement.entity.Teacher;
import com.studentManagement.entity.User;
import com.studentManagement.entity.semester.*;
import com.studentManagement.repository.TeacherRepository;
import com.studentManagement.repository.UserRepo;
import com.studentManagement.repository.semester.FirstSemesterInterface;
import com.studentManagement.service.excelService.FirstExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FirstSemesterController first;

    @Autowired
    private SecondSemesterController second;

    @Autowired
    private ThirdSemesterController third;

    @Autowired
    private FourthSemesterController fourth;

    @Autowired
    private FifthSemesterController fifth;

    @Autowired
    private SixthSemesterController sixth;

    @Autowired
    private SeventhSemesterController seventh;

    @Autowired
    private EighthSemesterController eighth;

    @Autowired
    TeacherRepository teacherRepository;

    User user;




    @ModelAttribute
    public void commonUser(Principal p, Model m)
    {
        if (p != null) {
            String email = p.getName();
            user = userRepo.findByUserId(email);
            m.addAttribute("user", user);
        }
    }
    @GetMapping("/profile")
    public String profile(Model m) throws Exception
    {
        Teacher t = teacherRepository.findByTeacherId(user.getUserId());
        m.addAttribute("teacher", t);

//            if (t.getImage() != null) {
//                byte[] imageBytes = t.getImage().getBytes(1, (int) t.getImage().length());
//                System.out.println(imageBytes.length);
//                m.addAttribute("teacherImage", ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes));
//            }
        return "teacher_profile";
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> displayImage(@RequestParam("id") String id) throws IOException, SQLException
    {
        Teacher t = teacherRepository.findById(id).get();
        byte [] imageBytes = t.getImage().getBytes(1,(int) t.getImage().length());
        System.out.println(imageBytes.length);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
    }




    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




    @GetMapping("/uploadFirst/form")
    public String firstshowUploadForm()
    {

        return first.showUploadForm();
    }
    @PostMapping("/uploadFirst/upload")
    public String firsthandleFileUpload(@RequestParam("file") MultipartFile file, Model model)
    {

        return first.handleFileUpload(file, model);
    }
    @GetMapping("/uploadFirst/all")
    public String firstmethod1(Model model) {

        return first.method1(model);
    }
    @GetMapping("/uploadFirst/getdetails/{value}")
    public String firstmethod1(@PathVariable String value,Model model)
    {
        return first.method2(value, model);
    }



//
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


    @GetMapping("/uploadSecond/form")
    public String secondshowUploadForm()
    {
        return "upload1";
    }
    @PostMapping("/uploadSecond/upload")
    public String secondhandleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        return second.handleFileUpload(file, model);
    }
    @GetMapping("/uploadSecond/getdetails/{value}")
    public String secondmethod1(@PathVariable String value, Model model)
    {
        return second.method1(value, model);
    }



    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



    @GetMapping("/uploadThird/form")
    public String thirdshowUploadForm() {
        return third.showUploadForm();
    }

    @PostMapping("/uploadThird/upload")
    public String thirdhandleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        return third.handleFileUpload(file,model);
    }
    @GetMapping("/uploadThird/getdetails/{value}")
    public String thirdmethod1(@PathVariable String value, Model model)
    {
        return third.method1(value,model);
    }


    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




    @GetMapping("/uploadFourth/form")
    public String fourthshowUploadForm()
    {
        return fourth.showUploadForm();
    }

    @PostMapping("/uploadFourth/upload")
    public String fourthhandleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        return fourth.handleFileUpload(file,model);
    }

    @GetMapping("/uploadFourth/getdetails/{value}")
    public String fourthmethod1(@PathVariable String value,Model model)
    {
        return fourth.method1(value, model);
    }



    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------






    @GetMapping("/uploadFifth/form")
    public String fifthshowUploadForm()
    {
        return fifth.showUploadForm();
    }

    @PostMapping("/uploadFifth/upload")
    public String fifthhandleFileUpload(@RequestParam("file") MultipartFile file, Model model)
    {
        return fifth.handleFileUpload(file,model);
    }

    @GetMapping("/uploadFifth/getdetails/{value}")
    public String fifthmethod1(@PathVariable String value, Model model)
    {
        return fifth.method1(value, model);
    }




    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------



    @GetMapping("/uploadSixth/form")
    public String sixthshowUploadForm() {
        return sixth.showUploadForm();
    }

    @PostMapping("/uploadSixth/upload")
    public String sixthhandleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        return sixth.handleFileUpload(file, model);
    }

    @GetMapping("/uploadSixth/getdetails/{value}")
    public String sixthmethod1(@PathVariable String value, Model model)
    {
        return sixth.method1(value, model);
    }





    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




    @GetMapping("/uploadSeventh/form")
    public String seventhshowUploadForm() {
        return seventh.showUploadForm();
    }

    @PostMapping("/uploadSeventh/upload")
    public String seventhhandleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        return seventh.handleFileUpload(file,model);
    }
    @GetMapping("/uploadSeventh/getdetails/{value}")
    public String seventhmethod1(@PathVariable String value, Model model)
    {
        return seventh.method1(value, model);
    }




    //
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------




    @GetMapping("/uploadEighth/form")
    public String eighthshowUploadForm()
    {
        return eighth.showUploadForm();
    }

    @PostMapping("/uploadEighth/upload")
    public String eighthhandleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
            return eighth.handleFileUpload(file,model);
    }

    @GetMapping("/uploadEighth/getdetails/{value}")
    public String eighthmethod1(@PathVariable String value,Model model)
    {
        return eighth.method1(value, model);
    }




}
