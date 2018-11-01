package zw.co.soskode.SchoolManagementSystem.controller.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zw.co.soskode.SchoolManagementSystem.model.Grades;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.repository.GradesRepository;
import zw.co.soskode.SchoolManagementSystem.service.StudentService;

import javax.inject.Inject;
import java.util.Optional;

@Controller
@RequestMapping("/grades")
public class GradesController {



    private final Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(GradesController.class);

    @Inject
    private GradesRepository gradesRepository;

    @Inject
    private StudentService studentService;


    @RequestMapping(value = "/{id}/add", method = RequestMethod.GET)
    public String add(@PathVariable("id") Long id, Model model) {

        logger.debug("grade - add() is executed!");

        Grades grades = new Grades();
        Optional<Student> studentOptional = studentService.findOne(id);
        final Student student = studentOptional.get();
        if (studentOptional.isPresent()) {
            grades.setStudent(student);
        } else {
            throw new IllegalArgumentException();
        }

        model.addAttribute("student", student);
        model.addAttribute("grades", grades);

        return "grades/add";

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("address") @Validated Grades grades,
                       BindingResult result, Model model) {

        if (result.hasErrors()) {

            model.addAttribute("student", grades.getStudent());
            model.addAttribute("grades", grades);
            return "grades/add";
        }
        gradesRepository.save(grades);
        return "redirect:/student/" + grades.getStudent().getId();
    }



    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long studentID = gradesRepository.findById(id).get().getStudent().getId();
        gradesRepository.deleteById(studentID);
        return "redirect:/student/" + studentID;
    }



}


