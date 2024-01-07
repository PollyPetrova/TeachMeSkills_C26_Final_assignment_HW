package com.teachmeskill.final_assignment;

import com.teachmeskill.final_assignment.service.AuthService;
import com.teachmeskill.final_assignment.service.DocumentSortService;
import com.teachmeskill.final_assignment.service.StatisticService;

import java.util.Scanner;

import static com.teachmeskill.final_assignment.utils.Constant.*;

public class Runner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login:");
        String login = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        //Right login:    Polina;
        //      password: Polina123987;

        AuthService.doLogin(login, password);
        /*System.out.println(Coder.code("Polina"));
        System.out.println(Coder.code("Polina123987"));*/

        //Считываем путь к папке с файлами
        System.out.println("Enter path to the Directory:");
        String resourcePath = scanner.nextLine();

        //вызов метода для сортировки файлов
        try {
            DocumentSortService.SortDocument(resourcePath);
        } catch (Exception e){
            //TODO запись в логгер с сообщением, датой и стектрейсом
      }


        //Вызов метода для суммирования транзакций из чеков
        try {
            double checkAmount = StatisticService.calculateStatisticCheck(PATH_VALID_CHECK);
            System.out.println(checkAmount);
        } catch (Exception e) {
            //TODO запись в логгер с сообщением, датой и стектрейсом
        }

    }

}
