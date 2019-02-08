package com.mhp.bootcamp.todo.todoapp.dao;


import com.mhp.bootcamp.todo.todoapp.domainmodel.ToDo;
import org.springframework.data.repository.CrudRepository;


public interface ToDoDao extends CrudRepository<ToDo, Integer> {
    public Iterable<ToDo> findByAsignee(String asignee);





}
