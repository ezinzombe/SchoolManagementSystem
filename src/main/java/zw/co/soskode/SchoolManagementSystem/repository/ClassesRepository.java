package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.Classes;
import zw.co.soskode.SchoolManagementSystem.model.School;

@Repository
public interface ClassesRepository  extends JpaRepository<Classes, Long> {
}
