package try_learn.login_signup.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import try_learn.login_signup.model.Tasks;
import try_learn.login_signup.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    public final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public List<Tasks> getAllTasks(){
        return taskService.getAllTasks();
    }
//    @PostMapping // this just creates but no response
//    public Tasks createTask(@RequestBody Tasks task){
//        return taskService.createTask(task);
//    }
    @GetMapping("/getTaskByTitle")
    public Tasks getTaskByTitle(@RequestParam String title){
        return taskService.findTaskByTitle(title);
    }

    @PostMapping("/addTask")
    public ResponseEntity<String> createTask(@Valid @RequestBody Tasks task){
        taskService.createTask(task);
        return ResponseEntity.ok("Task created Successfully!" + " With ID " + task.getId());
    }

//    @DeleteMapping("/{id}")
//    public void deleteTask(@PathVariable Long id){
//        taskService.deleteTask(id);
//    }
      @DeleteMapping("/delete/{id}")
      public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task with id " + id +" deleted successfully");
      }
}
