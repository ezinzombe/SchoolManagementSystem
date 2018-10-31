package zw.co.soskode.SchoolManagementSystem.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zinzombe on Oct
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/list")
    public String list(Model model) {
        return "admin/list";
    }

}
