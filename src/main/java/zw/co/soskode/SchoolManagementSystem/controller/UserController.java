package zw.co.soskode.SchoolManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.dto.UserRegistrationDto;
import zw.co.soskode.SchoolManagementSystem.model.Gender;
import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.RoleRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SchoolRepository;
import zw.co.soskode.SchoolManagementSystem.repository.UserRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("user") @Validated User user,
                       BindingResult result, Model model, RedirectAttributes redirectAttributes) {
        System.out.println("Updated USER++++++++++++++++++++++++++++++" + user);
        User updatedUser = userRepository.findById(user.getId()).get();

        if (result.hasErrors()) {
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("schools", schoolRepository.findAll());
            model.addAttribute("genders", Gender.values());
            return "admin/user/edit";
        }
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setDateOfBirth(user.getDateOfBirth());
        updatedUser.setGender(user.getGender());
        updatedUser.setSchool(user.getSchool());
        userRepository.save(updatedUser);
        System.out.println("Updated USER++++++++++++++++++++++++++++++" + updatedUser);
        return "admin/user/list";
    }
    
    
    
    
    
    
    @GetMapping("/list")
    public String list(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        User user = userRepository.findByEmail(name);
        School school = schoolRepository.getOne(user.getSchool().getId());
        List<User> users = userRepository.findAllBySchool(school);
        model.addAttribute("users", users);
        return "admin/user/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userRepository.getOne(id));
        model.addAttribute("genders", Gender.values());
        model.addAttribute("schools", schoolRepository.findAll());
        return "admin/user/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        User user = userRepository.getOne(id);
        userRepository.delete(user);
        return "redirect:/admin/user/list";
    }

}
