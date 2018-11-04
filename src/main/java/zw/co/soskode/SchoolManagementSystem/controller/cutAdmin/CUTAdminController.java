package zw.co.soskode.SchoolManagementSystem.controller.cutAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import zw.co.soskode.SchoolManagementSystem.model.Subject;

import java.util.List;

/**
 * Created by zinzombe on Nov
 */
@Controller
@RequestMapping("/cutadmin/")
public class CUTAdminController {


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        return "cutAdmin/list";
    }
}
