package try_learn.login_signup.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import try_learn.login_signup.dto.PasswordChangeRequest;
import try_learn.login_signup.model.Users;
import try_learn.login_signup.repository.UserRepository;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updatePassword(Long id, PasswordChangeRequest request){
        // 1. fetch the user
        Users user = userRepository.findById(id).orElseThrow(() ->new RuntimeException("User not Found!"));
        // 2. verify the current password matches the one in db
        String rawPassword = request.getCurrentPassword();
        String encodedPassword = user.getPassword();
        if (!passwordEncoder.matches(rawPassword, encodedPassword)){
            throw new RuntimeException("Invalid current password");
        }
        // 3. Check if new password is same as old // optional
        if(passwordEncoder.matches(request.getNewPassword(), encodedPassword)){ // to read on
            throw new RuntimeException("New password cannot be the same as the old one");
        }
        // Encode and update
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        //userRepository.save(user);  // stop redoing it hibernate will handle it.
    }

    // Constructor injection
    public UserService (UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Get all users
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }
    // Get user by id
    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }
    // Create new User
    public Users createUser(Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));// encodes
        return userRepository.save(user);
    }
    // delete user
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    // Update User
    @Transactional
    public Users updateUser(Long id, Users userDetails){
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found!"));

        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        // user.setPassword(userDetails.getPassword());

        return userRepository.save(user);
    }
    public Users login(String userName, String password){
        Optional<Users> userOptional = userRepository.findByUserName(userName);

        if(userOptional.isEmpty()){
            return null;
        }
        Users user = userOptional.get();
//        if(!user.getPassword().equals(password)){ // plain text
//            return null;
//        }
        if(!passwordEncoder.matches(password, user.getPassword())){
            return null;
        }
        return user;
    }
}
// curl -X PUT http://localhost:8080/users/3 -H "Content-Type: application/json" -d '{"firstName":"Pierre","lastName":"Kiarie","userName":"kiarie","phoneNumber":"0769916158","email":"pkiarie812@gmail.com"}'