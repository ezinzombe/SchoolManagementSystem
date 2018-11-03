package zw.co.soskode.SchoolManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.dto.UserRegistrationDto;
import zw.co.soskode.SchoolManagementSystem.model.Gender;
import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.RoleRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SchoolRepository;
import zw.co.soskode.SchoolManagementSystem.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
//@Autowired
//private StudentRepository studentRepository;
@Autowired
private SchoolRepository schoolRepository;


    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("roles", roleRepository.findAll());
        model.addAttribute("schools", schoolRepository.findAll());
        model.addAttribute("genders", Gender.values());
        return "registration/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        User user = new User();
//        Student student = new Student();
        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("schools", schoolRepository.findAll());
            model.addAttribute("genders", Gender.values());
            return "registration/registration";
        }
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setGender(userDto.getGender());
        user.setSchool(userDto.getSchool());
        System.out.println("============================ROLES======"+userDto.getRoles());

        for (Role r: userDto.getRoles()) {
            if(r.getName().toUpperCase().equals("ADMIN")){
                user.setRoleName("ADMIN");
                user.setApproved(true);
                userService.save(user);
            } else if(r.getName().toUpperCase().equals("STUDENT")){
                user.setRoleName("STUDENT");
                userService.save(user);
            } else if(r.getName().toUpperCase().equals("TEACHER")){
                user.setRoleName("TEACHER");
                userService.save(user);
            } else if (r.getName().toUpperCase().equals("CUTADMIN")) {
                user.setRoleName("CUTADMIN");
                user.setApproved(true);
                userService.save(user);
            } else if (r.getName().toUpperCase().equals("REGISTRAR")) {
                user.setRoleName("REGISTRAR");
                user.setApproved(true);
                userService.save(user);
            } else{
                user.setRoleName("zombie");
                userService.save(user);
            }
        }




        redirectAttributes.addFlashAttribute("confirmationMessage", "Your Account has been created successfully. A confirmation e-mail has been sent to " + user.getEmail());
        return "redirect:/login";
    }

}
