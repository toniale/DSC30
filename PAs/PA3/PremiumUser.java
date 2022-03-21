/*
 * NAME: Tonia Le
 * PID: A15662706
 */
import java.util.ArrayList;

/**
 * Premium User class extends the User class and represents what the Premium Users can do
 *
 * @author Tonia Le
 * @since 01-26-21
 */
public class PremiumUser extends User {

    // instance variable
    private String customTitle;

    /**
     * Constructor for the premium user
     *
     * @param username: name of the user
     * @param bio: bio of the user
     */
    public PremiumUser(String username, String bio) {
        super(username, bio);
        this.customTitle = "Premium";
    }

    /**
     * Method that gets the messages from the message exchange log
     *
     * @param me is the MessageExchange platform
     * @return returns a string of the messages from the log on separate lines
     * @throws IllegalArgumentException if the user can't join the room and/ me is null
     */
    public String fetchMessage(MessageExchange me) {
        // if me is null
        if (me == null){
            throw new IllegalArgumentException();
        }
        // user didn't join the room exception
        if (!this.rooms.contains(me)){
            throw new IllegalArgumentException();
        }
        // append content to string
        StringBuilder messageString = new StringBuilder();
        // loop through each message
        for (Message message : me.getLog()) {
            messageString.append(message.getContents());
            messageString.append("\n");
        }
        // convert string builder to string
        return messageString.toString();
    }

    /**
     * Method that create the photo room, where users will join if they can, if not, exceptions
     * will be thrown
     *
     * @param users: a list of users in the Photo Rooms
     * @return the photo room instance
     * @throws OperationDeniedException if the user can't join the room
     * @throws IllegalArgumentException if the input parameter is null
     */
    public MessageExchange createPhotoRoom(ArrayList<User> users) {
        /* Create a photo room*/
        PhotoRoom photoRoom = new PhotoRoom();
        users.add(this);
        for (User user : users){
            try {
                user.joinRoom(photoRoom);
            } catch (OperationDeniedException ode){
                System.out.println(ode.getMessage());
            }
        }
        return photoRoom;
    }

    /**
     * The displayed name for the premium user
     *
     * @return the display of the username and custom title
     */
    public String displayName() {
        return "<" + this.customTitle + ">" + " " + this.username;
    }

    /**
     * sets customTitle to a new value
     *
     * @param newTitle the title to replace customTitle
     */
    public void setCustomTitle(String newTitle) {
        this.customTitle = newTitle;
    }
}
