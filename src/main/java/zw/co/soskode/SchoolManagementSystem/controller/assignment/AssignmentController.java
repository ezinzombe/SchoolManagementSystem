package zw.co.soskode.SchoolManagementSystem.controller.assignment;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zw.co.soskode.SchoolManagementSystem.model.Assignment;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.model.TeacherDetails;
import zw.co.soskode.SchoolManagementSystem.repository.AssignmentRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SchoolRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SubjectRepository;
import zw.co.soskode.SchoolManagementSystem.repository.TeacherRepository;

import javax.inject.Inject;


@Controller
@RequestMapping("/assignment")
public class AssignmentController {

    private final Logger logger = (Logger) LoggerFactory.getLogger(AssignmentController.class);

    @Inject
    private AssignmentRepository assignmentRepository;

    @Inject
    private SubjectRepository subjectRepository;
    @Inject
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        logger.debug("assignment aid - add() is executed!");
        Assignment assignment = new Assignment();
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("assignment", assignment);
        return "assignment/add";
    }




    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("assignment") @Validated Assignment assignment,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("subject", assignment.getSubject());
            model.addAttribute("assignment", assignment);
            return "assignment/add";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        TeacherDetails teacherDetails = teacherRepository.findByEmail(name);
        School school = schoolRepository.getOne(teacherDetails.getSchool().getId());


        assignment.setSchool(school);
        assignmentRepository.save(assignment);
        return "redirect:/teacherPage";
    }


    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Assignment assignmentID = assignmentRepository.findById(id).get();
        assignmentRepository.delete(assignmentID);
        return "redirect:/assignment/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("assignments", assignmentRepository.findAll());
        return "assignment/list";
    }


}


