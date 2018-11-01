package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.soskode.SchoolManagementSystem.model.Grades;
import zw.co.soskode.SchoolManagementSystem.model.Student;

import java.util.List;

public interface GradesRepository extends JpaRepository<Grades, Long> {


    List<Grades> findByStudent(Student student);


}
