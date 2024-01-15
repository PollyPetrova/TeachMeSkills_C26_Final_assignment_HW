package com.teachmeskills.final_assignment.service.validation;

import com.teachmeskills.final_assignment.custom_exeption.CheckFileException;
import com.teachmeskills.final_assignment.custom_exeption.CheckSessionException;
import com.teachmeskills.final_assignment.logger.Logger;
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

                //Проверяем директорию с файлами на существование и наполненность
                if (!resource.exists()) {
                    System.out.println("File doesn't exist! Enter another one path to the file.");
                    Logger.logInfo(new Date(), "File doesn't exist. Fail in file processing.");
                    throw new CheckFileException("File doesn't exist.");

                } else if (resource.length() == 0) {
                    System.out.println("File is empty! Try again and enter another one path to the file.");
                    Logger.logInfo(new Date(), "File is empty. Fail in file processing.");
                    throw new CheckFileException("File is empty.");

                } else {
                    Logger.logInfo(new Date(), "Start of sorting files!");
                    //Идем по массиву и вызываем метод проверки названия
                    for (File file : resourceArray) {
                        DocumentNameValidService.checkDocNumValid(file);
                    }
                    Logger.logInfo(new Date(), "End of sorting files!");
                }

            } else {
                //Если сессия мертва, то вывод на экран и исключение
                System.out.println("Session was finished. Authorized again!");
                Logger.logInfo(new Date(), "Session was finished. Fail in file processing.");
                throw new CheckSessionException("Session was finished.");
            }
    }

}