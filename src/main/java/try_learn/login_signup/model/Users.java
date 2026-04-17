package try_learn.login_signup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String firstName;
    @Column(nullable = false, unique = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String userName;
    private String phoneNumber;

    @Column(nullable=false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String password;


//    public User() {} // constructor // done by @NoArgsConstructor

}
