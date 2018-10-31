package zw.co.soskode.SchoolManagementSystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.soskode.SchoolManagementSystem.model.Address;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.repository.AddressRepository;
import zw.co.soskode.SchoolManagementSystem.service.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Optional<List<Address>> findByStudentDetails(Student student) {
        return Optional.ofNullable((List<Address>) addressRepository.findByStudent(student));
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Address>> findAll() {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Boolean checkDuplicate(Address address) {
        return null;
    }
}
