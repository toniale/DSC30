/*
 * NAME: Tonia Le
 * PID: A15662706
 */
import java.util.ArrayList;

/**
 * ChatRoom class implements the MessageExchange interface to add, remove, retrieve information
 * about the users in there
 *
 * @author Tonia Le
 * @since 01-26-20
 */

public class ChatRoom implements MessageExchange {

    // instance variables
    private ArrayList<User> users;
    private ArrayList<Message> log;

    /**
     * Constructor of ChatRoom
     */
    public ChatRoom() {
        this.users = new ArrayList<User>();
        this.log = new ArrayList<Message>();
    }

    /**
     * This method gets the log of the chat
     *
     * @return returns the log of the chat
     */
    public ArrayList<Message> getLog() {
        return this.log;
    }

    /**
     * Method that adds users
     *
     * @param u: User to add
     * @return true when the user, u, is added
     */
    public boolean addUser(User u) {
        this.users.add(u);
        return true;
    }

    /**
     * This method removes users from the room
     *
     * @param u User to remove.
     */
    public void removeUser(User u) {
        this.users.remove(u);
    }

    /**
     * A method that gets the users of the chat room
     * @return returns the users in the chat room
     */
    public ArrayList<User> getUsers() {
        return this.users;
    }

    /**
     * A method that adds a new message to the log of the chat room
     * @param m Message to add.
     * @return the chat room log after a new message, m, is added and true when m is added
     */
    public boolean recordMessage(Message m) {
        this.log.add(m);
        return true;
    }
}
