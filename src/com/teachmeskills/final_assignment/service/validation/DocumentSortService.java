package com.teachmeskills.final_assignment.service.validation;

import com.teachmeskills.final_assignment.custom_exeption.CheckFileException;
import com.teachmeskills.final_assignment.custom_exeption.CheckSessionException;
import com.teachmeskills.final_assignment.logger.Logger;
import com.teachmeskills.final_assignment.session.Session;

import java.io.File;
import java.util.Date;

public class DocumentSortService {

    public static void sortDocument(String path, Session session) throws CheckFileException, CheckSessionException {
        //Checking if the session is alive
            if (session.isSessionAlive()) {
                //Creating an array of files
                File resource = new File(path);
                File[] resourceArray = resource.listFiles();

                //We check the directory with files for existence and fullness
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
                    //Go through the array and call the name verification method
                    for (File file : resourceArray) {
                        DocumentNameValidService.checkDocNumValid(file);
                    }
                    Logger.logInfo(new Date(), "End of sorting files!");
                }

            } else {
                //If the session is dead, then the output is displayed and an exception is thrown
                System.out.println("Session was finished. Authorized again!");
                Logger.logInfo(new Date(), "Session was finished. Fail in file processing.");
                throw new CheckSessionException("Session was finished.");
            }
    }

}