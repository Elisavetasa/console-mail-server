package commands;

import mail.User;
import mail.UserStorage;

import java.util.ArrayList;

public class List implements Command{
    private final UserStorage storage;
    public List(UserStorage storage) {
        this.storage = storage;
    }

    @Override
    public void execute() {
        ArrayList<User> list = storage.getterListUser();
        for (User u : list) {
            System.out.println("* " + u.getUserName());
        }
        System.out.printf("Общее число пользователей: %d %n", list.size());
    }
}
