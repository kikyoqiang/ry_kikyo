package com.ruoyi.project.demo.model;

public class User1 {
    private String name;
    private Integer age;

    public User1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public User1(Integer age) {
        this.age = age;
    }

    public User1() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String
    toString() {
        return "User1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
