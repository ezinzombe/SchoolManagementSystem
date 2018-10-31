package zw.co.soskode.SchoolManagementSystem.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.model.StudentDetails;
import zw.co.soskode.SchoolManagementSystem.model.Subject;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;
import zw.co.soskode.SchoolManagementSystem.repository.TeacherRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping("/addSubject/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("student", userRepository.getOne(id));
        return "student/registerSubject";
    }

//
//    @RequestMapping(value = {"/subject/save"}, method = RequestMethod.POST)
//    public String add(@ModelAttribute("user") @Validated StudentDetails studentDetails, Model model, RedirectAttributes redirectAttributes) {
//        System.out.println("=================================="+studentDetails);
//        final Subject subject = new Subject();
//        subject.setStudentDetails(studentDetails);
//
//        userRepository.save(user);
//        studentRepository.save(student);
//
//        List<User> teachers = userRepository.findAll();
//        model.addAttribute("teachers", teachers);
//        model.addAttribute("confirmationMessage", "Teacher"+student.getUser().getFirstName()+"  has been approved!!!");
//        return "admin/approve/student";
//    }





}
