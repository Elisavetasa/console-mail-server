package filters;

import mail.Message;

import java.util.ArrayList;

public class KeywordsSpamFilter implements SpamFilter {
    private final ArrayList<String> spamWords;

    public KeywordsSpamFilter(ArrayList<String> spamWords) {
        this.spamWords = new ArrayList<>(spamWords);
    }

    @Override
    public boolean isSpam(Message message) {
        String title = message.getCaption();
        String text = message.getText();
        for (String spamWord : spamWords){
            if (title.toLowerCase().contains(spamWord.toLowerCase()) || text.toLowerCase().contains(spamWord.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
