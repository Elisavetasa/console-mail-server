package filters;

import mail.Message;

public class SimpleSpamFilter implements SpamFilter {
    @Override
    public boolean isSpam (Message message){
        String title = message.getCaption();
        String text = message.getText();
        if (title.toLowerCase().contains("spam") || text.toLowerCase().contains("spam")){
            return true;
        } else {
            return false;
        }
    }
}