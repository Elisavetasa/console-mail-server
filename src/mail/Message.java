package mail;

public class Message {
    private final String caption;
    private final String text;
    private final User sender;
    private final User receiver;

    public Message(String caption, String text, User sender, User receiver){
        this.caption = caption;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver(){
        return receiver;
    }

    public String getCaption(){
        return caption;
    }

    public String getText(){
        return text;
    }
}
