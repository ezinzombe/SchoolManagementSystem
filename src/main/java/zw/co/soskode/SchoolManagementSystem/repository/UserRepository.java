package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.model.User;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByRoleName (String name);
    User findUserByRoleName (String name);
    User findUserByFirstName (String firstName);

    List<User> findAllBySchool(School school);
}
