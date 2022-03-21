/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * The TextMessage class extends the Message class.
 * It accesses methods from the Message class to create a method that gets the message content.
 *
 * @author Tonia Le
 * @since 01-26-21
 */
public class TextMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String EXCEED_MAX_LENGTH =
            "Your input exceeded the maximum length limit.";
    private static final int MAX_TEXT_LENGTH = 500;

    /**
     * Constructs the sender from the User class and the text of the message
     *
     * @param sender: sender from the User class
     * @param text: the content of the text message in string form
     * @throws OperationDeniedException: throws exception when text exceeds 500 in length
     */
    public TextMessage(User sender, String text)
            throws OperationDeniedException {
        /* Construct sender and text */
        super(sender);
        // sender & text cannot be null
        if (sender == null || text == null) {
            throw new IllegalArgumentException();
        }
        // text cannot be greater than 500 in length
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new OperationDeniedException(EXCEED_MAX_LENGTH);
        }
        // initialize contents
        this.contents = text;
    }

    /**
     * Method getContents():
     *
     * @return returns a string of the contents of a text message in the specified form:
     * “SenderName [year-month-day]: A sample text message.”
     */
    public String getContents() {
        String content =
                getSender().displayName() + " [" + getDate().toString() + "]: " + this.contents;
        return content;
    }
}
