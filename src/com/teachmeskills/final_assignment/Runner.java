package com.teachmeskills.final_assignment;
import com.teachmeskills.final_assignment.custom_exeption.CheckFileException;
import com.teachmeskills.final_assignment.custom_exeption.CheckSessionException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.service.credential.AuthService;
import com.teachmeskills.final_assignment.service.validation.DocumentSortService;
import com.teachmeskills.final_assignment.service.statistic.StatisticWriterService;
import com.teachmeskills.final_assignment.session.Session;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

       try (Scanner scanner = new Scanner(System.in)) {
           //Сбор кредов клиента
           System.out.println("Enter login:");
           String login = scanner.nextLine();
           System.out.println("Enter password:");
           String password = scanner.nextLine();

           //Right login:    PolinaLiza;
           //   password: 123987;

           //Создание сессии, авторизация
           Session session = AuthService.doLogin(login, password);

        /*System.out.println(Coder.code("PolinaLiza"));
        System.out.println(Coder.code("123987"));*/

           if (session != null) {
               //Считываем путь к папке с файлами
               System.out.println("Enter path to the Directory:");
               String resourcePath = scanner.nextLine();

               //Вызов методов для сортировки файлов, записи статистики
               try {
                   Logger.logInfo(new Date(), "The beginning of file processing.");

                   DocumentSortService.sortDocument(resourcePath, session);
                   StatisticWriterService.compileStatistics();

                   Logger.logInfo(new Date(), "End of file processing.");
                   Logger.logInfo(new Date(), "End of programme!");
               } catch (CheckSessionException | CheckFileException e) {
                   Logger.errorInfo(new Date(), e.getMessage(), e);
               }

           } else {
               Logger.logInfo(new Date(),"Session wasn't found.");
           }

       } catch (InputMismatchException e) {
           Logger.errorInfo(new Date(), "Scanner closing error", e);
       }

    }

}
