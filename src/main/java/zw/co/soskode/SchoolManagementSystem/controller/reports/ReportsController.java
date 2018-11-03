package zw.co.soskode.SchoolManagementSystem.controller.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zw.co.soskode.SchoolManagementSystem.service.UserService;

import javax.xml.ws.Action;
import java.time.LocalDateTime;

/**
 * Created by zinzombe on Nov
 */

@Controller
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "reports/list";
    }


    @GetMapping("/provinces/pdfList")
    public String download(Model model) {

        model.addAttribute("users", userService.findAll());
        return "";
    }


}
