package commands;

import mail.Message;
import mail.UserStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class Outbox implements Command{
    private final UserStorage storage;
    private final Scanner scan;

    public Outbox(UserStorage storage) {
        this(storage, new Scanner(System.in));
    }

    public Outbox(UserStorage storage, Scanner scan) {
        this.storage = storage;
        this.scan    = scan;
    }

    @Override
    public void execute(){
        System.out.print("Введите имя пользователя: ");
        String userName = scan.nextLine();
        if (storage.existenceUser(userName)) {
            ArrayList<Message> outbox = storage.getterUserInStorage(userName).getOutbox();
            if (outbox.isEmpty()){
                System.out.println("Писем нет");
            }
            for (int i = 0; i < outbox.size(); i++) {
                System.out.println("============================");
                System.out.println(storage.getterUserInStorage(userName).getCaptionOutbox(i));
                System.out.println(storage.getterUserInStorage(userName).getTextOutbox(i));
                System.out.println("============================");
            }
        } else {
            System.out.println("Такого пользователя нет в базе данных");
        }
    }
}
