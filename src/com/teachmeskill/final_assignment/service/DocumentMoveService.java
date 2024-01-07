package com.teachmeskill.final_assignment.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentMoveService {

    public static void moveDoc(File fileMove, File TargetDir) throws IOException {

        try {
            //Создаем объекты класса путь для перемещения файлов
            Path pathFileMove = Paths.get(fileMove.getAbsolutePath());
            Path pathTargetDir = Paths.get(TargetDir.getAbsolutePath() + "\\" + fileMove.getName());
            //перемещаем файлы
            Files.move(pathFileMove, pathTargetDir);
        } catch (IOException e) {
            throw new IOException("File moving error");
        }
    }

}
