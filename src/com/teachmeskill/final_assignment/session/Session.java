package com.teachmeskill.final_assignment.session;

import com.teachmeskill.final_assignment.utils.Constant;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.stream.Collectors;

public class Session {

    private String accessToken;
    private Date expDate;

    public Session() {
        setAccessToken();
        setExpDate();
    }

    //Метод для проверки существования сессии
    public boolean isSessionAlive(){
        if (this.accessToken.length()== Constant.ACCESS_TOKEN_LENGTH && this.expDate.after(new Date())){
            return true;
        }else {
            return false;
        }
    }

    //Метод для определения токена для сессии
    //!Андрей говорил просто переписать этот метод, мы пока не проходили как его реализовать
    private void setAccessToken(){
        String symbols="abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken=new Random().ints(16,0,symbols.length())
                                     .mapToObj(symbols::charAt)
                                     .map(Object::toString)
                                     .collect(Collectors.joining());
    }

    //Метод для определения времени, когда сессия прекратит свое существование
    private void setExpDate(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);

        this.expDate=calendar.getTime();
    }

}
