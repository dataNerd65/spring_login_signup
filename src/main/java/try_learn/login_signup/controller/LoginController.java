package try_learn.login_signup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins="*") // To remove
@Controller
public class LoginController {
    @GetMapping("/register")
    public String login(){
        return "register";
    }
    @GetMapping("/login")
    public  String register(){
        return "login";
    }
    @GetMapping("/dashboard")
    public String dashboard(){
        return "dashboard";
    }
}
