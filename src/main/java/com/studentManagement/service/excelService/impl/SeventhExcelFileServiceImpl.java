package com.studentManagement.service.excelService.impl;


import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.seventh;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.semester.SeventhSemesterInterface;
import com.studentManagement.service.excelService.SeventhExcelFileService;
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
public class SeventhExcelFileServiceImpl implements SeventhExcelFileService {

    @Autowired
    StudentRepository studentRepository;
    private final SeventhSemesterInterface seventhRepository;

    @Autowired
    public SeventhExcelFileServiceImpl(SeventhSemesterInterface seventhRepository) {
        this.seventhRepository = seventhRepository;
    }

    @Override
    public List<seventh> processExcelFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("The supplied file is empty.");
        }

        List<seventh> entities = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows and populate entities
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                seventh entity = new seventh();

                // Populate entity fields from row cells
                populateEntityFromRow(entity, row);

                entities.add(entity);
            }

            // Save entities to the database within a transaction
            seventhRepository.saveAll(entities);

            return entities;
        }
        catch (Exception e) {
            // Log the exception details
            e.printStackTrace();

            // Rethrow or return an appropriate response
            throw new IOException("Error processing the Excel file.", e);
        }
    }

    private void populateEntityFromRow(seventh entity, Row row)
    {
        String stdid=String.valueOf(getStringCellValue(row.getCell(0)));
        Student std=studentRepository.findById(stdid).orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + stdid));
        entity.setId1(stdid);
        entity.setSubjectName(String.valueOf(getStringCellValue(row.getCell(1))));
        entity.setInternals(String.valueOf(getStringCellValue(row.getCell(2))));
        entity.setExternals(String.valueOf(getStringCellValue(row.getCell(3))));
        entity.setRegulation(String.valueOf(getStringCellValue(row.getCell(4))));
        entity.setAttendance(String.valueOf(getStringCellValue(row.getCell(5))));
        entity.setStudent6(std);
        std.getSeventh().add(entity);
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