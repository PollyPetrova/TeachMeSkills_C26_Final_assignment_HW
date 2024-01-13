package com.teachmeskills.final_assignment.service.validation;

import com.teachmeskills.final_assignment.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.teachmeskills.final_assignment.util.Constant.*;
import static com.teachmeskills.final_assignment.util.Constant.REGEXP_DOC_INVOICE;


public class DocumentNameValidService {

    public static void checkDocNumValid(File doc) {

        // создаем объекты класса Файл по путям к папкам
        File validCheck = new File(PATH_VALID_CHECK);
        File validInvoice = new File(PATH_VALID_INVOICE);
        File validOrder = new File(PATH_VALID_ORDER);
        File invalidDocName = new File(PATH_INVALID_DOC);

        //Проверяем формат файла
        if (doc.getName().endsWith(".txt")) {

            //проверяем на валидность чека, вызываем метод перемещения
            if (doc.getName().matches(REGEXP_DOC_CHECK)) {
                try {
                    DocumentMoveService.moveDoc(doc, validCheck);
                    Logger.logInfo(new Date(), "Successful check transfer of document: " + doc.getName());
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }

            //проверяем на валидность инвойса, вызываем метод перемещения
            if (doc.getName().matches(REGEXP_DOC_INVOICE)) {
                try {
                    DocumentMoveService.moveDoc(doc, validInvoice);
                    Logger.logInfo(new Date(), "Successful invoice transfer of document: " + doc.getName());
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }

            //проверяем на валидность ордера, вызываем метод перемещения
            if (doc.getName().matches(REGEXP_DOC_ORDER)) {
                try {
                    DocumentMoveService.moveDoc(doc, validOrder);
                    Logger.logInfo(new Date(), "Successful order transfer of document: " + doc.getName());
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }

            //запись невалидных файлов, непрошедших предыдущие проверки, но подходящих по формату
            //вызываем метод перемещения
            if (!doc.getName().matches(REGEXP_DOC_CHECK)
                    && !doc.getName().matches(REGEXP_DOC_ORDER)
                    && !doc.getName().matches(REGEXP_DOC_INVOICE)) {
                try {
                    DocumentMoveService.moveDoc(doc, invalidDocName);
                    Logger.logInfo(new Date(), "Invalid document name:"
                            + doc.getName() + ".\n File was sent to a directory with invalid files and has not been processed");
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }
            //Перемещаем объекты неверного формата
        } else {
            try {
                DocumentMoveService.moveDoc(doc, invalidDocName);
                Logger.logInfo(new Date(), "Invalid document format: " + doc.getName()
                        + ".\n File was sent to a directory with invalid files and has not been processed");
            } catch (IOException e) {
                Logger.logInfo(new Date(), e.getMessage());
            }
        }

    }

}
