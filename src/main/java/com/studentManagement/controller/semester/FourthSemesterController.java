package com.studentManagement.controller.semester;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.fourth;
import com.studentManagement.entity.semester.second;
import com.studentManagement.repository.semester.FourthSemesterInterface;
import com.studentManagement.service.excelService.FourthExcelFileService;
import com.studentManagement.service.excelService.SecondExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/excel/fourth")
public class FourthSemesterController {

    @Autowired
    FourthSemesterInterface repo;
    @Autowired
    private FourthExcelFileService excelFileService;

//    @GetMapping("/uploadForm")
    public String showUploadForm()
    {
        return "upload3";
    }

//    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        try {
            List<fourth> entities = excelFileService.processExcelFile(file);
            model.addAttribute("entities", entities);
            model.addAttribute("message", "File uploaded and data saved successfully!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            model.addAttribute("error", "Error processing the file.");
        }
        return "uploadResult";
    }

//    @GetMapping("/getdetails/{value}")
    public String method1(@PathVariable String value,Model model)
    {
        List<fourth> std2=repo.findUsersWithId(value);
        model.addAttribute("entities",std2);
        return "uploadResult";
    }
}