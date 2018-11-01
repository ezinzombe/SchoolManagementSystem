package zw.co.soskode.SchoolManagementSystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.Classes;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.repository.ClassesRepository;
import zw.co.soskode.SchoolManagementSystem.repository.SchoolRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/classes")
public class ClassesController {

    @Autowired
    private ClassesRepository classesRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Classes classes;
        if (id != null) {
            classes = classesRepository.findById(id).get();
        } else {
            classes = new Classes();
        }
        model.addAttribute("classes", classes);
        model.addAttribute("title", "Create/ Edit Classes");
        return "admin/classes/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("school") @Validated Classes classes,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/classes/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        classesRepository.save(classes);
        return "redirect:/admin/classes/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Classes> classes = classesRepository.findAll();
        model.addAttribute("classes", classes);
        return "admin/classes/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("classes", classesRepository.findById(id).get());
        return "admin/classes/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long classeId = classesRepository.findById(id).get().getId();
        classesRepository.deleteById(classeId);
        return "redirect:/admin/classes/list";
    }

}
