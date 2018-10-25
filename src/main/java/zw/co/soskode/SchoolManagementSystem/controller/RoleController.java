package zw.co.soskode.SchoolManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import zw.co.soskode.SchoolManagementSystem.model.Role;
import zw.co.soskode.SchoolManagementSystem.repository.RoleRepository;

import java.util.List;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @RequestMapping(value = {"/add", "/add/{id}"}, method = RequestMethod.GET)
    public String add(@RequestParam(required = false) Long id, Model model) {
        final Role role;
        if (id != null) {
            role = roleRepository.findById(id).get();
        } else {
            role = new Role();
        }
        model.addAttribute("role", role);
        model.addAttribute("title", "Create/ Edit Role");
        return "admin/role/add";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("role") @Validated Role role,
                       BindingResult result, SessionStatus status,
                       final RedirectAttributes redirectAttributes) throws Exception {
        //Check validation errors
        if (result.hasErrors()) {
            return "admin/role/add";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
        }
        status.setComplete();
        roleRepository.save(role);
        return "redirect:/admin/role/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "admin/role/list";
    }

    @RequestMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("role", roleRepository.findById(id).get());
        return "admin/role/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        Long roleId = roleRepository.findById(id).get().getId();
        roleRepository.deleteById(roleId);
        return "redirect:/admin/role/list";
    }

}
