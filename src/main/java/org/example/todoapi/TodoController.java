package org.example.todoapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList ;
    public TodoController(){
        todoList= new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo1",1));
        todoList.add(new Todo(2,false,"Todo2",2));
    }
    @GetMapping("/")
//    @ResponseStatus (HttpStatus.ACCEPTED)
    public ResponseEntity< List<Todo>>getTodos(@RequestParam (required = false , defaultValue = "true") boolean isCompleted){
        System.out.println("Incoming Query param "+ isCompleted);
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }
    @PostMapping("/")

    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Todo> createTodo(@RequestBody Todo newTodo){
           todoList.add(newTodo);
           return  ResponseEntity.status(HttpStatus.CREATED).body(newTodo);
    }
       @GetMapping("/{todoId}")
    public ResponseEntity<?>getTodoById( @PathVariable Long todoId){
        for (Todo todo : todoList){
            if (todo.getId() == (todoId)){
                return  ResponseEntity.ok(todo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\": \"Todo Not Found\"}") ;
       }
}
