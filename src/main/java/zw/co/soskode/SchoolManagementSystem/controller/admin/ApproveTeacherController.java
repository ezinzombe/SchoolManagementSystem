package zw.co.soskode.SchoolManagementSystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.TeacherRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;

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
        teacherDetails.setUser(user);
        teacherDetails.setUserId(user.getId());
        teacherDetails.setDateCreated(new Date());
        user.setApproved(true);

        userRepository.save(user);
        teacherRepository.save(teacherDetails);

        List<User> teachers = userRepository.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("confirmationMessage", "Teacher"+teacherDetails.getUser().getFirstName()+"  has been approved!!!");
        return "admin/approve/teacher";
    }

}
