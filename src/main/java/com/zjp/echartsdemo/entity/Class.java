package com.zjp.echartsdemo.entity;

public class Class {
    private int id;
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    private int boys;
    private int girls;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoys() {
        return boys;
    }

    public void setBoys(int boys) {
        this.boys = boys;
    }

    public int getGirls() {
        return girls;
    }

    public void setGirls(int girls) {
        this.girls = girls;
    }

    @Override
    public String toString() {
        return "Class [id=" + id + ", className=" + className + ", boys=" + boys + ", girls=" + girls + "]";
    }
}
