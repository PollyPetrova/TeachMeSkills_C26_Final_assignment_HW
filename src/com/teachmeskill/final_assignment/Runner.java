package com.teachmeskill.final_assignment;
import com.teachmeskill.final_assignment.service.AuthService;
import com.teachmeskill.final_assignment.service.DocumentSortService;
import com.teachmeskill.final_assignment.service.StatisticService;

import java.util.Scanner;

import static com.teachmeskill.final_assignment.utils.Constant.*;

public class Runner {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);//TODO нам надо закрыть сканнер в конце программы
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
        double checkAmountAll = 0;
        try {
            checkAmountAll = StatisticService.calculateStatisticCheck(PATH_VALID_CHECK);
            System.out.println(checkAmountAll);
        } catch (Exception e) {
            //TODO запись в логгер с сообщением, датой и стектрейсом
        }

        double invoiceAmountAll = 0;
        try {
            invoiceAmountAll = StatisticService.calculateStatisticInvoice(PATH_VALID_INVOICE);
            System.out.println(invoiceAmountAll);
        } catch (Exception e) {
            //TODO запись в логгер с сообщением, датой и стектрейсом
        }

        double orderAmountAll = 0;
        try {
            orderAmountAll = StatisticService.calculateStatisticOrder(PATH_VALID_ORDER);
            System.out.println(orderAmountAll);
        } catch (Exception e) {
            //TODO запись в логгер с сообщением, датой и стектрейсом
        }

        try {
            StatisticService.calculateStatisticFinal(checkAmountAll, invoiceAmountAll, orderAmountAll);
        } catch (Exception e) {
            //TODO запись в логгер с сообщением, датой и стектрейсом
        }

    }

}
