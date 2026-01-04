package org.oop.interfaces;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User addUser(String username, String password) {
        return new User(username, password);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin@123");
    }
}
