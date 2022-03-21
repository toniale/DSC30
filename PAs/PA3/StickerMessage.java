/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * The StickerMessage extends the Message class, using its methods to create methods to: get the
 * content of the message and sticker pack name.
 *
 * @author Tonia Le
 * @since 01-26-21
 */

public class StickerMessage extends Message {

    // instance variable
    private String packName;

    /** Constructs the StickerMessage class
     *
     * @param sender: the individual who is sending the message
     * @param stickerSource: the source that contains the sticker
     * @throws OperationDeniedException when the sender is not a Premium User
     * @throws IllegalArgumentException when parameters are null
     */
    public StickerMessage(User sender, String stickerSource)
            throws OperationDeniedException {
        super(sender);
        // sender & stickerSource cannot be null
        if (sender == null || stickerSource == null) {
            throw new IllegalArgumentException();
        }
        // must be premium user
        if (!(sender instanceof PremiumUser)) {
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        String forwardSlash = "/";
        String [] chunk = stickerSource.split(forwardSlash);
        // extract name of sticker pack
        this.packName = chunk[0];
        // extract sticker name
        this.contents = chunk[1];
    }

    /** getContents method gets the content of the message and puts it into a string format
     *
     * @return a string of the contents in the format of:
     * "SenderName [date]: Sticker [sticker name] from Pack [sticker pack name]”
     */
    public String getContents() {
        String content = getSender().displayName() + " [" + getDate().toString() +
                         "]: " + "Sticker [" + this.contents + "]" + " from Pack [" +
                         this.packName + "]";
        return content;
    }

    /** getPackName returns the name of the sticker pack
     *
     * @return returns the name of the sticker pack, a string
     */
    public String getPackName() {
        return this.packName;
    }
}
