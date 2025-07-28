package filters;

import mail.Message;
import mail.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRepetitionSpamFilter {
    @Test
    void testRepetitionSpamFilter(){
        RepetitionSpamFilter filter2 = new RepetitionSpamFilter(2);
        RepetitionSpamFilter filter3 = new RepetitionSpamFilter(3);
        User sender = new User("s");
        User receiver = new User("r");

        Message msg1 = new Message("x x x", "y z", sender, receiver);
        assertTrue(filter2.isSpam(msg1));

        Message msg2 = new Message("a a a", "b", sender, receiver);
        assertFalse(filter3.isSpam(msg2));

        Message msg3 = new Message("a a a a", "", sender, receiver);
        assertTrue(filter3.isSpam(msg3));
    }
}
