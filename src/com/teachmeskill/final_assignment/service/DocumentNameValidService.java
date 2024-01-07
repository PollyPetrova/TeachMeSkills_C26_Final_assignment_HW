package com.teachmeskill.final_assignment.service;

import java.io.File;
import java.io.IOException;
import static com.teachmeskill.final_assignment.utils.Constant.*;


public class DocumentNameValidService {
    //TODO вставить кастомные исключения
    public static void checkDocNumValid(File doc) {
// создаем объекты, где лежат пути к папкам
        File validCheck = new File(PATH_VALID_CHECK);
        File validInvoice = new File(PATH_VALID_INVOICE);
        File validOrder = new File(PATH_VALID_ORDER);
        File invalidDocName = new File(PATH_INVALID_DOC);
//проверяем формата файла, вызываем метод перемещения
        if(!doc.getName().endsWith(".txt")) {
            try {
                DocumentMoveService.moveDoc(doc, invalidDocName);
                System.out.println("Invalid document format");
                return;
            } catch (IOException e) {
                //TODO запись в логг e.getMessage());
            }
        }
//проверяем на валидность чека, вызываем метод перемещения
        if (doc.getName().matches(REGEXP_DOC_CHECK)) {
            try {
                DocumentMoveService.moveDoc(doc, validCheck);
                System.out.println("Successful check transfer");
                return;
            } catch (IOException e) {
                //TODO запись в логг e.getMessage());
            }
        }
//проверяем на валидность инвойса, вызываем метод перемещения
        if(doc.getName().matches(REGEXP_DOC_INVOICE)){
            try {
                DocumentMoveService.moveDoc(doc, validInvoice);
                System.out.println("Successful invoice transfer");
                return;
            } catch (IOException e){
                //TODO запись в логг e.getMessage());
            }
        }
//проверяем на валидность ордера, вызываем метод перемещения
        if (doc.getName().matches(REGEXP_DOC_ORDER)) {
            try {
                DocumentMoveService.moveDoc(doc, validOrder);
                System.out.println("Successful order transfer");
                return;
            } catch (IOException e) {
                //TODO запись в логг e.getMessage());
            }
        }

            //запись невалидных файлов, непрошедших предыдущие проверки, но подходящих по формату
            //вызываем метод перемещения
        if (!doc.getName().matches(REGEXP_DOC_CHECK)
                && !doc.getName().matches(REGEXP_DOC_ORDER)
                && !doc.getName().matches(REGEXP_DOC_INVOICE)) {
            try {
                DocumentMoveService.moveDoc(doc, invalidDocName);
                System.out.println("Invalid document name");
            } catch (IOException e) {
                //TODO запись в логг e.getMessage());
            }
        }
    }

}
