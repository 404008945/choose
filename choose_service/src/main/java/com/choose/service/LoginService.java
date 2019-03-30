package com.choose.service;

public interface LoginService {
    //type=0学生，1代表非学生
    <T> T login(String account,String password,int type);
}
