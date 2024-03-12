package com.studentManagement.service.excelService.impl;


import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.fourth;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.semester.FourthSemesterInterface;
import com.studentManagement.service.excelService.FourthExcelFileService;
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
public class FourthExcelFileServiceImpl implements FourthExcelFileService {

    @Autowired
    StudentRepository studentRepository;
    private final FourthSemesterInterface fourthRepository;

    @Autowired
    public FourthExcelFileServiceImpl(FourthSemesterInterface fourthRepository) {
        this.fourthRepository = fourthRepository;
    }

    @Override
    public List<fourth> processExcelFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("The supplied file is empty.");
        }

        List<fourth> entities = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows and populate entities
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                fourth entity = new fourth();

                // Populate entity fields from row cells
                populateEntityFromRow(entity, row);

                entities.add(entity);
            }

            // Save entities to the database within a transaction
            fourthRepository.saveAll(entities);

            return entities;
        }
        catch (Exception e) {
            // Log the exception details
            e.printStackTrace();

            // Rethrow or return an appropriate response
            throw new IOException("Error processing the Excel file.", e);
        }
    }

    private void populateEntityFromRow(fourth entity, Row row)
    {
        String stdid=String.valueOf(getStringCellValue(row.getCell(0)));
        Student std=studentRepository.findById(stdid).orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + stdid));
        entity.setId1(stdid);
        entity.setSubjectName(String.valueOf(getStringCellValue(row.getCell(1))));
        entity.setInternals(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setExternals(String.valueOf(getStringCellValue(row.getCell(3))));
        entity.setRegulation(String.valueOf(getStringCellValue(row.getCell(4))));
        entity.setAttendance(String.valueOf(getStringCellValue(row.getCell(5))));
        entity.setStudent3(std);
        std.getFourth().add(entity);
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