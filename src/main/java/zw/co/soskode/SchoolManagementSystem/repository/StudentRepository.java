package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.Province;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.model.User;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUserFirstName(String firstName);

    List<Student> findAllByFirstNameLike(String name);

    Student findByUserId(Long userId);

    Student findByUser(User user);

    List<Student> findAllBySchool(School school);

    @Query("select s from Student s order by points desc")
    List<Student> findBestStudents(Pageable pageable);

    @Query("select s from Student s order by points desc")
    List<Student> findBestStudentsByProvince(Pageable pageable);

}
