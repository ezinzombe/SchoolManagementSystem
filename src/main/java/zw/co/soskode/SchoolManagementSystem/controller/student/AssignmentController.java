//package zw.co.soskode.SchoolManagementSystem.controller.student;
//
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import zw.co.soskode.SchoolManagementSystem.model.Assignment;
//import zw.co.soskode.SchoolManagementSystem.model.AddressType;
//import zw.co.soskode.SchoolManagementSystem.model.Student;
//import zw.co.soskode.SchoolManagementSystem.repository.AssignmentRepository;
//import zw.co.soskode.SchoolManagementSystem.service.StudentService;
//
//import javax.inject.Inject;
//import java.util.Optional;
//
//
//@Controller
//@RequestMapping("/assignment")
//public class AssignmentController {
//
//    private final Logger logger = (Logger) LoggerFactory.getLogger(AssignmentController.class);
//
//    @Inject
//    private AssignmentRepository assignmentRepository;
//
//    @Inject
//    private StudentService studentService;
//
//
//    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
//    public String add(@PathVariable("id") Long id, Model model) {
//
//        logger.debug("assignment aid - add() is executed!");
//
//        Assignment assignment = new Assignment();
//        Optional<Student> studentOptional = studentService.findOne(id);
//        final Student student = studentOptional.get();
//        if (studentOptional.isPresent()) {
//            assignment.setStudent(student);
//        } else {
//            throw new IllegalArgumentException();
//        }
//
//        model.addAttribute("student", student);
//        model.addAttribute("assignment", assignment);
//        populateDefaultModel(model);
//        return "assignment/add";
//
//    }
//
//    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
//    public String save(@ModelAttribute("assignment") @Validated Assignment assignment, @PathVariable("id") Long id,
//                       BindingResult result, Model model) {
//        if (result.hasErrors()) {
//
//            model.addAttribute("student", assignment.getStudent());
//            model.addAttribute("assignment", assignment);
//            return "assignment/add";
//        }
//
//        assignment.setStudent(studentService.findOne(id).get());
//
//        assignmentRepository.save(assignment);
//        return "redirect:/student/show/" + assignment.getStudent().getId();
//    }
//
//    private void populateDefaultModel(Model model) {
//        model.addAttribute("addressTypeList", AddressType.values());
//
//    }
//
////    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
////    public String delete(@PathVariable("id") Long id) {
////        Long assignmentID = assignmentRepository.findOne(id).get();
////        assignmentRepository.delete(assignmentID);
////        return "redirect:/student/" + studentID;
////    }
//
//
//}
//
//
