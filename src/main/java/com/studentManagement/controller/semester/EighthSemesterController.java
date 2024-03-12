package com.studentManagement.controller.semester;
import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.eighth;
import com.studentManagement.repository.semester.EighthSemesterInterface;
import com.studentManagement.service.excelService.EighthExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/excel/eighth")
public class EighthSemesterController {

    @Autowired
    EighthSemesterInterface repo;
    private final EighthExcelFileService excelFileService;

    public EighthSemesterController(EighthExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

//    @GetMapping("/uploadForm")
    public String showUploadForm()
    {
        return "upload7";
    }

//    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<eighth> entities = excelFileService.processExcelFile(file);
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
    public String method1(@PathVariable String value,Model model)
    {
        List<eighth> std2=repo.findUsersWithId(value);
        model.addAttribute("entities",std2);
        return "uploadResult";
    }
}





