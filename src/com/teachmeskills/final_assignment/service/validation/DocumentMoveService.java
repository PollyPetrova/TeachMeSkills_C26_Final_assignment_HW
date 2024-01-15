package com.teachmeskills.final_assignment.service.validation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentMoveService {

    public static void moveDoc(File fileMove, File TargetDir) throws IOException {

        try {
            //Creating objects of the path class for moving files
            Path pathFileMove = Paths.get(fileMove.getAbsolutePath());
            Path pathTargetDir = Paths.get(TargetDir.getAbsolutePath() + "\\" + fileMove.getName());
            //moving files
            Files.move(pathFileMove, pathTargetDir);
        } catch (IOException e) {
            throw new IOException("File moving error");
        }
    }

}
