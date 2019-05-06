package com.leo.common.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class TestStudent {
    @Id
    private Long id;
    @Property
    private int number;
    @Property
    private String name;
    @Generated(hash = 86451508)
    public TestStudent(Long id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }
    @Generated(hash = 1786807495)
    public TestStudent() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getNumber() {
        return this.number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
