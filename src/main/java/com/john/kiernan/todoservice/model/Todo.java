package com.john.kiernan.todoservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TODOS")
public class Todo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long  id;
    private String user;
    private String desc;
    private Date lastUpdated;



    private boolean done;

    public Todo() {

    }

    public Todo(long id, String user, String desc, Date lastUpdated, boolean done) {
        this.id = id;
        this.user = user;
        this.desc = desc;
        this.lastUpdated = lastUpdated;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long  id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
