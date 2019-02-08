package com.mhp.bootcamp.todo.todoapp.service;

import com.mhp.bootcamp.todo.todoapp.dao.ToDoDao;
import com.mhp.bootcamp.todo.todoapp.domainmodel.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ToDoService {

    private ToDoDao dao;

    public ToDoService(ToDoDao dao) {
        this.dao = dao;
    }

    public void saveTodo(ToDo todo) {
        log.debug("Service: forwarding " + todo +" to DAO");
        dao.save(todo);
        log.debug("Saved!");
    }

    public Iterable<ToDo> getAllToDos() {
        log.debug("Service: getAllToDos");
        return dao.findAll();
    }

    public Iterable<ToDo> findByAsignee(String asignee) {
        log.debug("Service: findByName name: " + asignee);
        return dao.findByAsignee(asignee);
    }

    public void updateTodo(ToDo todo) {
        log.debug("Servcie: updateTodo: " + todo);
        dao.save(todo);
    }



}
