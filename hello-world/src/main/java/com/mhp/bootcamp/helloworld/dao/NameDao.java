package com.mhp.bootcamp.helloworld.dao;

import com.mhp.bootcamp.helloworld.domain.Name;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class NameDao {

    private List<Name> mydb = new LinkedList<>();

    public void saveName(Name name) {
        mydb.add(name);


        System.out.println("State of save: " + mydb);
    }

    public List getAll() {
        return mydb;
    }
}
