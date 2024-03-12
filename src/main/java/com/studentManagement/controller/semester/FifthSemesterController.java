package com.studentManagement.controller.semester;

import com.studentManagement.entity.semester.fifth;
import com.studentManagement.repository.semester.FifthSemesterInterface;
import com.studentManagement.service.excelService.FifthExcelFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/excel/fifth")
public class FifthSemesterController {

    @Autowired
    FifthSemesterInterface repo;

    private final FifthExcelFileService excelFileService;

    public FifthSemesterController(FifthExcelFileService excelFileService) {
        this.excelFileService = excelFileService;
    }

//    @GetMapping("/uploadForm")
    public String showUploadForm()
    {
        return "upload4";
    }

//    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model)
    {
        try
        {
            List<fifth> entities = excelFileService.processExcelFile(file);
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
    public String method1(@PathVariable String value, Model model)
    {
        List<fifth> std2=repo.findUsersWithId(value);
        model.addAttribute("entities",std2);
        return "uploadResult";
    }
}



