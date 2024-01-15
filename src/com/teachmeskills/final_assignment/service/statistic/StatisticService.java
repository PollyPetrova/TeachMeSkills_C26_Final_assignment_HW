package com.teachmeskills.final_assignment.service.statistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.teachmeskills.final_assignment.util.Constant.*;


public class StatisticService {

    //Summing up the annual Check amount
    public static double calculateStatisticCheck(String path) throws IOException {
        double amountAllCheck = processFiles(path);
        amountAllCheck *= EURO_TO_USD;
        return amountAllCheck * 100 / 100;
    }

    //Summing up the annual Invoice amount
    public static double calculateStatisticInvoice(String path) throws IOException {
        double amountAllInvoice = processFiles(path);
        return amountAllInvoice * 100 / 100;
    }

    //Summing up the annual Order amount
    public static double calculateStatisticOrder(String path) throws IOException {
        double amountAllOrder = processFiles(path);
        return amountAllOrder * 100 / 100;
    }

    //Summing up the total annual turnover amount and writing to the statistics file
    public static void calculateStatisticFinal(double check, double invoice, double order) throws IOException {
        //Суммирование, составление строк статистики
        double result = check + invoice + order;
        String checkResult = "The total annual amount of transfers in USD by checks: " + check + "\n";
        String invoiceResult = "The total annual amount of transfers in USD by invoice: " + invoice + "\n";
        String orderResult = "The total annual amount of transfers in USD by order: " + order + "\n";
        String resultAll = "The total annual amount of transfers in USD by all documents: " + result + "\n";

        //Writing to the statistics file
        try {
            Path pathStatistic = Path.of(PATH_YEAR_TURNOVER);
            Files.write(pathStatistic, (checkResult + invoiceResult + orderResult + resultAll).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new IOException("Error writing to file");
        }
    }

    //A method for finding the transaction amount in files
    private static double processFiles(String path) throws IOException {
        //Creating an array of files
        File directory = new File(path);
        File[] filesArray = directory.listFiles();
        double totalAmount = 0;

        //Checking for the fullness of the array
        if (filesArray != null) {
            //We go through the array
            for (File file : filesArray) {
                String filePath = file.getPath();

                //Creating an object of the BufferedReader class to read from a file
                try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
                    //The line that will contain the line including the transfer amount
                    String stringStatistic;
                    while ((stringStatistic = reader.readLine()) != null) {

                        //Check if the string matches the regular expression for the CHECK files
                        if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_CHECK)) {
                            double amount; //a variable for the amount from a specific file
                            String[] statistic = stringStatistic.split(" "); // an array of a string with a sum
                            amount = Double.parseDouble(statistic[4].replace(",", ".")); // converting and writing the transfer amount to a variable
                            totalAmount += amount;

                            //Check if the string matches the regular expression for the INVOICE files
                        } else if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_INVOICE)) {
                            double amount;//a variable for the amount from a specific file

                            //Creating objects of the pattern and metcher class to search for the sum from the string
                            Pattern pattern = Pattern.compile(REGEXP_AMOUNT_INVOICE);
                            Matcher matcher = pattern.matcher(stringStatistic);

                            //An object of the stringbuilder class for composing a sum string using a regular expression
                            //from the line with the sum statistics
                            StringBuilder amountStr = new StringBuilder();
                            while (matcher.find()) {
                                amountStr.append(matcher.group());
                            }

                            //conversion and recording of the transfer amount into a variable, summation with the full amount of documents
                            amount = Double.parseDouble(amountStr.toString());
                            totalAmount = totalAmount + amount;

                            //We check whether the string matches the regular expression for the ORDER files
                        } else if (!stringStatistic.isEmpty() && stringStatistic.matches(REGEXP_TRANSACTION_ORDER)) {
                            double amount;// a variable for the amount from a specific file

                            //to search for numbers in a string, we use a metcher and a pattern
                            Pattern pattern = Pattern.compile(REGEXP_AMOUNT_ORDER);
                            Matcher matcher = pattern.matcher(stringStatistic);

                            //we write down the found numbers in the stringbuilder
                            StringBuilder amountStr = new StringBuilder();
                            while (matcher.find()) {
                                amountStr.append(matcher.group());
                            }

                            //we bring the row with the sum to the double
                            amount = Double.parseDouble(amountStr.toString().replace(",", ""));
                            //we add to the total amount
                            totalAmount += amount;
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Conversion error");
                } catch (IOException e) {
                    throw new IOException("Input/output error");
                }
            }
        }
        return totalAmount;
    }

}
