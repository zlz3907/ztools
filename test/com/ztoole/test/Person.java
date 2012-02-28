package com.ztoole.test;

import java.util.Date;
import java.util.List;

public class Person {
    private String name;
    private String age;
    private String sex;
    private List<House> houses;
    private List<String> children;
    private List<Object> objlist;
    private Person father;
    private int wifeCount;
    private Date date;
    private Object[] arr;
    private Integer i;
    private boolean b;
    private Boolean bObj;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public Boolean getbObj() {
        return bObj;
    }

    public void setbObj(Boolean bObj) {
        this.bObj = bObj;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public Object[] getArr() {
        return arr;
    }

    public void setArr(Object[] arr) {
        this.arr = arr;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWifeCount() {
        return wifeCount;
    }

    public void setWifeCount(int wifeCount) {
        this.wifeCount = wifeCount;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public List<Object> getObjlist() {
        return objlist;
    }

    public void setObjlist(List<Object> objlist) {
        this.objlist = objlist;
    }

    private List<Integer> thr;

    public List<Integer> getThr() {
        return thr;
    }

    public void setThr(List<Integer> thr) {
        this.thr = thr;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    public Person() {
    }

    public Person(String name, String age, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "{name:" + name + ", age:" + age + ", houses:" + houses;
    }
}
