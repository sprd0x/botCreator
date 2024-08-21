package com.azizsaparniyazov;

import com.azizsaparniyazov.models.User;
import com.azizsaparniyazov.services.UserService;

import java.util.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        UserService userService = new UserService();
        User user = new User("1","madina","blabla");
        userService.add(user);
    }
}
