package com.ruoyi.project.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test01 {
    public static void main(String[] args) {
        test01();
    }

    private static void test01() {
        List<User1> list = getList();
        Optional<User1> optional = list.stream().filter(a -> a.getAge() == 2).findAny();
        if (optional.isPresent()) {
            System.out.println("optional.isPresent()=" + optional.get());
        }
    }

    private static List<User1> getList() {
        List<User1> list = new ArrayList<>();
        list.add(new User1(2));
        list.add(new User1(6));
        list.add(new User1(3));
        list.add(new User1(1));
        list.add(new User1(9));
        return list;
    }

    public static class User1 {
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
}
