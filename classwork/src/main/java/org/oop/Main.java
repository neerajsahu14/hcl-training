package org.oop;

import org.oop.interfaces.User;
import org.oop.interfaces.UserRepository;
import org.oop.interfaces.UserRepositoryImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        UserRepository userRepository = new UserRepositoryImpl();
        User user = userRepository.addUser("admin","admin@123");
        System.out.println(userRepository.authenticate(user.getUsername(), user.getPassword()));
        System.out.println(user.getUsername() + "\t" + user.getPassword());

        org.oop.abstractclass.UserRepository userRepository1 = new org.oop.abstractclass.UserRepositoryImpl();
        User user2 = userRepository1.addUser("admin","admin@123");
        System.out.println(userRepository1.authenticate(user2.getUsername(), user2.getPassword()));
        System.out.println(user2.getUsername() + "\t" + user2.getPassword());

    }

}