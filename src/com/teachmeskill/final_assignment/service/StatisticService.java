package com.teachmeskill.final_assignment.service;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.teachmeskill.final_assignment.utils.Constant.*;


public class StatisticService {

    public static double calculateStatisticCheck(String path) throws Exception {

        //создаем массив Check файлов
        File directCheck = new File(path);
        File[] directCheckArray = directCheck.listFiles();
        double amountAllCheck = 0; // переменная для суммирования транзакций

        //идем по массиву
        for (File check : directCheckArray) {
            String checkPath = check.getPath();

            //используем трай виз ресурс и открываем поток для считывания из файла
            try (BufferedReader checkReader = Files.newBufferedReader(Path.of(checkPath))) {
                String stringStatistic;

                while (!((stringStatistic = checkReader.readLine()) == null)) { //не работает вариант (string_1 = checkReader.readLine()) != null)
                    //проверяем строки не пустые строки на соответствие регулярке
                    if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_CHECK)) {
                        System.out.println(stringStatistic);
                        double amount; // переменная для суммы из конкретного файла
                        String[] statistic = stringStatistic.split(" "); // массив из строки с суммой
                        amount = Double.parseDouble(statistic[4].replace(",", ".")); // преобразование и запись в переменную суммы перевода
                        amountAllCheck = amountAllCheck + amount;
                        System.out.println(amountAllCheck);
                    }
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Conversion error");
            } catch (Exception e) {
                throw new Exception("Unexpected error");
            }
        }
        amountAllCheck = amountAllCheck * EURO_TO_USD;
        return amountAllCheck;
    }

//работаю над ним..
//   public static double calculateStatisticInvoice(String path) throws Exception {
//        File directInvoice = new File(path);
//        File[] directInvoiceArray = directInvoice.listFiles();
//        double amountAllInvoice = 0; // переменная для суммирования транзакций
//
//        //идем по массиву
//        for (File invoice : directInvoiceArray) {
//            String invoicePath = invoice.getPath();
//
//            //используем трай виз ресурс и открываем поток для считывания из файла
//            try (BufferedReader invoiceReader = Files.newBufferedReader(Path.of(invoicePath))) {
//                String stringStatistic;
//
//                while (!((stringStatistic = invoiceReader.readLine()) == null)) { //не работает вариант (string_1 = checkReader.readLine()) != null)
//                    //проверяем не пустые строки на соответствие регулярке
//                    if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_INVOICE)) {
//                        System.out.println(stringStatistic);
//                         double amount;// переменная для суммы из конкретного файла
//                        String[] statistic = stringStatistic.split(" "); // массив из строки с суммой
//                        for(String string : statistic){
//                            if(string.matches(".*\\b[$]\\b.*")){
//                                // преобразование и запись в переменную суммы перевода
//                                String amountStr = string.replace("$", "");
//                                System.out.println(amountStr);
//                                amount = Double.parseDouble(amountStr);
//                            }
//                        }
//
//                        //amountAllInvoice = amountAllInvoice + amount;
//
//                    }
//                }
//            } catch (NumberFormatException e) {
//                throw new NumberFormatException("Conversion error");
//            } catch (Exception e) {
//                throw new Exception("Unexpected error");
//            }
//        }
//        System.out.println(amountAllInvoice);
//        return amountAllInvoice;
//}


    public static void calculateStatisticOrder(String path){

    }

    public static void calculateStatisticFinal(String path){

    }

}
