package com.teachmeskills.final_assignment.session;

import com.teachmeskills.final_assignment.util.Constant;

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

    //A method for verifying the existence of a session
    public boolean isSessionAlive() {
        if (this.accessToken.length() == Constant.ACCESS_TOKEN_LENGTH && this.expDate.after(new Date())) {
            return true;
        } else {
            return false;
        }
    }

    //Method for determining the token for the session
    //!Андрей говорил просто переписать этот метод, мы пока не проходили как его реализовать
    private void setAccessToken() {
        String symbols = "abcdefghijklmnopqrstuvwxyz0123456789";

        this.accessToken = new Random().ints(16, 0, symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    //A method for determining the time when a session will cease to exist
    private void setExpDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, 1);

        this.expDate = calendar.getTime();
    }

}
