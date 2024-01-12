package com.teachmeskills.final_assignment.logger;
import com.teachmeskills.final_assignment.utils.Constant;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger {

    public static void logInfo(Date date, String logMessage){
        try {
            String message=date+" -> "+logMessage+" \n";
            Files.write(Paths.get(Constant.PATH_EXECUTION_LOG), message.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {

        }
    }

    public static void errorInfo(Date date,String errorMessage, Exception exception){
        try {
            StringBuilder sb=new StringBuilder();
            sb.append(date+" -> "+errorMessage+" \n");

            StackTraceElement[] traceElements= exception.getStackTrace();
            for (StackTraceElement element: traceElements) {
                sb.append(element.toString());
                sb.append("\n");
            }

            Files.write(Paths.get(Constant.PATH_ERROR_LOG), sb.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {

        }
    }

}
