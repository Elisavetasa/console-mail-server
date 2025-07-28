package filters;

import mail.Message;

public class RepetitionSpamFilter implements SpamFilter {
    public int count;

    public RepetitionSpamFilter(int count) {
        this.count = count;
    }

    @Override
    public boolean isSpam(Message message) {
        return hasRepetition(message.getCaption())
                || hasRepetition(message.getText());
    }

    private boolean hasRepetition(String content) {
        if (content == null || content.isBlank()) {
            return false;
        }
        String[] words = content.toLowerCase().split("\\W+");
        for (String word : words) {
            int cnt = 0;
            for (String s : words) {
                if (word.equals(s)) {
                    if (++cnt > count) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
