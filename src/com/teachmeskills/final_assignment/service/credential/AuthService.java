package com.teachmeskills.final_assignment.service.credential;

import com.teachmeskills.final_assignment.coder.Coder;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.session.Session;
import com.teachmeskills.final_assignment.storage.StorageMock;

import java.util.Date;

public class AuthService {

    //A method for verifying the entered username and password with the contents of the database
    public static Session doLogin(String login, String password) {

        Logger.logInfo(new Date(), "Start of authorization.");
        //Items from the database are decrypted for further comparison
        StorageMock storageMock = new StorageMock();
        String loginFromStorage = Coder.decode(storageMock.getLogin());
        String passwordFromStorage = Coder.decode(storageMock.getPassword());

        //Comparison
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
