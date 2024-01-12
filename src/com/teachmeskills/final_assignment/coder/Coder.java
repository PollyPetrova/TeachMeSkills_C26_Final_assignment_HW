package com.teachmeskills.final_assignment.coder;

import java.util.Base64;
import java.util.Random;
import java.util.stream.Collectors;

public class Coder {

    //Метод "из коробки" для шифрования
    public static String code(String input){
        String encodedString= Base64.getEncoder().encodeToString(input.getBytes());

        return addSalt(encodedString);
    }

    //Метод "из коробки" для дешифрования
    public static String decode(String input){
        byte[] decodedBytes=Base64.getDecoder().decode(input.substring(10));

        return new String(decodedBytes);
    }

    //Метод для добавления 10 рандомных символов в начале строки
    private static String addSalt(String input){
        String symbols="abcdefghijklmnopqrstuvwxyz0123456789";

        String salt=new Random().ints(10,0,symbols.length())
                .mapToObj(symbols::charAt)
                .map(Object::toString)
                .collect(Collectors.joining());

        return salt+input;

    }

}
