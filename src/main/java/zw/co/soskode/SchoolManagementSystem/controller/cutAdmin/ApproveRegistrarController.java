package zw.co.soskode.SchoolManagementSystem.controller.cutAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/cutAdmin/approve")
public class ApproveRegistrarController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/registrar")
    public String list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User registrar = userRepository.findByEmail(name);
        List<User> registrars = userRepository.findAllByRoleNameAndSchool("REGISTRAR", registrar.getSchool());
        model.addAttribute("registrars", registrars);
        return "cutAdmin/approve/registrar";
    }

    @RequestMapping("/registrar/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        return "cutAdmin/approve/approveRegistrar";
    }

    @RequestMapping(value = {"/registrar/save"}, method = RequestMethod.POST)
    public String add(@ModelAttribute("user") @Validated User user, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("====================USER ==============" + user);
        final Student registrar = new Student();
        final User updatedUser = userRepository.getOne(user.getId());
        final Role registrarRole = roleRepository.findRoleByName("REGISTRAR");
//        =================== MAPPING THE REGISTRAR AFTER APPROVING===============
        registrar.setUser(user);
        registrar.setUserId(user.getId());
        registrar.setDateCreated(new Date());
        registrar.setFirstName(user.getFirstName());
        registrar.setLastName(user.getLastName());
        registrar.setSchool(user.getSchool());
        registrar.setGender(user.getGender());
        registrar.setDateOfBirth(user.getDateOfBirth());
//        =============UPDATING THE USER NOW===================
        updatedUser.setApproved(true);
        updatedUser.setRoles(user.getRoles());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword((user.getPassword()));
        updatedUser.setRoleName(user.getRoleName());
        updatedUser.setRoles(Arrays.asList(registrarRole));

        studentRepository.save(registrar);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        List<User> registrars = userRepository.findAllByRoleName("REGISTRAR");
        model.addAttribute("registrars", registrars);
        model.addAttribute("confirmationMessage", "REGISTRAR:: " + registrar.getUser().getFirstName() + "  has been approved!!!");
        return "cutAdmin/approve/registrar";
    }

}
