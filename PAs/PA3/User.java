/*
 * NAME: Tonia Le
 * PID: A15662706
 */

import java.util.ArrayList;

/**
 * User class is an abstract class that defines the functionality of a user in the messaging app.
 *
 * @author Tonia Le
 * @since 01-26-21
 */

public abstract class User {

    // Error message to use in OperationDeniedException
    protected static final String JOIN_ROOM_FAILED =
            "Failed to join the chat room.";
    protected static final String INVALID_MSG_TYPE =
            "Cannot send this type of message to the specified room.";

    // instance variables
    protected String username;
    protected String bio;
    protected ArrayList<MessageExchange> rooms;

    /**
     *  Constructor of the User class
     *
     * @param username: a string of the given user's username
     * @param bio: a string that represents the user's bio
     */
    public User(String username, String bio) {
        this.username = username;
        this.bio = bio;
        this.rooms = new ArrayList<MessageExchange>();

        if (this.username == null || this.bio == null){
            throw new IllegalArgumentException();
        }
    }

    /**
     * Sets the user's bio as a new given one
     *
     * @param newBio: the new bio that replaces the old bio
     */
    public void setBio(String newBio) {
        this.bio = newBio;
        // exception if given a null bio
        if (newBio == null){
            throw new IllegalArgumentException();
        }
    }

    /** Method that returns the user's bio
     *
     * @return the user's bio
     */
    public String displayBio() {

        return this.bio;
    }

    /** Method that adds users to the list of users in the message exchange platform and adds the
     *  platform to the list of rooms of the current user
     *
     * @param me is the MessageExchange room
     * @throws OperationDeniedException when a user cannot be added to the room and/ the
     * MessageExchange room is already in the list of rooms, rooms.
     */
    public void joinRoom(MessageExchange me) throws OperationDeniedException {
        if (me == null){
            throw new IllegalArgumentException();
        }
        // add user to list of user
        // ode if can't add user
        if (!(me.addUser(this))){
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        // ode if MessageExchange is already in the room list
        if (this.rooms.contains(me)){
            throw new OperationDeniedException(JOIN_ROOM_FAILED);
        }
        // adds platform to list of rooms of this user
        this.rooms.add(me);
    }

    /**
     * Method that removes the message exchange platform form the list of rooms.
     * It also removes the user from the list of users.
     *
     * @param me, the MessageExchange room
     */
    public void quitRoom(MessageExchange me) {
        if (me == null){
            throw new IllegalArgumentException();
        }
        // remove message exchange from rooms
        this.rooms.remove(me);
        // remove user from message exchange
        me.removeUser(this);
    }

    /**
     * createChatRoom is a method that creates a new instance of the ChatRoom.
     * Every user in the users list will join the room unless they're already there
     *
     * @param users is an array list containing the users in the chatroom
     * @return the instance of ChatRoom
     */
    public MessageExchange createChatRoom(ArrayList<User> users) {
        ChatRoom chatRoom = new ChatRoom();
        // null users
        if (users == null){
            throw new IllegalArgumentException();
        }
        // add current user to the room
        users.add(this);
        // add each user from the users list, which now includes the current user, to the chatRoom
        for (User user : users){
            try {
                user.joinRoom(chatRoom);
            } catch (OperationDeniedException ode){
                System.out.println(ode.getMessage());
            }
        }
        return chatRoom;
    }

    /**
     * sendMessage creates an instance of a message with the correct type specified by the msgType
     * It is recorded in the MessageExchange instance
     *
     * @param me: the MessageExchange platform
     * @param msgType: the type of message
     * @param contents: what is in the message
     */
    public void sendMessage(MessageExchange me, MessageType msgType, String contents) {

        // exception for nulls
        if (me == null || msgType == null || contents == null){
            throw new IllegalArgumentException();
        }
        if (!me.getUsers().contains(this)){
            throw new IllegalArgumentException();
        }

        // checking for invalid input
        // Text Type
        if (msgType == MessageType.TEXT) {
            try {
                Message textType = new TextMessage(this, contents);
                if (!me.recordMessage(textType)) {
                    throw new OperationDeniedException(INVALID_MSG_TYPE);
                }
            } catch (OperationDeniedException ode) {
                System.out.println(ode.getMessage());
            }
        }
        // Photo Type
        if (msgType == MessageType.PHOTO) {
            try {
                Message photoType = new PhotoMessage(this, contents);
                if (!me.recordMessage(photoType)) {
                    throw new OperationDeniedException(INVALID_MSG_TYPE);
                }
            } catch (OperationDeniedException ode) {
                System.out.print(ode.getMessage());
            }
        }
        // Sticker Type
        if (msgType == MessageType.STICKER) {
            try {
                Message stickerType = new StickerMessage(this, contents);
                if (!me.recordMessage(stickerType)) {
                    throw new OperationDeniedException(INVALID_MSG_TYPE);
                }
            } catch (OperationDeniedException ode) {
                System.out.print(ode.getMessage());
                }
            }
        }

    public abstract String fetchMessage(MessageExchange me);

    public abstract String displayName();
}
