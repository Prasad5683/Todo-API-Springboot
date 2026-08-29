package org.example.todoapi;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("FakeTodoService")

public class FakeTodoService implements TodoService {
    @Override
    @TimeMonitor
   public String  doSomething (){
       return  "SomeThing";
   }


}
