package try_learn.login_signup.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message="Title cannot be empty!")
    @Setter
    @Column(nullable = false)
    private String title;

    @NotBlank(message="Description cannot be empty!")
    @Setter
    @Column(nullable = false)
    private String description;

    @NotNull(message="dueDate cannot be blank!")
    @Setter
    @Column(nullable = false)
    private LocalDate dueDate;

    @NotNull(message="Priority cannot be empty!")
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM; //DEFAULT

    @NotNull(message="Status cannot be empty!")
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    // constructor with the changeable fields
    public Tasks(String title, String description, LocalDate dueDate, Priority priority, Status status){
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
    }
}
