package zw.co.soskode.SchoolManagementSystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.RoleRepository;
import zw.co.soskode.SchoolManagementSystem.repository.StudentRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;
import zw.co.soskode.SchoolManagementSystem.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

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
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

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
        final Student student = new Student();
        final User updatedUser = userRepository.getOne(user.getId());
        final  Role studentRole = roleRepository.findRoleByName("STUDENT");
//        =================== MAPPING THE STUDENT AFTER APPROVING===============
        student.setUser(user);
        student.setUserId(user.getId());
        student.setDateCreated(new Date());
        student.setFirstName(user.getFirstName());
        student.setLastName(user.getLastName());
        student.setSchool(user.getSchool());
//        =============UPDATING THE USER NOW===================
        updatedUser.setApproved(true);
        updatedUser.setRoles(user.getRoles());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword((user.getPassword()));
        updatedUser.setRoleName(user.getRoleName());
        updatedUser.setRoles(Arrays.asList(studentRole));

        studentRepository.save(student);

        List<User> students = userRepository.findByRoleName("STUDENT");
        model.addAttribute("students", students);
        model.addAttribute("confirmationMessage", "STUDENT:: "+student.getUser().getFirstName()+"  has been approved!!!");
        return "admin/approve/student";
    }

}
