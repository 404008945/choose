package com.choose.info;

import java.util.List;

public class WeekInfo {
    private Integer day;
    private List<AdminInfo> infos;

    public WeekInfo(Integer day, List<AdminInfo> infos) {
        this.day = day;
        this.infos = infos;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public List<AdminInfo> getInfos() {
        return infos;
    }

    public void setInfos(List<AdminInfo> infos) {
        this.infos = infos;
    }

    @Override
    public String toString() {
        return "WeekInfo{" +
                "day=" + day +
                ", infos=" + infos +
                '}';
    }
}
