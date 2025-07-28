package commands;

import mail.Message;
import mail.UserStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class Spam implements Command{
    private final UserStorage storage;
    private final Scanner scan;

    public Spam(UserStorage storage) {
        this(storage, new Scanner(System.in));
    }

    public Spam(UserStorage storage, Scanner scan) {
        this.storage = storage;
        this.scan    = scan;
    }

    @Override
    public void execute(){
        System.out.print("Введите имя пользователя: ");
        String userName = scan.nextLine();
        if (storage.existenceUser(userName)) {
            ArrayList<Message> spam = storage.getterUserInStorage(userName).getSpam();
            if (spam.isEmpty()){
                System.out.println("Писем нет");
            }
            for (int i = 0; i < spam.size(); i++) {
                System.out.println("============================");
                System.out.println(storage.getterUserInStorage(userName).getCaptionSpam(i));
                System.out.println(storage.getterUserInStorage(userName).getTextSpam(i));
                System.out.println("============================");
            }
        } else {
            System.out.println("Такого пользователя нет в базе данных");
        }
    }
}
