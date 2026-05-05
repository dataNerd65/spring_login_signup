package try_learn.login_signup.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import try_learn.login_signup.dto.PasswordChangeRequest;
import try_learn.login_signup.service.UserService;


import try_learn.login_signup.model.Users;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="*") // To remove
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping("/{id}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable Long id,
            @RequestBody PasswordChangeRequest request){
        userService.updatePassword(id, request);
        return ResponseEntity.ok("Password updated successfully");
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @PostMapping
    public Users createUser(@RequestBody Users user){
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user){
        return userService.updateUser(id, user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
