package com.teachmeskill.final_assignment;

import com.teachmeskill.final_assignment.coder.Coder;
import com.teachmeskill.final_assignment.service.authorization.AuthService;

public class Runner {

    public static void main(String[] args) {

        AuthService.doLogin("Polina", "Polina123987");
/*        System.out.println(Coder.code("Polina"));
        System.out.println(Coder.code("Polina123987"));*/

    }

}
