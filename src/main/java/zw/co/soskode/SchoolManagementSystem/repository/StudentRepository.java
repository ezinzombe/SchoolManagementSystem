package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.model.User;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByUserFirstName(String firstName);

    Student findByUserId(Long id);
}
