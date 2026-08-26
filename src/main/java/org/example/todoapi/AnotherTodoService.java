package org.example.todoapi;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("AnotherTodoService")

public class AnotherTodoService implements  TodoService {
    @Override
    public String  doSomething(){
        return "Something From Another todo Servise";
    }
}
