package zw.co.soskode.SchoolManagementSystem.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;
import zw.co.soskode.SchoolManagementSystem.util.BestPerfomingStudentsByProvincePDFGenerator;
import zw.co.soskode.SchoolManagementSystem.util.BestPerfomingStudentsPDFGenerator;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by zinzombe on Nov
 */
@RestController
@RequestMapping("/reports/students/best")
public class BestHundredStudentsController {
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_PDF_VALUE)

    public ResponseEntity<InputStreamResource> customerReport() throws IOException {

        List<Student> students = studentRepository.findBestStudents(new PageRequest(0, 100));

        ByteArrayInputStream bis = BestPerfomingStudentsPDFGenerator.studentPDFReport(students);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=students.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
