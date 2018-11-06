package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.Grades;
import zw.co.soskode.SchoolManagementSystem.model.RecomendedProgramme;
import zw.co.soskode.SchoolManagementSystem.model.Student;

import java.util.List;


@Repository
public interface RecommendedProgrammesRepository extends JpaRepository<RecomendedProgramme, Long> {


    List<RecomendedProgramme> findByStudent(Student student);


}
