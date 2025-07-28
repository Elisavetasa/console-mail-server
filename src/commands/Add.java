package commands;

import mail.User;
import mail.UserStorage;

import java.util.Scanner;

public class Add implements Command{
    private final UserStorage storage;
    private final Scanner scan;

    public Add(UserStorage storage) {
        this(storage, new Scanner(System.in));
    }

    public Add(UserStorage storage, Scanner scan) {
        this.storage = storage;
        this.scan = scan;
    }

    @Override
    public void execute() {
        System.out.print("Введите имя пользователя: ");
        String userName = scan.nextLine();
        if (storage.existenceUser(userName)) {
            System.out.println("Такой пользователь уже существует");
            return;
        }
        User user = new User(userName);
        System.out.printf("Пользователь '%s' добавлен\n", user.getUserName());
        storage.addUserInStorage(user);
    }
}
