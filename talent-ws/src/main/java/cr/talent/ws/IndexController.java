package cr.talent.web.controllers ;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String printIndex(ModelMap model) {
        model.addAttribute("text", "This is a Controller test");
        return "index";
    }
}