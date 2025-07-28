package mail;

import filters.SpamFilter;

import java.util.ArrayList;

public class User implements SpamFilter {
    private final String userName;
    private final ArrayList<Message> inbox;
    private final ArrayList<Message> outbox;
    private final ArrayList<Message> spam;
    public SpamFilter spamFilter;

    public User(String userName, ArrayList<Message> inbox, ArrayList<Message> outbox, ArrayList<Message> spam){
        this.userName = userName;
        this.inbox = inbox;
        this.outbox = outbox;
        this.spam = spam;
    }

    public User(String userName){
        this(userName,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Message> getInbox() {
        return inbox;
    }

    public String getCaptionInbox(int i){
        return inbox.get(i).getCaption();
    }

    public String getTextInbox(int i){
        return inbox.get(i).getText();
    }

    public String getCaptionOutbox(int i){
        return outbox.get(i).getCaption();
    }

    public String getTextOutbox(int i){
        return outbox.get(i).getText();
    }

    public String getCaptionSpam(int i){
        return spam.get(i).getCaption();
    }

    public String getTextSpam(int i){
        return spam.get(i).getText();
    }

    public void setInbox(Message message){
        inbox.add(message);
    }

    public void setOutbox(Message message){
        outbox.add(message);
    }

    public void setSpam(Message message){
        spam.add(message);
    }

    public ArrayList<Message> getOutbox() {
        return outbox;
    }

    public ArrayList<Message> getSpam() {
        return spam;
    }

    public SpamFilter getSpamFilter() {
        return spamFilter;
    }

    public void sendMessage(String caption, String text, User receiver){
        Message message = new Message(caption, text, this, receiver);
        if (receiver.isSpam(message)) {
            receiver.setSpam(message);
            System.out.println("Сообщение отправлено");
        } else {
            receiver.setInbox(message);
            System.out.println("Сообщение отправлено");
        }
        this.setOutbox(message);
    }

    public void setSpamFilter(SpamFilter spamFilter) {
        this.spamFilter = spamFilter;
    }

    @Override
    public boolean isSpam(Message message) {
        if (this.spamFilter != null) {
            return this.spamFilter.isSpam(message);
        }
        return false;
    }
}
