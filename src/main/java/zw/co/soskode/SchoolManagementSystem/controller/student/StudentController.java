package zw.co.soskode.SchoolManagementSystem.controller.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.Address;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.model.Subject;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.GradesRepository;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SubjectRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;
import zw.co.soskode.SchoolManagementSystem.service.AddressService;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

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
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private GradesRepository gradesRepository;


    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("student", userRepository.getOne(id));
        Subject subject = new Subject();
        model.addAttribute("subject", subject);
        return "student/registerSubject";
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("subject") @Validated Subject subject, Principal principal,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        String name = principal.getName();
        Student student = studentRepository.findByUserFirstName(name);
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/subject/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
       // subject.setStudent(student);
        subjectRepository.save(subject);
        return "redirect:/student/subject/list";
    }


    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()) {
            model.addAttribute("css", "danger");
            model.addAttribute("msg", "Student not found");
        } else {

            final Student student = studentOptional.get();
            model.addAttribute("title", "Student Detail");
            model.addAttribute("addressList", addressService.findByStudent(student).orElse(Collections.<Address>emptyList()));
            model.addAttribute("grades",gradesRepository.findByStudent(student));

        }

    return "student/view";
    }

    @RequestMapping("/list")
    public String list(@PathVariable Long id, Model model) {
        model.addAttribute("student", userRepository.getOne(id));
        return "student/registerSubject";
    }







}
