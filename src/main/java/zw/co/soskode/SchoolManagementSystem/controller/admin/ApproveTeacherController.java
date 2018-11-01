package zw.co.soskode.SchoolManagementSystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.RoleRepository;
import zw.co.soskode.SchoolManagementSystem.repository.TeacherRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/approve")
public class ApproveTeacherController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/teacher")
    public String list(Model model) {
        List<User> teachers = userRepository.findByRoleName("ADMIN");
        model.addAttribute("teachers", teachers);
        return "admin/approve/teacher";
    }

    @RequestMapping("/teacher/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "admin/approve/approveTeacher";
    }

    @RequestMapping(value = {"/teacher/save"}, method = RequestMethod.POST)
    public String add(@ModelAttribute("user") @Validated User user, Model model, RedirectAttributes redirectAttributes) {
        final TeacherDetails teacherDetails = new TeacherDetails();
        User updatedUser = new User();
        final Role studentRole = roleRepository.findRoleByName("TEACHER");
        teacherDetails.setUser(user);
        teacherDetails.setUserId(user.getId());
        teacherDetails.setDateCreated(new Date());

        updatedUser.setApproved(true);
        updatedUser.setRoles(user.getRoles());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword((user.getPassword()));
        updatedUser.setRoleName(user.getRoleName());
        updatedUser.setRoles(Arrays.asList(studentRole));
        userRepository.save(updatedUser);
        teacherRepository.save(teacherDetails);

        List<User> teachers = userRepository.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("confirmationMessage", "Teacher"+teacherDetails.getUser().getFirstName()+"  has been approved!!!");
        return "admin/approve/teacher";
    }

}
