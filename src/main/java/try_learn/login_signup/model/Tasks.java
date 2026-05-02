package try_learn.login_signup.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import try_learn.login_signup.Enum.Priority;
import try_learn.login_signup.Enum.Status;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Getter
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    @Column(nullable = false)
    private LocalDate dueDate;

    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM; //DEFAULT

    @Setter
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    // constructor with the changeable fields
    public Tasks(String title, String description, LocalDate dueDate, Priority priority){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }
}
