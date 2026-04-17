package try_learn.login_signup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import try_learn.login_signup.dto.LoginRequest;
import try_learn.login_signup.model.Users;
import try_learn.login_signup.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth") // clean grouping
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Users user = userService.login(request.getUserName(), request.getPassword());
        if(user != null){
            return ResponseEntity.ok("Login Successful!");
        }else{
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
