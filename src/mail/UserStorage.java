package mail;

import java.util.ArrayList;

public class UserStorage {
    private final ArrayList<User> storage = new ArrayList<>();

    public void addUserInStorage(User user) {
        storage.add(user);
    }

    public User getterUserInStorage(String username) {
        for (User user : storage) {
            if (user.getUserName().equals(username)){
                return user;
            }
        }
        System.out.println("Такого пользователя нет в базе данных");
        return null;
    }

    public boolean existenceUser(String username) {
        for (User user : storage) {
            if (user.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getterListUser(){
        return storage;
    }
}
