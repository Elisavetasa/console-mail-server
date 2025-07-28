package filters;

import mail.Message;

public interface SpamFilter {
    public boolean isSpam(Message message);
}
