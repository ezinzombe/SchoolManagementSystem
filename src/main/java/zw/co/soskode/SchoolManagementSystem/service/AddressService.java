package zw.co.soskode.SchoolManagementSystem.service;

import zw.co.soskode.SchoolManagementSystem.model.Address;
import zw.co.soskode.SchoolManagementSystem.model.Student;

import java.util.List;
import java.util.Optional;

public interface AddressService extends IService<Address> {

    Optional<List<Address>> findByStudentDetails(Student studentDetails);
}
