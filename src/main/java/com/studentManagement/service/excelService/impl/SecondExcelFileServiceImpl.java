package com.studentManagement.service.excelService.impl;


import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.second;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.semester.FirstSemesterInterface;
import com.studentManagement.repository.semester.SecondSemesterInterface;
import com.studentManagement.service.excelService.SecondExcelFileService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class SecondExcelFileServiceImpl implements SecondExcelFileService {

    @Autowired
    SecondSemesterInterface repo;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<second> processExcelFile(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream()))
        {
            List<second> lis=new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext())
            {
                rowIterator.next();
            }
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                second entity=new second();
                populateEntityFromRow(entity,row);
                lis.add(entity);
            }
            repo.saveAll(lis);

            return repo.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IOException("Error processing the Excel file.", e);
        }
    }
    private void populateEntityFromRow(second entity, Row row)
    {
        String stdid=String.valueOf(getStringCellValue(row.getCell(0)));
        Student std=studentRepository.findById(stdid).orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + stdid));
        entity.setId1(stdid);
        entity.setSubjectName(String.valueOf(getStringCellValue(row.getCell(1))));
        entity.setInternals(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setExternals(String.valueOf(getStringCellValue(row.getCell(3))));
        entity.setRegulation(String.valueOf(getStringCellValue(row.getCell(4))));
        entity.setAttendance(String.valueOf(getStringCellValue(row.getCell(5))));
        entity.setStudent1(std);
        std.getSecond().add(entity);
    }
    private String getStringCellValue(Cell cell) {
        if (cell == null)
        {
            return null;
        }
        switch (cell.getCellType())
        {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                // Handle formula cells if needed
                return cell.getCellFormula();
        }
        return null;
    }
}