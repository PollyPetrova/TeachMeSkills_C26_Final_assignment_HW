package com.teachmeskill.final_assignment.service.authorization;

import com.teachmeskill.final_assignment.coder.Coder;
import com.teachmeskill.final_assignment.session.Session;
import com.teachmeskill.final_assignment.storage.StorageMock;

public class AuthService {

    //Метод для проверки введенных логина и пароля с содержимым базы данных
    public static Session doLogin(String login, String password){

        //Элементы из базы данных дешифруются для дальнейшего сравнения
        StorageMock storageMock=new StorageMock();
        String loginFromStorage= Coder.decode(storageMock.getLogin());
        String passwordFromStorage=Coder.decode(storageMock.getPassword());

        //Сравнение
        if(login.equals(loginFromStorage) && password.equals(passwordFromStorage)){
            //Вывести информацию о том, что логин и пароль введены верно
            System.out.println("Login and password were entered correctly!");
            return new Session();
        }else {
            //Вывести информацию о том, что логин или пароль введены неверно
            return null;
        }

    }

}
