package zw.co.soskode.SchoolManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.soskode.SchoolManagementSystem.model.Address;
import zw.co.soskode.SchoolManagementSystem.model.Student;

import java.util.List;

/**
 * Created by zinzombe on Oct
 */
public interface AddressRepository extends JpaRepository<Address, Long> {


    List<Address> findByStudent(Student student);


}
