package com.caloriescounter.app.model;

/**
 * Created by Aleksandr_Shakhov on 05.05.17 20:34.
 */


public class NamedEntity extends BaseEntity {

    protected String name;

    public NamedEntity() {}

    protected NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
