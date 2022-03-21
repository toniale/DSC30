/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * StandardUser class extends the User class.
 *
 * @author Tonia Le
 * @since 01-26-21
 */

import java.util.List;

public class StandardUser extends User {

    // Message to append when fetching non-text message
    private static final String FETCH_DENIED_MSG =
            "This message cannot be fetched because you are not a premium user.";
    // standard user max messages
    private static final int maxMessages = 100;

    public StandardUser(String username, String bio) {
        super(username, bio);
    }

    /**
     * Method that gets the messages from the MessageExchange
     *
     * @param me: the MessageExchange platform
     * @return returns a string composed of the message contents
     */
    public String fetchMessage(MessageExchange me) {
        //
        StringBuilder messageString = new StringBuilder();
        // create a sublist that starts 100 away from the size of the log
        if (me.getLog().size() < 100) {
            for (Message message : me.getLog()) {
                if (message instanceof TextMessage) {
                    messageString.append(FETCH_DENIED_MSG);
                }
                messageString.append(message.getContents());
                messageString.append("\n");
            }
        } else {
            List<Message> sub = me.getLog().subList(me.getLog().size() - maxMessages,
                                                    me.getLog().size());
            // append for every message in the sublist
            for (Message message : sub) {
                if (message instanceof TextMessage) {
                    messageString.append(FETCH_DENIED_MSG);
                }
                messageString.append(message.getContents());
                messageString.append("\n");
            }
        }
        return messageString.toString();
    }

    /**
     * Method that displays the username
     * @return the username
     */
    public String displayName() {
        return this.username;
    }
}
