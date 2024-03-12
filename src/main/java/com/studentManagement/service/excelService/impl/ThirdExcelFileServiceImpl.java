package com.studentManagement.service.excelService.impl;

import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.entity.semester.third;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.semester.ThirdSemesterInterface;
import com.studentManagement.service.excelService.ThirdExcelFileService;
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
public class ThirdExcelFileServiceImpl implements ThirdExcelFileService {

    private final ThirdSemesterInterface thirdRepository;

    @Autowired
    public ThirdExcelFileServiceImpl(ThirdSemesterInterface thirdRepository) {
        this.thirdRepository = thirdRepository;
    }

    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<third> processExcelFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("The supplied file is empty.");
        }

        List<third> entities = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream()))
        {
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows and populate entities
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                third entity = new third();

                // Populate entity fields from row cells
                populateEntityFromRow(entity, row);

                entities.add(entity);
            }

            // Save entities to the database within a transaction
            thirdRepository.saveAll(entities);

            return thirdRepository.findAll();
        }
        catch (Exception e) {
            // Log the exception details
            e.printStackTrace();

            // Rethrow or return an appropriate response
            throw new IOException("Error processing the Excel file.", e);
        }
    }

    private void populateEntityFromRow(third entity, Row row)
    {
        String stdid=String.valueOf(getStringCellValue(row.getCell(0)));
        Student std=studentRepository.findById(stdid).orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + stdid));
        entity.setId1(stdid);
        entity.setSubjectName(String.valueOf(getStringCellValue(row.getCell(1))));
        entity.setInternals(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setExternals(String.valueOf(getStringCellValue(row.getCell(3))));
        entity.setRegulation(String.valueOf(getStringCellValue(row.getCell(4))));
        entity.setAttendance(String.valueOf(getStringCellValue(row.getCell(5))));
        entity.setStudent2(std);
        std.getThird().add(entity);
    }
    private String getStringCellValue(Cell cell) {
        if (cell == null) {
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