package com.design.uniqueidgen.entity;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
public class IdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id ;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

}


