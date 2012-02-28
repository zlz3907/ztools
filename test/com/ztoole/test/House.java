package com.ztoole.test;

import java.util.List;

public class House {
    private String name;
    private String add;
    private int size;
    private List<Room> rooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public House() {
    }

    public House(String name, String add, int size) {
        this.name = name;
        this.add = add;
        this.size = size;
    }
}
