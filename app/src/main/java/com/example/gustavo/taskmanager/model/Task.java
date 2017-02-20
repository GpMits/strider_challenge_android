package com.example.gustavo.taskmanager.model;

/**
 * Created by Gustavo on 17/02/2017.
 */

public class Task {

    private int id;
    private String description;
    private User user;
    private TaskStatus taskStatus;
    private String img;

    public Task() {
        this.id = 0;
        this.description = "none";
        this.user = null;
        this.taskStatus = null;
    }

    public Task(int id) {
        this.id = id;
    }

    public Task(String description) {
        this.description = description;
    }

    public Task(String description, User usr, TaskStatus tStatus) {
        this.description = description;
        this.user = usr;
        this.taskStatus = tStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User usr) {
        this.user = usr;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

}
