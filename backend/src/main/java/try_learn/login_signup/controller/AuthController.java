package try_learn.login_signup.controller;

import try_learn.login_signup.util.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import try_learn.login_signup.dto.LoginRequest;
import try_learn.login_signup.model.Users;
import try_learn.login_signup.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth") // clean grouping
public class AuthController {
    private final UserService userService;
    private final JwtUtil jwtUtil; // injecting

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        Users user = userService.login(request.getUserName(), request.getPassword());
        if(user != null){
            // inject JwtUtil
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(Map.of("token", token, "message","Login Successful!"));
        }else{
            return ResponseEntity.status(401).body(Map.of("message","Invalid credentials"));
        }
    }
}
