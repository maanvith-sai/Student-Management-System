package com.studentManagement.service.excelService;

import com.studentManagement.entity.semester.first;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FirstExcelFileService
{
    List<first> processExcelFile(MultipartFile file) throws IOException;
}
