package filters;

import mail.Message;
import mail.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSimpleSpamFilter {
    @Test
    void testSimpleSpamFilter() {
        SimpleSpamFilter filter = new SimpleSpamFilter();
        User sender = new User("s");
        User receiver = new User("r");

        Message msg1 = new Message("SPAM subject", "no body", sender, receiver);
        assertTrue(filter.isSpam(msg1));

        Message msg2 = new Message("Hello", "world", sender, receiver);
        assertFalse(filter.isSpam(msg2));
    }
}
