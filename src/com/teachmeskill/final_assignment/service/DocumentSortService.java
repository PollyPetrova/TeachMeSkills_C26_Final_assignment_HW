package com.teachmeskill.final_assignment.service;

import java.io.File;

public class DocumentSortService {

    public static void SortDocument (String path){
        //Создаем массив файлов
        File resource = new File(path);
        File[] resourceArray = resource.listFiles();

        //идет по массиву и вызываем метод проверки названия
        assert resourceArray != null;
        for(File file : resourceArray){
            DocumentNameValidService.checkDocNumValid(file);
        }

    }

}
