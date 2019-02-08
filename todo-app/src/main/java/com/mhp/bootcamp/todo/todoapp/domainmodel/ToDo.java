package com.mhp.bootcamp.todo.todoapp.domainmodel;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="todo")
@Data
public class ToDo {
    @Id
    @GeneratedValue
    @Column
    int id;

    @Column
    String title;

    @Column
    String description;

    @Column
    String asignee;

    @Column
    String status;
}
