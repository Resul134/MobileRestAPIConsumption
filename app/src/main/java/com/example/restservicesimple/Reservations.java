package com.example.restservicesimple;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public class Reservations  implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fromTime")
    @Expose
    private Integer fromTime;
    @SerializedName("toTime")
    @Expose
    private Integer toTime;
    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("purpose")
    @Expose
    private String purpose;
    @SerializedName("roomId")
    @Expose
    private Integer roomId;


    public Reservations(int fromTime, int toTime, String userId, String purpose, int roomId) {
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.userId = userId;
        this.purpose = purpose;
        this.roomId = roomId;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFromTime() {
        return fromTime;
    }

    public void setFromTime(Integer fromTime) {
        this.fromTime = fromTime;
    }

    public Integer getToTime() {
        return toTime;
    }

    public void setToTime(Integer toTime) {
        this.toTime = toTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Reservations{" +
                "fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", userId='" + userId + '\'' +
                ", purpose='" + purpose + '\'' +
                ", roomId=" + roomId +
                '}';
    }
}
