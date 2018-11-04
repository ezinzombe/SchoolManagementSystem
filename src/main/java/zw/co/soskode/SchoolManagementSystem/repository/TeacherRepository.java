package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;


@Repository
public interface TeacherRepository extends JpaRepository<TeacherDetails, Long> {

    TeacherDetails findByEmail(String email);
}
