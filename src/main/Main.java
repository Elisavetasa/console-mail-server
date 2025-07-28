package main;

import commands.*;
import mail.UserStorage;

import java.util.Scanner;

public class Main {
    public static UserStorage storage = new UserStorage();

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        while (true) {
            String userInput = console.nextLine().strip();
            inputProcessing(userInput);
        }
    }

    public static void inputProcessing(String userInput) {
        switch (userInput) {
            case "add" -> new Add(storage).execute();
            case "list" -> new List(storage).execute();
            case "send" -> new Send(storage).execute();
            case "inbox" -> new Inbox(storage).execute();
            case "spam" -> new Spam(storage).execute();
            case "outbox" -> new Outbox(storage).execute();
            case "setfilter" -> new SetFilter(storage).execute();
            default -> System.out.println("Такой команды не существует");
        }
    }
}