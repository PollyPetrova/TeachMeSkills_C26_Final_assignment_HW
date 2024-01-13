package com.teachmeskills.final_assignment.service.credential;

import com.teachmeskills.final_assignment.coder.Coder;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.session.Session;
import com.teachmeskills.final_assignment.storage.StorageMock;

import java.util.Date;

public class AuthService {

    //Метод для проверки введенных логина и пароля с содержимым базы данных
    public static Session doLogin(String login, String password) {

        Logger.logInfo(new Date(), "Start of authorization.");
        //Элементы из базы данных дешифруются для дальнейшего сравнения
        StorageMock storageMock = new StorageMock();
        String loginFromStorage = Coder.decode(storageMock.getLogin());
        String passwordFromStorage = Coder.decode(storageMock.getPassword());

        //Сравнение
        if (!login.equals(loginFromStorage)) {
            Logger.logInfo(new Date(), "Login was entered wrongly.");
            System.out.println("Login or password was entered wrongly! Try again.");
            return null;
        } else if (!password.equals(passwordFromStorage)) {
            Logger.logInfo(new Date(), "Password was entered wrongly.");
            System.out.println("Login or password was entered wrongly! Try again.");
            return null;
        } else {
            Logger.logInfo(new Date(), "Login and password were entered correctly.");
            Logger.logInfo(new Date(), "Authorization was completed successfully.");
            Logger.logInfo(new Date(), "End of authorization.");
            return new Session();
        }

    }

}
