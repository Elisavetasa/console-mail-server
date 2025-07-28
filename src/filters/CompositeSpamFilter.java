package filters;

import mail.Message;

import java.util.ArrayList;

public class CompositeSpamFilter implements SpamFilter {
    public ArrayList<SpamFilter> filters;

    public CompositeSpamFilter(ArrayList<SpamFilter> filters) {
        this.filters = new ArrayList<>(filters);
    }

    @Override
    public boolean isSpam(Message message) {

        for (SpamFilter filter : filters){
            if (filter.isSpam(message)){
                return true;
            }
        }
        return false;

    }
}
