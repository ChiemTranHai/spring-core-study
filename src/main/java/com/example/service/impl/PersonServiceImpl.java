package com.example.service.impl;

import com.example.annotation.Auditable;
import com.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl {

    @Auditable("Person")
    public void insertPerson(Person person) {
        System.out.println("insertPerson ne "+person);
        // TODO: do st after insert
    }
}
