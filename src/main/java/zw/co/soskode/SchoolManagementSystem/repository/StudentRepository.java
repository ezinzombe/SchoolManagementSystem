package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.StudentDetails;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;


@Repository
public interface StudentRepository extends JpaRepository<StudentDetails, Long> {

}
