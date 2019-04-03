package com.choose.entity;

import java.util.Date;

public class Choose {
    private Integer id;

    private Integer userId;

    private Integer seatNumber;

    private Admin admin;

    //选课时间
    private Date date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Choose{" +
                "id=" + id +
                ", userId=" + userId +
                ", seatNumber=" + seatNumber +
                ", admin=" + admin +
                ", date=" + date +
                '}';
    }
}