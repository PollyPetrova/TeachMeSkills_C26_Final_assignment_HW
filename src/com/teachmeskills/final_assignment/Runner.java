package com.teachmeskills.final_assignment;
import com.teachmeskills.final_assignment.custom_exeption.CheckFileException;
import com.teachmeskills.final_assignment.custom_exeption.CheckSessionException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.service.AuthService;
import com.teachmeskills.final_assignment.service.DocumentSortService;
import com.teachmeskills.final_assignment.service.Validation;
import com.teachmeskills.final_assignment.session.Session;

import java.util.Date;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login:");
        String login = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        //Right login:    PolinaLiza;
        //      password: 123987;

        Session session= AuthService.doLogin(login, password);

        /*System.out.println(Coder.code("PolinaLiza"));
        System.out.println(Coder.code("123987"));*/

        //Считываем путь к папке с файлами
        System.out.println("Enter path to the Directory:");
        String resourcePath = scanner.nextLine();


        //вызов метода для сортировки файлов
        try {
            Logger.logInfo(new Date(), "The beginning of file processing");
            DocumentSortService.sortDocument(resourcePath, session);
            Validation.doValidation();
            Logger.logInfo(new Date(), "End of file processing");
            Logger.logInfo(new Date(), "End of programme!");
        } catch (CheckSessionException | CheckFileException e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
            return;
        }


        scanner.close();

    }

}
