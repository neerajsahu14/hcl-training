package org.oop.abstractclass;

import org.oop.interfaces.User;

public class UserRepositoryImpl extends UserRepository {

    @Override
        public User addUser(String username, String password) {
        return new User(username,password);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin@123");
    }
}
