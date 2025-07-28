package commands;

import filters.*;
import mail.User;
import mail.UserStorage;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TestSetFilter {

    @Test
    void testExecuteOnNonExistingUser() {
        UserStorage storage = new UserStorage();
        SetFilter cmd = new SetFilter(storage, new Scanner("nobody\n"));
        cmd.execute();

        assertFalse(storage.existenceUser("nobody"),
                "Не должно создаваться пользователя при установке фильтров");
    }

    @Test
    void testDoneOnlyCreatesEmptyCompositeFilter() {
        UserStorage storage = new UserStorage();
        User lisa = new User("lisa");
        storage.addUserInStorage(lisa);
        SetFilter cmd = new SetFilter(storage, new Scanner("lisa\ndone\n"));
        cmd.execute();

        assertTrue(lisa.getSpamFilter() instanceof CompositeSpamFilter,
                "Должен быть установлен CompositeSpamFilter");
        CompositeSpamFilter composite = (CompositeSpamFilter) lisa.getSpamFilter();
        assertEquals(0, composite.filters.size(),
                "CompositeSpamFilter должен содержать 0 вложенных фильтров");
    }

    @Test
    void testAddSimpleFilterThenDone() {
        UserStorage storage = new UserStorage();
        User lisa = new User("lisa");
        storage.addUserInStorage(lisa);
        SetFilter cmd = new SetFilter(storage, new Scanner("lisa\nsimple\ndone\n"));
        cmd.execute();

        CompositeSpamFilter composite = (CompositeSpamFilter) lisa.getSpamFilter();
        assertEquals(1, composite.filters.size(),
                "CompositeSpamFilter должен содержать 1 вложенный фильтр");
        assertTrue(composite.filters.get(0) instanceof SimpleSpamFilter,
                "Первый фильтр должен быть SimpleSpamFilter");
    }

    @Test
    void testAddKeywordsFilterThenDone() {
        UserStorage storage = new UserStorage();
        User lisa = new User("lisa");
        storage.addUserInStorage(lisa);
        SetFilter cmd = new SetFilter(storage, new Scanner("lisa\nkeywords\nfoo bar baz\ndone\n"));
        cmd.execute();

        CompositeSpamFilter composite = (CompositeSpamFilter) lisa.getSpamFilter();
        assertEquals(1, composite.filters.size());
        assertTrue(composite.filters.get(0) instanceof KeywordsSpamFilter,
                "Первый фильтр должен быть KeywordsSpamFilter");
    }

    @Test
    void testAddSenderFilterThenDone() {
        UserStorage storage = new UserStorage();
        User lisa = new User("lisa");
        User spammer = new User("spammer");
        storage.addUserInStorage(lisa);
        storage.addUserInStorage(spammer);
        SetFilter cmd = new SetFilter(storage, new Scanner("lisa\nsender\nspammer\ndone\n"));
        cmd.execute();

        CompositeSpamFilter composite = (CompositeSpamFilter) lisa.getSpamFilter();
        assertEquals(1, composite.filters.size());
        assertTrue(composite.filters.get(0) instanceof SenderSpamFilter,
                "Первый фильтр должен быть SenderSpamFilter");
    }

    @Test
    void testAddRepetitionFilterThenDone() {
        UserStorage storage = new UserStorage();
        User lisa = new User("lisa");
        storage.addUserInStorage(lisa);
        SetFilter cmd = new SetFilter(storage, new Scanner("lisa\nrepetition\n3\ndone\n"));
        cmd.execute();

        CompositeSpamFilter composite = (CompositeSpamFilter) lisa.getSpamFilter();
        assertEquals(1, composite.filters.size());
        assertTrue(composite.filters.get(0) instanceof RepetitionSpamFilter,
                "Первый фильтр должен быть RepetitionSpamFilter");
    }
}
