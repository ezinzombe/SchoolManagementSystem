package zw.co.soskode.SchoolManagementSystem.controller.student;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import zw.co.soskode.SchoolManagementSystem.model.Address;
import zw.co.soskode.SchoolManagementSystem.model.AddressType;
import zw.co.soskode.SchoolManagementSystem.model.Student;
import zw.co.soskode.SchoolManagementSystem.service.AddressService;
import zw.co.soskode.SchoolManagementSystem.service.StudentService;

import javax.inject.Inject;
import java.util.Optional;


@Controller
@RequestMapping("/address")
public class AddressController {

    private final Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(AddressController.class);

    @Inject
    private AddressService addressService;

    @Inject
    private StudentService studentService;


    @RequestMapping(value = "/add/{id}", method = RequestMethod.GET)
    public String add(@PathVariable("id") Long id, Model model) {

        logger.debug("address aid - add() is executed!");

        Address address = new Address();
        Optional<Student> studentOptional = studentService.findOne(id);
        final Student student = studentOptional.get();
        if (studentOptional.isPresent()) {
            address.setStudent(student);
        } else {
            throw new IllegalArgumentException();
        }

        model.addAttribute("student", student);
        model.addAttribute("address", address);
        populateDefaultModel(model);
        return "address/add";

    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@ModelAttribute("address") @Validated Address address,
                       @RequestParam("id") Long id,
                       BindingResult result, Model model) {
        System.out.println("-----------------------------------------"+id);
        if (result.hasErrors()) {

            model.addAttribute("student", address.getStudent());
            model.addAttribute("address", address);
            return "address/add";
        }
        Student student = studentService.findOne(id).get();
        address.setStudent(student);
        addressService.save(address);
        return "redirect:/student/show" + address.getStudent().getId();
    }

    private void populateDefaultModel(Model model) {
        model.addAttribute("addressTypeList", AddressType.values());

    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long studentID = addressService.findOne(id).get().getStudent().getId();
        addressService.delete(id);
        return "redirect:/student/" + studentID;
    }



}


