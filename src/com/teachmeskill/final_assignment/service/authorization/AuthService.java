package com.teachmeskill.final_assignment.service.authorization;

import com.teachmeskill.final_assignment.session.Session;
import com.teachmeskill.final_assignment.storage.StorageMock;

public class AuthService {

    public static Session doLogin(String login, String password){

        StorageMock storageMock=new StorageMock();
        String loginFromStorage=storageMock.getLogin();
        String passwordFromStorage=storageMock.getPassword();

        if(login.equals(loginFromStorage) && password.equals(passwordFromStorage)){
            //Вывести информацию о том, что логин и пароль введены верно
            return new Session();
        }else {
            //Вывести информацию о том, что логин или пароль введены неверно
            return null;
        }

    }

}
