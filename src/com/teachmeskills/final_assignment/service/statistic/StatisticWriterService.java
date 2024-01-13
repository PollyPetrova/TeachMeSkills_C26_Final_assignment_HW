package com.teachmeskills.final_assignment.service.statistic;

import com.teachmeskills.final_assignment.logger.Logger;

import java.util.Date;

import static com.teachmeskills.final_assignment.util.Constant.*;

public class StatisticWriterService {
    //Метод для составления статистики
    public static void compileStatistics() {
        double checkAmountAll = 0;
        try {
            checkAmountAll = StatisticService.calculateStatisticCheck(PATH_VALID_CHECK);
            Logger.logInfo(new Date(), "Annual amount of checks received");
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }

        double invoiceAmountAll = 0;
        try {
            invoiceAmountAll = StatisticService.calculateStatisticInvoice(PATH_VALID_INVOICE);
            Logger.logInfo(new Date(), "Annual amount of invoice received");
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }

        double orderAmountAll = 0;
        try {
            orderAmountAll = StatisticService.calculateStatisticOrder(PATH_VALID_ORDER);
            Logger.logInfo(new Date(), "Annual amount of order received");
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }

        try {
            StatisticService.calculateStatisticFinal(checkAmountAll, invoiceAmountAll, orderAmountAll);
            Logger.logInfo(new Date(), "Annual amount of all received and recorded");
        } catch (Exception e) {
            Logger.errorInfo(new Date(), e.getMessage(), e);
        }
    }

}
