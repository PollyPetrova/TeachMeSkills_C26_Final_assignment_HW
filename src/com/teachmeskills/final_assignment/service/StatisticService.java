package com.teachmeskills.final_assignment.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teachmeskills.final_assignment.utils.Constant.*;


public class StatisticService {

    public static double calculateStatisticCheck(String path) throws IOException {
        double amountAllCheck = processFiles(path, REGEXP_TRANSACTION_CHECK);
        return amountAllCheck * EURO_TO_USD;
    }

    public static double calculateStatisticInvoice(String path) throws IOException {
        double amountAllInvoice = processFiles(path, REGEXP_TRANSACTION_INVOICE);
        return amountAllInvoice;
    }

    public static double calculateStatisticOrder(String path) throws IOException {
        double amountAllOrder = processFiles(path, REGEXP_TRANSACTION_ORDER);
        return amountAllOrder;
    }

    public static void calculateStatisticFinal(double check, double invoice, double order) throws IOException {
        double result = check + invoice + order;
        String checkResult = "The total annual amount of transfers in USD by checks: " + check + "\n";
        String invoiceResult = "The total annual amount of transfers in USD by invoice: " + invoice + "\n";
        String orderResult = "The total annual amount of transfers in USD by order: " + order + "\n";
        String resultAll = "The total annual amount of transfers in USD by all documents: " + result + "\n";

        try {
            Path pathStatistic = Path.of(PATH_YEAR_TURNOVER);
            Files.write(pathStatistic, (checkResult + invoiceResult + orderResult + resultAll).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IOException("Error writing to file");
        }
    }

    private static double processFiles(String path, String regexp) throws IOException {
        File directory = new File(path);
        File[] filesArray = directory.listFiles();
        double totalAmount = 0;

        if (filesArray != null) {
            for (File file : filesArray) {
                String filePath = file.getPath();

                try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
                    String stringStatistic;
                    while ((stringStatistic = reader.readLine()) != null) {
                        if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_CHECK)) {
                            double amount; // переменная для суммы из конкретного файла
                            String[] statistic = stringStatistic.split(" "); // массив из строки с суммой
                            amount = Double.parseDouble(statistic[4].replace(",", ".")); // преобразование и запись в переменную суммы перевода
                            totalAmount+= amount;
                        }
                        else if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_INVOICE)) {
                            double amount;// переменная для суммы из конкретного файла
                            //Создаем объекты класса паттерн и метчер для поиска суммы из строки
                            Pattern pattern = Pattern.compile("(\\d)+(\\.?)\\d+");
                            Matcher matcher = pattern.matcher(stringStatistic);
                            //Объект класса стрингбилдер для составления строки суммы по регурярному выражению
                            //из строки с статистикой суммы
                            StringBuilder amountStr = new StringBuilder();
                            while (matcher.find()) {
                                amountStr.append(matcher.group());
                            }
                            //преобразование и запись в переменную суммы перевода, суммирование с полной суммой документов
                            amount = Double.parseDouble(amountStr.toString());
                            totalAmount = totalAmount + amount;
                        }
                        else if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_ORDER)) {
                            double amount;// переменная для суммы из конкретного файла
                            //для поиска цифр в строке используем метчер и паттерн
                            Pattern pattern = Pattern.compile("(\\d)+(,?)\\d+(\\.?)\\d+");
                            Matcher matcher = pattern.matcher(stringStatistic);
                            //в стрингбилдер записываем найденные цифры
                            StringBuilder amountStr = new StringBuilder();
                            while (matcher.find()) {
                                amountStr.append(matcher.group());
                            }
                            //приводим к дабл строку с суммой
                            amount = Double.parseDouble(amountStr.toString().replace(",", ""));
                            //прибавляем к итоговой сумме
                            totalAmount += amount;
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new IOException("Conversion error");
                }
            }
        }
        return totalAmount;
    }

}
