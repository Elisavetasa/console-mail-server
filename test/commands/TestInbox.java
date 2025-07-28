package commands;

import mail.Message;
import mail.User;
import mail.UserStorage;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestInbox {
    @Test
    void testExecuteOnExistingUserLeavesInboxUnchanged() {
        UserStorage storage = new UserStorage();
        User uliana = new User("uliana");
        storage.addUserInStorage(uliana);
        Message msg = new Message("Subject", "Body", uliana, uliana);
        uliana.setInbox(msg);

        Inbox cmd = new Inbox(storage, new Scanner("uliana\n"));
        cmd.execute();
        assertEquals(1, uliana.getInbox().size(), "Inbox size должен остаться равен одному");
    }
}
