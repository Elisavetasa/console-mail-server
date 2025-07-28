package commands;

import mail.Message;
import mail.User;
import mail.UserStorage;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestOutbox {
    @Test
    void testExistingUserOutboxUnchanged() {
        UserStorage storage = new UserStorage();
        User masha = new User("masha");
        storage.addUserInStorage(masha);
        Message m = new Message("T", "B", masha, masha);
        masha.setOutbox(m);

        Outbox cmd = new Outbox(storage, new Scanner("masha\n"));
        cmd.execute();

        assertEquals(1, masha.getOutbox().size(),
                "Outbox size должен остаться равен одному");
    }
}
