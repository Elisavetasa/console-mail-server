package commands;

import filters.*;
import mail.User;
import mail.UserStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class SetFilter implements Command{
    private final UserStorage storage;
    private final Scanner scan;

    public SetFilter(UserStorage storage) {
        this(storage, new Scanner(System.in));
    }

    public SetFilter(UserStorage storage, Scanner scan) {
        this.storage = storage;
        this.scan    = scan;
    }

    @Override
    public void execute(){
        System.out.print("Введите имя пользователя, для которого задаются фильтры: ");
        String userSetFilter = scan.nextLine();

        if (!storage.existenceUser(userSetFilter)) {
            System.out.println("Такого пользователя нет в базе данных");
            return;
        }
        ArrayList<SpamFilter> filters = collectFilters();
        storage.getterUserInStorage(userSetFilter).setSpamFilter(new CompositeSpamFilter(filters));
        System.out.println("Фильтры успешно установлены");
    }

    private ArrayList<SpamFilter> collectFilters() {
        ArrayList<SpamFilter> filters = new ArrayList<>();

        while (true) {
            System.out.print("Введите тип фильтра (simple, keywords, sender, repetition) или 'done' для завершения: ");
            String command = scan.nextLine().trim();

            switch (command) {
                case "done":
                    return filters;
                case "simple":
                    addSimpleFilter(filters);
                    break;
                case "keywords":
                    addKeywordsFilter(filters);
                    break;
                case "sender":
                    addSenderFilter(filters);
                    break;
                case "repetition":
                    addRepetitionFilter(filters);
                    break;
                default:
                    System.out.println("Неизвестный фильтр");
            }
        }
    }

    private void addSimpleFilter(ArrayList<SpamFilter> filters) {
        filters.add(new SimpleSpamFilter());
        System.out.println("Фильтр simple добавлен");
    }

    private void addKeywordsFilter(ArrayList<SpamFilter> filters) {
        System.out.print("Введите ключевые слова через пробел: ");
        String line = scan.nextLine();
        String[] parts = line.split("\\s+");
        ArrayList<String> keywords = new ArrayList<>();
        for (String kw : parts) {
            if (!kw.isBlank()) {
                keywords.add(kw);
            }
        }
        filters.add(new KeywordsSpamFilter(keywords));
        System.out.println("Фильтр keywords добавлен");
    }

    private void addSenderFilter(ArrayList<SpamFilter> filters) {
        System.out.print("Введите имя пользователя для фильтрации: ");
        String spamUserName = scan.nextLine().trim();
        if (storage.existenceUser(spamUserName)) {
            User spamUser = storage.getterUserInStorage(spamUserName);
            ArrayList<User> spamUsers = new ArrayList<>();
            spamUsers.add(spamUser);
            filters.add(new SenderSpamFilter(spamUsers));
            System.out.println("Фильтр sender добавлен");
        } else {
            System.out.println("Такого пользователя нет в базе данных");
        }
    }

    private void addRepetitionFilter(ArrayList<SpamFilter> filters) {
        System.out.print("Введите количество повторений: ");
        String num = scan.nextLine().trim();
        if (num.matches("\\d+")) {
            filters.add(new RepetitionSpamFilter(Integer.parseInt(num)));
            System.out.println("Фильтр repetition добавлен");
        } else {
            System.out.println("Ошибка: нужно ввести целое число.");
        }
    }
}
