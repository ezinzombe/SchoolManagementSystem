package zw.co.soskode.SchoolManagementSystem.controller.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zw.co.soskode.SchoolManagementSystem.model.Grades;
import zw.co.soskode.SchoolManagementSystem.repository.GradesRepository;
import zw.co.soskode.SchoolManagementSystem.service.StudentService;

import javax.inject.Inject;

@Controller
@RequestMapping("/grades")
public class GradesController {



    private final Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(GradesController.class);

    @Inject
    private GradesRepository gradesRepository;

    @Inject
    private StudentService studentService;


    @GetMapping(value = "/single/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        Grades grades = gradesRepository.getOne(id);
        return "redirect:/student/show/" + grades.getStudent().getId();
    }

    @RequestMapping(value = "/save/{id}",method = RequestMethod.POST)
    public String save(@ModelAttribute("newGrade") @Validated Grades grades, @PathVariable("id") Long id,
                       BindingResult result, Model model) {
        if (result.hasErrors()) {

            model.addAttribute("student", grades.getStudent());
            model.addAttribute("grades", grades);
            return "grades/add";
        }
        grades.setStudent(studentService.findOne(id).get());
        gradesRepository.save(grades);
        return "redirect:/student/show/" + grades.getStudent().getId();
    }



    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long studentID = gradesRepository.findById(id).get().getStudent().getId();
        gradesRepository.deleteById(studentID);
        return "redirect:/student/show/" + id;
    }


    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Long id, Model model) {
        return "student/view";
    }

}


