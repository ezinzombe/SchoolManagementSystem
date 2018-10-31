package zw.co.soskode.SchoolManagementSystem.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;
import zw.co.soskode.SchoolManagementSystem.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public Optional<Student> findOne(Long id) {

            return studentRepository.findById(id);
    }

    @Override
    public Optional<List<Student>> findAll() {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Boolean checkDuplicate(Student student) {
        return null;
    }
}
