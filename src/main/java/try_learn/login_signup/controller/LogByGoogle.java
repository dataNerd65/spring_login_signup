package try_learn.login_signup.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.api.client.json.jackson2.JacksonFactory;
import try_learn.login_signup.controller.GoogleIdToken.payload;
import try_learn.login_signup.model.Users;
import try_learn.login_signup.repository.UserRepository;
import try_learn.login_signup.util.JwtUtil;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class LogByGoogle {
    @Value("${google.client.id}")
    private String  clientId;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public LogByGoogle(UserRepository userRepository, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/google")
    public ResponseEntity<?> googleAuth(@RequestBody Map<String , String> body){
        String token = body.get("token");
        System.out.println("Received token: " + token);

        try{
            GoogleIdToken.Payload payload = verifyToken(token);
            System.out.println("Payload: " + payload);
            if(payload == null){
                System.out.println("Token verification failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String googleId = payload.getSubject();

            String[] parts = name.split(" ");
            String firstName = parts[0];
            String lastName = parts.length > 1 ? parts[1] : "";

            // logic
            Users user = userRepository.findByEmail(email).orElseGet(() -> {
                Users newUser = new Users();
                newUser.setEmail(email);
                newUser.setFirstName(firstName);
                newUser.setLastName(lastName);
                newUser.setProvider("GOOGLE");
                newUser.setGoogleId(googleId);
                return userRepository.save(newUser);
            });

            // save if not saved else login
            // jwt generation
            String appToken = jwtUtil.generateToken(user.getEmail());

            return ResponseEntity.ok(Map.of(
                    "token", appToken,
                    "email", user.getEmail(),
                    "name", user.getFirstName()
            ));
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error verifying token");
        }
    }
    private GoogleIdToken.Payload verifyToken(String idTokenString) throws Exception{
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId)).build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        if(idToken != null){
            return idToken.getPayload();
        }
        return null;
    }

}
