package com.example.restservicesimple;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rooms implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("capacity")
    @Expose
    private Integer capacity;
    @SerializedName("remarks")
    @Expose
    private String remarks;

    public Rooms(String name, String description, int capacity, String remarks) {

        this.name = name;
        this.description = description;
        this.capacity = capacity;
        this.remarks = remarks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @Override
    public String toString() {
        return "Rooms{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", capacity=" + capacity +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
