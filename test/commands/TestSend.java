package commands;

import mail.Message;
import mail.User;
import mail.UserStorage;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class TestSend {
    @Test
    void testSendMessageSuccess() {
        UserStorage storage = new UserStorage();
        storage.addUserInStorage(new User("yan"));
        storage.addUserInStorage(new User("kate"));

        Scanner input = new Scanner("yan\nkate\nHello\nHi kate\n");
        Send cmd = new Send(storage, input);

        cmd.execute();

        User yan = storage.getterUserInStorage("yan");
        assertEquals(1, yan.getOutbox().size(),
                "У yan должен быть 1 исходящий");
        Message m = yan.getOutbox().get(0);
        assertEquals("Hello", m.getCaption());
        assertEquals("Hi kate", m.getText());

        User kate = storage.getterUserInStorage("kate");
        assertEquals(1, kate.getInbox().size(),
                "У kate должен быть 1 входящий");
        assertSame(m, kate.getInbox().get(0));
    }
}