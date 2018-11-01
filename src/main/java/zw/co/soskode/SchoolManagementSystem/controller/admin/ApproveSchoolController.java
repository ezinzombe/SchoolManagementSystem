package zw.co.soskode.SchoolManagementSystem.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.Province;
import zw.co.soskode.SchoolManagementSystem.model.School;
import zw.co.soskode.SchoolManagementSystem.repository.SchoolRepository;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by zinzombe on Nov
 */

@Controller
@RequestMapping("/admin/school")
public class ApproveSchoolController {


    @Autowired
    private SchoolRepository schoolRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final School school;
        if (id != null) {
            school = schoolRepository.findById(id).get();
        } else {
            school = new School();
        }
        model.addAttribute("school", school);
        model.addAttribute("title", "Create/ Edit School");
        model.addAttribute("provinces", Province.values());
        return "admin/school/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("school") @Validated School school,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/school/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        schoolRepository.save(school);
        return "redirect:/admin/school/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<School> schools = schoolRepository.findAll();
        model.addAttribute("schools", schools);
        return "admin/school/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("school", schoolRepository.findById(id).get());
        model.addAttribute("provinces", Province.values());
        return "admin/school/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long schoolId = schoolRepository.findById(id).get().getId();
        schoolRepository.deleteById(schoolId);
        return "redirect:/admin/school/list";
    }
}
