package try_learn.login_signup.service;


import org.springframework.stereotype.Service;
import try_learn.login_signup.Enum.Priority;
import try_learn.login_signup.Enum.Status;
import try_learn.login_signup.model.Tasks;
import try_learn.login_signup.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor its definition is as below in the constructor, I injected the task repository
@Service
public class TaskService {

    public final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    // Methods I need

    /*
    Get all Tasks
    * */
    public List<Tasks> getAllTasks(){
        return taskRepository.findAll();
    }
    /*
    Find task by id
    * */
    public Optional <Tasks> findTaskById(Long id){
        return taskRepository.findById(id);
    }
    /*
    Create a Task
    * */
    public Tasks createTask(Tasks task){
        if(task.getPriority() == null){
            task.setPriority(Priority.MEDIUM);
        }
        if(task.getStatus() == null){
            task.setStatus(Status.PENDING);
        }
        return taskRepository.save(task);
    }
    /*
    delete a Task
    * */
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
