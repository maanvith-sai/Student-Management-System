package com.studentManagement.service.excelService;

import com.studentManagement.entity.semester.fifth;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface FifthExcelFileService {
    List<fifth> processExcelFile(MultipartFile file) throws IOException;
}
