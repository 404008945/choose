package com.choose.info;

public class ChooseInfo {
    private String beginTime;
    private String endTime;
    private Integer day;
    private String courseName;
    private String teacherName;
    //所选座位
    private Integer seat;
    //课程id用于删除使用
    private Integer adminId;
    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "ChooseInfo{" +
                "beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", day=" + day +
                ", courseName='" + courseName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", seat=" + seat +
                ", adminId=" + adminId +
                '}';
    }
}
