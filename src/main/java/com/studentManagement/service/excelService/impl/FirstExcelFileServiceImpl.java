package com.studentManagement.service.excelService.impl;


import com.studentManagement.entity.Student;
import com.studentManagement.entity.semester.first;
import com.studentManagement.repository.StudentRepository;
import com.studentManagement.repository.semester.FirstSemesterInterface;
import com.studentManagement.service.excelService.FirstExcelFileService;
import jakarta.persistence.EntityNotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class FirstExcelFileServiceImpl implements FirstExcelFileService
{
    @Autowired
    FirstSemesterInterface firstRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<first> processExcelFile(MultipartFile file) throws IOException {
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream()))
        {
            List<first> lis=new ArrayList<>();
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                first entity = new first();
                populateEntityFromRow(entity,row);
                lis.add(entity);
            }
            firstRepository.saveAll(lis);

            return firstRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new IOException("Error processing the Excel file.", e);
        }
    }
private void populateEntityFromRow(first entity, Row row)
{
    String stdid=String.valueOf(getStringCellValue(row.getCell(0)));
    Student std=studentRepository.findById(stdid).orElseThrow(() -> new EntityNotFoundException("ParentEntity not found with id: " + stdid));
    entity.setId1(stdid);
    entity.setSubjectName(String.valueOf(getStringCellValue(row.getCell(1))));
    entity.setInternals(String.valueOf(getStringCellValue(row.getCell(2))));
    entity.setExternals(String.valueOf(getStringCellValue(row.getCell(3))));
    entity.setRegulation(String.valueOf(getStringCellValue(row.getCell(4))));
    entity.setAttendance(String.valueOf(getStringCellValue(row.getCell(5))));
    entity.setStudent(std);
    std.getFirst().add(entity);
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