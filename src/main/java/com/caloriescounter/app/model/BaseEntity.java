package com.caloriescounter.app.model;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 20:32.
 */


public class BaseEntity {
    protected Integer id;

    public BaseEntity() {
    }

    public BaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew() {
        return (this.id == null);
    }
}
