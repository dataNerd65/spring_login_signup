package try_learn.login_signup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import try_learn.login_signup.model.Tasks;
import try_learn.login_signup.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    public final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public List<Tasks> getAllTasks(){
        return taskService.getAllTasks();
    }
//    @PostMapping // this just creates but no response
//    public Tasks createTask(@RequestBody Tasks task){
//        return taskService.createTask(task);
//    }
    @PostMapping
    public ResponseEntity<Tasks> createTask(@Valid @RequestBody Tasks task){
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }
}
