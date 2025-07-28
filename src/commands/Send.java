package commands;

import mail.Message;
import mail.User;
import mail.UserStorage;

import java.util.Scanner;

public class Send implements Command{
    private final UserStorage storage;
    private final Scanner scan;

    public Send(UserStorage storage) {
        this(storage, new Scanner(System.in));
    }

    public Send(UserStorage storage, Scanner scan) {
        this.storage = storage;
        this.scan = scan;
    }

    @Override
    public void execute(){
        System.out.print("Напишите имя отправителя: ");
        String userSender = scan.nextLine();
        System.out.print("Напишите имя получателя: ");
        String userReceiver = scan.nextLine();
        if (storage.existenceUser(userReceiver) && storage.existenceUser(userSender)) {
            System.out.print("Введите заголовок: ");
            String title = scan.nextLine();
            System.out.print("Введите текст сообщения: ");
            String text = scan.nextLine();
            User senderUser = storage.getterUserInStorage(userSender);
            User receiverUser = storage.getterUserInStorage(userReceiver);
            Message message = new Message(title, text, senderUser, receiverUser);
            senderUser.sendMessage(title,text,receiverUser);
        } else {
            System.out.println("Ошибка ввода");
        }
    }
}
