package commands;

import mail.User;
import mail.UserStorage;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TestAdd {
    @Test
    void testAddNewUser(){
        UserStorage storage = new UserStorage();
        Add addCommand = new Add(storage, new Scanner("liza\n"));
        addCommand.execute();
        assertTrue(storage.existenceUser("liza"), "Пользователь должен быть создан");
    }

    @Test
    void testAddExistingUser() {
        UserStorage storage = new UserStorage();
        storage.addUserInStorage(new User("dima"));
        Add addCommand = new Add(storage, new Scanner("dima\n"));

        addCommand.execute();

        assertTrue(storage.existenceUser("dima"), "Существующий пользователь должен остаться");
    }
}
