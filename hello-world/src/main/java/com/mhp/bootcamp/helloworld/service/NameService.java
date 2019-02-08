package com.mhp.bootcamp.helloworld.service;

import com.mhp.bootcamp.helloworld.domain.Name;
import com.mhp.bootcamp.helloworld.dao.NameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NameService {

    private NameDao nameDao;

    @Autowired
    public NameService (NameDao nameDao) {
        this.nameDao = nameDao;
    }

    public void saveName(String name, String firstname) {
        Name aName = new Name(name, firstname);
        nameDao.saveName(aName);
    }

    public void saveName (Name name) {
        nameDao.saveName(name);
    }

    public List getAll() {
        return nameDao.getAll();
    }
 }
