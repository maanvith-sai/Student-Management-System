package com.studentManagement.controller.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.repository.semester.FirstSemesterInterface;
import com.studentManagement.service.excelService.FirstExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/first")
public class FirstSemesterController
{
    @Autowired
    FirstSemesterInterface repo;
    private final FirstExcelFileService excelFileService;

    public FirstSemesterController(FirstExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

//    @GetMapping("/form")
    public String showUploadForm()
    {
        return "upload";
    }

//    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model)
    {
        try
        {
            List<first> entities = excelFileService.processExcelFile(file);
            model.addAttribute("entities", entities);
            model.addAttribute("message", "File uploaded and data saved successfully!");
        } catch (IOException e)
        {
            e.printStackTrace();
            model.addAttribute("error", "Error processing the file.");
        }
        return "uploadResult";
    }

//    @GetMapping("/all")
    public String method1(Model model) {
        List<Student> results = repo.findAllUsersWithAddressfirst();
        model.addAttribute("first", results);
        return "firstMarks";
    }

//    @GetMapping("/getdetails/{value}")
    public String method2(@PathVariable String value,Model model)
    {
        List<first> std2=repo.findUsersWithId(value);
        model.addAttribute("entities",std2);
        return "uploadResult";
    }
}


