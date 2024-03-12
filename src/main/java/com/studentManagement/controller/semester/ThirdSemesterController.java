package com.studentManagement.controller.semester;


import com.studentManagement.entity.semester.second;
import com.studentManagement.entity.semester.third;
import com.studentManagement.repository.semester.SecondSemesterInterface;
import com.studentManagement.repository.semester.ThirdSemesterInterface;
import com.studentManagement.service.excelService.ThirdExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/excel/third")
public class ThirdSemesterController {

    @Autowired
    ThirdSemesterInterface repo;
    private final ThirdExcelFileService excelFileService;

    public ThirdSemesterController(ThirdExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

//    @GetMapping("/uploadForm")
    public String showUploadForm() {
        return "upload2";
    }

//    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<third> entities = excelFileService.processExcelFile(file);

            model.addAttribute("entities", entities);
            model.addAttribute("message", "File uploaded and data saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Error processing the file.");
        }

        return "uploadResult";
    }

//    @GetMapping("/getdetails/{value}")
    public String method1(@PathVariable String value, Model model)
    {
        List<third> std=repo.findUsersWithId(value);
        model.addAttribute("entities",std);
        return "uploadResult";
    }
}



