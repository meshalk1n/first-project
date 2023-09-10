package org.example.models;

public class Person {
    private int id;
    private String name;
    private String comment;
    private int age;
    public Person(){

    }
    public Person(int id, String name, int age, String comment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
