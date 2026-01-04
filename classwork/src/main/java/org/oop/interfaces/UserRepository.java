package org.oop.interfaces;

public interface UserRepository {
    User addUser(String username, String password);
    boolean authenticate(String username, String password);
}
