package com.choose.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Admin {
    private Integer id;
    @DateTimeFormat(pattern = "HH:mm")
    private Date beginTime;
    @DateTimeFormat(pattern = "HH:mm")
    private Date endTime;

    private Integer totalSeat;

    private Integer day;

    private Course course;

    private Teacher teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", totalSeat=" + totalSeat +
                ", day=" + day +
                ", course=" + course +
                ", teacher=" + teacher +
                '}';
    }
}