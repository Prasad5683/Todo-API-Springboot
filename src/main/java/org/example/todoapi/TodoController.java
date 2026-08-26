package org.example.todoapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
     private TodoService todoService1; //i want fakeTodoService in this
     private  TodoService todoService2; // i want AnotherTodoService In This

//    private TodoService todoService; // this is Composition means you can kept instance to another class as a class property

    private static List<Todo> todoList ;
    public TodoController(
            @Qualifier("FakeTodoService") TodoService todoService1,
            @Qualifier("AnotherTodoService") TodoService todoService2){
        this.todoService1 = todoService1;
        this.todoService2= todoService2;

        todoList= new ArrayList<>();
        todoList.add(new Todo(1,false,"Todo1",1));
        todoList.add(new Todo(2,false,"Todo2",2));
//        this.toddoService = new ToddoService();
    }
    @GetMapping("/")
//    @ResponseStatus (HttpStatus.ACCEPTED)

    public ResponseEntity< List<Todo>>getTodos(@RequestParam (required = false ) boolean isCompleted){
        System.out.println("Incoming Query param "+ isCompleted+ " "+ this.todoService2.doSomething());
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
