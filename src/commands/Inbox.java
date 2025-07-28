package commands;

import mail.Message;
import mail.UserStorage;

import java.util.ArrayList;
import java.util.Scanner;

public class Inbox implements Command{
    private final UserStorage storage;
    private final Scanner scan;

    public Inbox(UserStorage storage) {
        this(storage, new Scanner(System.in));
    }

    public Inbox(UserStorage storage, Scanner scan) {
        this.storage = storage;
        this.scan    = scan;
    }

    @Override
    public void execute(){
        System.out.print("Введите имя пользователя: ");
        String userName = scan.nextLine();
        if (storage.existenceUser(userName)) {
            ArrayList<Message> inbox = storage.getterUserInStorage(userName).getInbox();
            if (inbox.isEmpty()){
                System.out.println("Писем нет");
            }
            for (int i = 0; i < inbox.size(); i++) {
                System.out.println("============================");
                System.out.println(storage.getterUserInStorage(userName).getCaptionInbox(i));
                System.out.println(storage.getterUserInStorage(userName).getTextInbox(i));
                System.out.println("============================");
            }
        } else {
            System.out.println("Такого пользователя нет в базе данных");
        }
    }
}
