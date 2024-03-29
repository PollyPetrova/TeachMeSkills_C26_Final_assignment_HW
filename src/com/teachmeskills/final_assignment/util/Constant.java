package com.teachmeskills.final_assignment.util;

public interface Constant {

    int ACCESS_TOKEN_LENGTH = 16;

    // for currency conversion
    double EURO_TO_USD = 1.09;

    //regular expressions for searching the amount of transfers in documents
    String REGEXP_TRANSACTION_CHECK = ".*\\bEURO [0-9]+([0-9]{2})?\\b.*";
    String REGEXP_TRANSACTION_INVOICE = ".*\\bTotal\\b.*";
    String REGEXP_TRANSACTION_ORDER = ".*\\b([a-zA-Z]+)?(Total)\\b.*";

    //Regulars for searching for a number in the sum line
    String REGEXP_AMOUNT_INVOICE = "(\\d)+(\\.?)\\d+";
    String REGEXP_AMOUNT_ORDER = "(\\d)+(,?)\\d+(\\.?)\\d+";

    //regular checks for file names
    String REGEXP_DOC_CHECK = "2023_Electric_Bill_(\\d{2})\\.txt";
    String REGEXP_DOC_INVOICE = "INVOICE_(\\d{2})\\_2023.txt";
    String REGEXP_DOC_ORDER = "(?i)2023_order_(\\d{2})\\.txt";

    //folder paths
    String PATH_RESOURCE = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\resource";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\resource";
    String PATH_VALID_CHECK = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\valid\\valid_check";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\result\valid\valid_check";
    String PATH_VALID_INVOICE = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\valid\\valid_invoice";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\result\valid\valid_invoice";
    String PATH_VALID_ORDER = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\valid\\valid_order";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\result\valid\valid_order";
    String PATH_INVALID_DOC = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\invalid\\invalid_doc";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\result\invalid\invalid_doc";

    //the path to the statistical files
    String PATH_YEAR_TURNOVER = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\statistic\\year_cash_turnover";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\result\statistic\year_cash_turnover";

    //the path to files with log
    String PATH_EXECUTION_LOG = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\log\\execution_log\\execution_log";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\log\execution_log\execution_log";
    String PATH_ERROR_LOG = "";
    //"C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\log\\error_log\\error_log";
    //"D:\JAVA\Add\TeachMeSkills_C26_Final_assignment_HW\log\error_log\error_log";

}
