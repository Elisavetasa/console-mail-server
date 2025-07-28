package commands;

import mail.Message;
import mail.User;
import mail.UserStorage;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSpam {
    @Test
    void testSpamWithExistingUserAndMessages() {
        UserStorage storage = new UserStorage();
        User alice = new User("alice");
        storage.addUserInStorage(alice);
        Message msg = new Message("SpamSubject", "SpamBody", alice, alice);
        alice.setSpam(msg);

        Spam cmd = new Spam(storage, new Scanner("alice\n"));

        cmd.execute();

        assertEquals(1, alice.getSpam().size(),
                "Spam-папка должна содержать одно сообщение после execute()");
    }
}