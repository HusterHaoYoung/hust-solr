package com.hust.hustsearch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ResearchDirection {
    private int id;
    private String name;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResearchDirection(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ResearchDirection() {
    }

    @Override
    public String toString() {
        return "ResearchDirection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
