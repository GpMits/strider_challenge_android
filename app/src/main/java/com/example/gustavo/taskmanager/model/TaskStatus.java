package com.example.gustavo.taskmanager.model;

public class TaskStatus {

    private int id;
    private String name;

    public TaskStatus (){
        this.id = 0;
        this.name = "none";
    }

    public TaskStatus(int id) {
        this.id = id;
    }

    public TaskStatus(String name) {
        this.name = name;
    }

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

}