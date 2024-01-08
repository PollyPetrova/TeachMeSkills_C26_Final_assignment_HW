package com.teachmeskill.final_assignment.utils;

public interface Constant {

    int ACCESS_TOKEN_LENGTH = 16;

    // для конвертаци валют
    double EURO_TO_USD = 1.09;

    //регулярные выражения для поиска суммы переводов в документах

    String REGEXP_TRANSACTION_CHECK = ".*\\bEURO [0-9]+([0-9]{2})?\\b.*";
    String REGEXP_TRANSACTION_INVOICE = ".*\\bTotal\\b.*";
    String REGEXP_TRANSACTION_ORDER = ".*\\b([a-zA-Z]+)?(Total)\\b.*";

    //регурярки для проверки названия файлов
    String REGEXP_DOC_CHECK = "2023_Electric_Bill_(\\d{2})\\.txt";
    String REGEXP_DOC_INVOICE = "INVOICE_(\\d{2})\\_2023.txt";
    String REGEXP_DOC_ORDER = "(?i)2023_order_(\\d{2})\\.txt";

    //пути к папкам
    String PATH_RESOURCE = "C:\\Users\\Liisa\\Desktop\\уроки Java\\Lesson materials\\Курсовой проект\\course\\Course\\resource";
    String PATH_VALID_CHECK = "C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\valid\\valid_check";
    String PATH_VALID_INVOICE = "C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\valid\\valid_invoice";
    String PATH_VALID_ORDER = "C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\valid\\valid_order";
    String PATH_INVALID_DOC = "C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\invalid\\invalid_doc";

    //путь к статистическим файлам
    String PATH_YEAR_TURNOVER = "C:\\Users\\Liisa\\Desktop\\уроки Java\\Homework\\TeachMeSkills_C26_Final_assignment_HW\\result\\statistic\\year_cash_turnover";

}
