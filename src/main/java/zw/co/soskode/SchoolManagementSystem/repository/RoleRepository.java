package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.model.Student;

import java.util.List;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByName(String name);


    @Query("select r from Role r where r.id >1")
    List<Role> findAllExpertCutAdmin();
}
