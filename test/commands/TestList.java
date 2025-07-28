package commands;

import mail.User;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static main.Main.storage;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestList {
    @Test
    void testListUser(){
        ArrayList<String> names = new ArrayList<>();
        names.add("nastya");
        for (String name : names) {
            storage.addUserInStorage(new User(name));
        }
        for (String name : names) {
            assertTrue(storage.existenceUser(name),
                    "Хранилище должно содержать пользователя '" + name + "'");
        }
        assertTrue(storage.getterListUser().size() == names.size(),
                "В хранилище должно быть ровно " + names.size() + " пользователей");
    }
}
