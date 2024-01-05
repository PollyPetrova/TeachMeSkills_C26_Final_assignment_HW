package com.teachmeskill.final_assignment;

import com.teachmeskill.final_assignment.coder.Coder;
import com.teachmeskill.final_assignment.service.authorization.AuthService;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);
        System.out.println("Enter login:");
        String login= scanner.nextLine();
        System.out.println("Enter password:");
        String password= scanner.nextLine();

        //Right login:    Polina;
        //      password: Polina123987;

        AuthService.doLogin(login, password);
        /*System.out.println(Coder.code("Polina"));
        System.out.println(Coder.code("Polina123987"));*/

    }

}
