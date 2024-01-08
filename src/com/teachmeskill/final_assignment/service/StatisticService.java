package com.teachmeskill.final_assignment.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   public static double calculateStatisticInvoice(String path) throws Exception {
        File directInvoice = new File(path);
        File[] directInvoiceArray = directInvoice.listFiles();
        double amountAllInvoice = 0; // переменная для суммирования транзакций

        //идем по массиву
        for (File invoice : directInvoiceArray) {
            String invoicePath = invoice.getPath();

            //используем трай виз ресурс и открываем поток для считывания из файла
            try (BufferedReader invoiceReader = Files.newBufferedReader(Path.of(invoicePath))) {
                String stringStatistic;

                while (!((stringStatistic = invoiceReader.readLine()) == null)) { //не работает вариант (string_1 = checkReader.readLine()) != null)
                    //проверяем не пустые строки на соответствие регулярке
                    if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_INVOICE)) {
                        System.out.println(stringStatistic);
                        double amount;// переменная для суммы из конкретного файла
                        Pattern pattern = Pattern.compile("(\\d)+(\\.?)\\d+");
                        Matcher matcher = pattern.matcher(stringStatistic);

                        StringBuilder amountStr = new StringBuilder();
                        while(matcher.find()) {
                            amountStr.append(matcher.group());
                        }
                        System.out.println(amountStr);
                        amount = Double.parseDouble(amountStr.toString());
                        amountAllInvoice = amountAllInvoice + amount;
                    }
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Conversion error");
            } catch (Exception e) {
                throw new Exception("Unexpected error");
            }
        }
        System.out.println(amountAllInvoice);
        return amountAllInvoice;
}


    public static double calculateStatisticOrder(String path) throws Exception {

        File directOrder = new File(path);
        File[] directOrderArray = directOrder.listFiles();
        double amountAllOrder = 0; // переменная для суммирования транзакций

        //идем по массиву
        for (File order : directOrderArray) {
            String orderPath = order.getPath();

            //используем трай виз ресурс и открываем поток для считывания из файла
            try (BufferedReader orderReader = Files.newBufferedReader(Path.of(orderPath))) {
                String stringStatistic;

                while (!((stringStatistic = orderReader.readLine()) == null)) { //не работает вариант (string_1 = checkReader.readLine()) != null)
                    //проверяем не пустые строки на соответствие регулярке
                    if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_ORDER)) {
                        System.out.println(stringStatistic);

                        double amount;// переменная для суммы из конкретного файла
                        //для поиска цифр в строке используем метчер и паттерн
                        Pattern pattern = Pattern.compile("(\\d)+(,?)\\d+(\\.?)\\d+");
                        Matcher matcher = pattern.matcher(stringStatistic);
                        //в стрингбилдер записываем найденные цифры
                        StringBuilder amountStr = new StringBuilder();
                        while(matcher.find()) {
                            amountStr.append(matcher.group());
                        }
                        System.out.println(amountStr);
                        //приводим к дабл строку с суммой
                        amount = Double.parseDouble(amountStr.toString().replace(",", ""));
                        //прибавляем к итоговой сумме
                        amountAllOrder = amountAllOrder + amount;
                    }
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Conversion error");
            } catch (Exception e) {
                throw new Exception("Unexpected error");
            }
        }
        System.out.println(amountAllOrder);
        return amountAllOrder;

    }

    public static void calculateStatisticFinal(double check, double invoice, double order) throws Exception {
        double result = check + invoice + order;
        String checkResult = "The total annual amount of transfers in USD by checks: " + check + "\n";
        String invoiceResult = "The total annual amount of transfers in USD by invoice: " + invoice + "\n";
        String orderResult = "The total annual amount of transfers in USD by order: " + order + "\n";
        String resultAll = "The total annual amount of transfers in USD by all documents: " + result + "\n";

        try{
            Path pathStatistic = Path.of(PATH_YEAR_TURNOVER);
            Files.write(pathStatistic, checkResult.getBytes(), StandardOpenOption.APPEND);
            Files.write(pathStatistic, invoiceResult.getBytes(), StandardOpenOption.APPEND);
            Files.write(pathStatistic, orderResult.getBytes(), StandardOpenOption.APPEND);
            Files.write(pathStatistic, resultAll.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
