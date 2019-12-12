package com.hust.hustsearch.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author :younghao
 * @ClassName: Province
 * @Description: TODO
 * @date : 12/11/19  10:29 AM
 */

@Entity
public class Province {
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

    public Province() {
    }
}
