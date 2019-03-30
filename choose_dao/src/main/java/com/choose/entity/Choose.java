package com.choose.entity;

public class Choose {
    private Integer id;

    private Integer userId;

    private Integer seatNumber;

    private Admin admin;

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

    @Override
    public String toString() {
        return "Choose{" +
                "id=" + id +
                ", userId=" + userId +
                ", seatNumber=" + seatNumber +
                ", admin=" + admin +
                '}';
    }
}