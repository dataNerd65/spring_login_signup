package try_learn.login_signup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Getter


public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //no setter

    @Setter
    @Column(nullable = false, unique = false)
    private String firstName;

    @Setter
    @Column(nullable = false, unique = false)
    private String lastName;


    @Column(nullable = true, unique = true)
    private String userName; // no setter

    @Setter
    private String phoneNumber;

    //@Setter // testing
    @Column(nullable=false, unique = true)
    private String email; // no setter

    public void changeEmail(String newEmail){
        if(newEmail == null || newEmail.isBlank()){
            throw new IllegalArgumentException("Email cannot be empty!");
        }
        // Email format(REGEX)

        this.email = newEmail;
    }


    private String password; // no setter

    public void setPassword(String encodedPassword){
        this.password = encodedPassword;
    }
//    public void changePassword(String encodedPassword){
//        if(encodedPassword == null || encodedPassword.isBlank()){
//            throw new IllegalArgumentException("Password cannot be empty!");
//        }
//        this.password = encodedPassword;
//    }
    public void changeUsername(String newUserName){
        if(newUserName == null || newUserName.isBlank()){
            throw new IllegalArgumentException("Username cannot be empty!");
        }
        this.userName = newUserName;
    }
    public void updateProfileNames(String firstName, String lastName, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    private String provider;
    

    private String googleId;

    public void getProvider(String google) {
    }

    public void getGoogleId(String googleId) {
    }


//    public User() {} // constructor // done by @NoArgsConstructor

}