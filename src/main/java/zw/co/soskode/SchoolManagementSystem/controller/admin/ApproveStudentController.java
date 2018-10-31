package zw.co.soskode.SchoolManagementSystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.StudentDetails;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;
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
@RequestMapping("/admin/approve")
public class ApproveStudentController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student")
    public String list(Model model) {
        List<User> students = userRepository.findByRoleName("STUDENT");
        model.addAttribute("students", students);
        return "admin/approve/student";
    }

    @RequestMapping("/student/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "admin/approve/approveStudent";
    }

    @RequestMapping(value = {"/student/save"}, method = RequestMethod.POST)
    public String add(@ModelAttribute("user") @Validated User user, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("=================================="+user);
        final StudentDetails student = new StudentDetails();
        student.setUser(user);
        student.setUserId(user.getId());
        student.setDateCreated(new Date());
        user.setApproved(true);

        userRepository.save(user);
        userRepository.save(user);
        studentRepository.save(student);

        List<User> students = userRepository.findByRoleName("STUDENT");
        model.addAttribute("students", students);
        model.addAttribute("confirmationMessage", "Teacher"+student.getUser().getFirstName()+"  has been approved!!!");
        return "admin/approve/student";
    }

}
