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
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.model.Subject;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SubjectRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;

import java.security.Principal;

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


    @RequestMapping("/list")
    public String list(@PathVariable Long id, Model model) {
        model.addAttribute("student", userRepository.getOne(id));
        return "student/registerSubject";
    }







}
