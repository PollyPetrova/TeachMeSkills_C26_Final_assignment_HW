package com.teachmeskills.final_assignment.service;

import com.teachmeskills.final_assignment.logger.Logger;

import java.util.Date;

import static com.teachmeskills.final_assignment.utils.Constant.*;

public class Validation {
    //Метод для суммирования транзакций
    public static void doValidation(){
        double checkAmountAll = 0;
        try {
            checkAmountAll = StatisticService.calculateStatisticCheck(PATH_VALID_CHECK);
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }

        double invoiceAmountAll = 0;
        try {
            invoiceAmountAll = StatisticService.calculateStatisticInvoice(PATH_VALID_INVOICE);
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }

        double orderAmountAll = 0;
        try {
            orderAmountAll = StatisticService.calculateStatisticOrder(PATH_VALID_ORDER);
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }

        try {
            StatisticService.calculateStatisticFinal(checkAmountAll, invoiceAmountAll, orderAmountAll);
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }
    }

}
