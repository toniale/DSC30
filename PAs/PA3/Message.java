/*
 * NAME: Tonia Le
 * PID: A15662706
 */
import java.time.LocalDate;

/** Abstract class that contains three instance variables: sender of the message,
 * time the message is created, and the contents of the message.
 *
 * @author Tonia Le
 * @since 01-26-21
 */

public abstract class Message {

    // Error message to use in OperationDeniedException
    protected static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";

    // instance variables
    private LocalDate date;
    private User sender;
    protected String contents;

    /**
     * Constructs the sender and the date fields
     * @param sender of the User class that represents the sender of the message
     */
    public Message(User sender) {
        this.date = LocalDate.now();
        this.sender = sender;
        if (this.sender == null) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Method LocalDate returns the date of the message
     * @return date of the message
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Method User returns the sender of the message
     * @return the sender of the message
     */
    public User getSender() {
        return this.sender;
    }

    public abstract String getContents();

}
