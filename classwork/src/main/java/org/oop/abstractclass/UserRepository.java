package org.oop.abstractclass;

import org.oop.interfaces.User;

public abstract class UserRepository {
    public abstract User addUser(String username, String password);
    public abstract boolean authenticate(String username, String password);
}
