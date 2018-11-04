package zw.co.soskode.SchoolManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;
import zw.co.soskode.SchoolManagementSystem.model.User;
import zw.co.soskode.SchoolManagementSystem.repository.*;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

/**
 * Created by zinzombe on Oct
 */
@Controller
public class HomeController {
    @Inject
    private StudentRepository studentRepository;
    @Inject
    private UserRepository  userRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }

    @RequestMapping(value = {"/homePage"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value = {"/teacherPage"}, method = RequestMethod.GET)
    public ModelAndView userPage(Principal principal) {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        TeacherDetails teacherDetails = teacherRepository.findByEmail(name);
        System.out.println("=======================NAME++==================" + name);
        System.out.println("=======================TEACHER++==================" + teacherDetails);
        School school = schoolRepository.getOne(teacherDetails.getSchool().getId());

        System.out.println("=======================SCHOOL++==================" + school);
        model.addObject("username", name);
        model.addObject("students", studentRepository.findAllBySchool(school));
        model.setViewName("teacher/home");
        return model;
    }

    @RequestMapping(value = {"/studentPage"}, method = RequestMethod.GET)
    public ModelAndView studentPage() {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        model.addObject("username", name);
        User user=userRepository.findByEmail(name);
        Student student= studentRepository.findByUserId(Long.valueOf(user.getId()));
        System.out.println("---------------------------------------"+student);
        model.setViewName("redirect:/student/show/"+ student.getId());
        return model;
    }

    @RequestMapping(value = {"/cutAdminPage"}, method = RequestMethod.GET)
    public ModelAndView isCUTAdmin() {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addObject("username", name);
        model.addObject("schools", schoolRepository.findAll());
        model.setViewName("cutAdmin/home");
        return model;
    }

    @RequestMapping(value = {"/registrarPage"}, method = RequestMethod.GET)
    public ModelAndView isRegistarPage() {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

       User user = userRepository.findByEmail(name);

        School school = schoolRepository.getOne(2L);

        model.addObject("username", name);
        model.addObject("students", studentRepository.findAllBySchool(school));

        model.setViewName("registrar/home");
        return model;
    }

    @RequestMapping(value = {"/adminPage"}, method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addObject("username", name);
        model.setViewName("admin/home");
        return model;
    }




}
