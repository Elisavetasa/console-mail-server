package filters;

import mail.Message;
import mail.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestKeywordsSpamFilter {

    @Test
    void detectsSpamInTitle() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(
                new ArrayList<>(Arrays.asList("foo", "bar"))
        );
        User sender = new User("s");
        User receiver = new User("r");
        Message msg = new Message("This is foo here", "Clean text", sender, receiver);
        assertTrue(filter.isSpam(msg), "Should detect 'foo' in title as spam");
    }

    @Test
    void detectsSpamInText() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(
                new ArrayList<>(Arrays.asList("foo", "bar"))
        );
        User sender = new User("s");
        User receiver = new User("r");
        Message msg = new Message("Clean title", "bar appears here", sender, receiver);
        assertTrue(filter.isSpam(msg), "Should detect 'bar' in text as spam");
    }

    @Test
    void matchesAnyOfMultipleKeywords() {
        KeywordsSpamFilter filter = new KeywordsSpamFilter(
                new ArrayList<>(Arrays.asList("alpha", "beta", "gamma"))
        );
        User sender = new User("s");
        User receiver = new User("r");
        Message msgAlpha = new Message("alpha appears", "text", sender, receiver);
        Message msgBeta  = new Message("title", "beta appears", sender, receiver);
        Message msgGamma = new Message("random", "something gamma here", sender, receiver);
        assertTrue(filter.isSpam(msgAlpha), "Should detect 'alpha'");
        assertTrue(filter.isSpam(msgBeta),  "Should detect 'beta'");
        assertTrue(filter.isSpam(msgGamma), "Should detect 'gamma'");
    }
}

