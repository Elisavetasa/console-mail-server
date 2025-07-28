package filters;

import mail.Message;
import mail.User;

import java.util.ArrayList;

public class SenderSpamFilter implements SpamFilter {
    private ArrayList<User> userSpammer;

    public SenderSpamFilter(ArrayList<User> userSpammer) {
        this.userSpammer = new ArrayList<>(userSpammer);
    }

    @Override
    public boolean isSpam(Message message) {
        User userSender = message.getSender();
        for (User spammer : userSpammer){
            if (userSender == spammer){
                return true;
            }
        }
        return false;
    }
}
