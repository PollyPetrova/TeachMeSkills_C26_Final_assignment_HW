package com.teachmeskills.final_assignment.service.validation;

import com.teachmeskills.final_assignment.custom_exeption.CheckFileException;
import com.teachmeskills.final_assignment.custom_exeption.CheckSessionException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.service.validation.DocumentNameValidService;
import com.teachmeskills.final_assignment.session.Session;

import java.io.File;
import java.util.Date;

public class DocumentSortService {

    public static void sortDocument(String path, Session session) throws CheckFileException, CheckSessionException {
        //Проверяем жива ли сессия
            if (session.isSessionAlive()) {
                //Создаем массив файлов
                File resource = new File(path);
                File[] resourceArray = resource.listFiles();
                Logger.logInfo(new Date(), "Start of sorting files!");

                //Проверяем директорию с файлами на существование и наполненность
                if (!resource.exists()) {
                    throw new CheckFileException("File doesn't exist! Enter another one path to the file");
                } else if (resource.length() == 0) {
                    throw new CheckFileException("File is empty. Try again and enter another one path to the file");
                } else {
                    //Идем по массиву и вызываем метод проверки названия
                    for (File file : resourceArray) {
                        DocumentNameValidService.checkDocNumValid(file);
                    }
                    Logger.logInfo(new Date(), "End of sorting files!");
                }

            } else {
                //Если сессия мертва, то вывод на экран и исключение
                System.out.println("Session was finished. Authorized again!");
                throw new CheckSessionException("Session was finished.");
            }
    }

}