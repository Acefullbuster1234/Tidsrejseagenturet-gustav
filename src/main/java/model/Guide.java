package model;

import database.GuideRepository;

public class Guide {
    private final int id;
    private String name;
    private String speciality;

    public Guide(int id, String name, String speciality){
        this.id = id;
        this.name = name;
        this.speciality = speciality;
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

    public String getSpeciality(){
        return speciality;
    }

    public void setSpeciality(String speciality){
        this.speciality = speciality;
    }

}
