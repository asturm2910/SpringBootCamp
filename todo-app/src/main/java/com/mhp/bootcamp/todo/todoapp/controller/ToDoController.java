package com.mhp.bootcamp.todo.todoapp.controller;

import com.mhp.bootcamp.todo.todoapp.domainmodel.ToDo;
import com.mhp.bootcamp.todo.todoapp.service.ToDoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController()
public class ToDoController {
    private ToDoService service;

    @Autowired
    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @GetMapping("/todo/version")
    public ResponseEntity<String> getVersion() {
        return new ResponseEntity<String>("0.1", HttpStatus.OK);
    }

    @GetMapping("/todo")
    public ResponseEntity<Iterable<ToDo>> getAllToDos() {
        return new ResponseEntity<Iterable<ToDo>>(service.getAllToDos(), HttpStatus.OK);
    }

    @GetMapping("/todo/name/{name}")
    public ResponseEntity<Iterable<ToDo>> findByName(@PathVariable("name") String name) {
        log.debug("Controller findByName name: " + name);
        return new ResponseEntity<Iterable<ToDo>>(service.findByAsignee(name), HttpStatus.OK);
    }

    @PostMapping()
    public void addTodo(@RequestBody ToDo todo){
        log.info("Controller: addTodo: " + todo);
        service.saveTodo(todo);
    }

    @PutMapping()
    public void updateTodo(@RequestBody ToDo todo) {
        log.info("Controller: updateTodo " + todo);
        service.updateTodo(todo);

    }
}
