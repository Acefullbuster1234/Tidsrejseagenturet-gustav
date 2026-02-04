package model;

import database.TimemachineRepository;

public class Timemachine {
    private final int id;
    private String name;
    private int capacity;
    private String status;
    public Timemachine(int id, String name, int capacity, String status){
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.status = status;
    }
    public int getId(){
        return id;
    }


    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
