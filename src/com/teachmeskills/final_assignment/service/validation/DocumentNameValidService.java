package com.teachmeskills.final_assignment.service.validation;

import com.teachmeskills.final_assignment.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.teachmeskills.final_assignment.util.Constant.*;
import static com.teachmeskills.final_assignment.util.Constant.REGEXP_DOC_INVOICE;


public class DocumentNameValidService {

    public static void checkDocNumValid(File doc) {

        // creating objects of the File class by folder paths
        File validCheck = new File(PATH_VALID_CHECK);
        File validInvoice = new File(PATH_VALID_INVOICE);
        File validOrder = new File(PATH_VALID_ORDER);
        File invalidDocName = new File(PATH_INVALID_DOC);

        //Checking the file format
        if (doc.getName().endsWith(".txt")) {

            //we check the validity of the receipt, call the transfer method
            if (doc.getName().matches(REGEXP_DOC_CHECK)) {
                try {
                    DocumentMoveService.moveDoc(doc, validCheck);
                    Logger.logInfo(new Date(), "Successful check transfer of document: " + doc.getName());
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }

            //we check the validity of the invoice, call the transfer method
            if (doc.getName().matches(REGEXP_DOC_INVOICE)) {
                try {
                    DocumentMoveService.moveDoc(doc, validInvoice);
                    Logger.logInfo(new Date(), "Successful invoice transfer of document: " + doc.getName());
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }

            //we check the validity of the order, call the move method
            if (doc.getName().matches(REGEXP_DOC_ORDER)) {
                try {
                    DocumentMoveService.moveDoc(doc, validOrder);
                    Logger.logInfo(new Date(), "Successful order transfer of document: " + doc.getName());
                } catch (IOException e) {
                    Logger.logInfo(new Date(), e.getMessage());
                }
            }

            //recording invalid files that have not passed previous checks, but are suitable in format
            //calling the move method
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
            //Moving objects of the wrong format
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
