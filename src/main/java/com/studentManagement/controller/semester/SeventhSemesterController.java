package com.studentManagement.controller.semester;


import com.studentManagement.entity.semester.seventh;
import com.studentManagement.repository.semester.SeventhSemesterInterface;
import com.studentManagement.service.excelService.SeventhExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/excel/seventh")
public class SeventhSemesterController {

    @Autowired
    SeventhSemesterInterface repo;
    private final SeventhExcelFileService excelFileService;

    public SeventhSemesterController(SeventhExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

//    @GetMapping("/uploadForm")
    public String showUploadForm() {
        return "upload6";
    }

//    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<seventh> entities = excelFileService.processExcelFile(file);
            // You can do further processing or validation here

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
        List<seventh> std2=repo.findUsersWithId(value);
        model.addAttribute("entities",std2);
        return "uploadResult";
    }
}





