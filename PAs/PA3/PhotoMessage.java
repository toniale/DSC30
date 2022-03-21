/*
 * NAME: Tonia Le
 * PID: A15662706
 */

/**
 * The PhotoMessage class extends the Message class.
 * It accesses methods from the Message class to create a method that gets the message content,
 * as well as a method that retrieves the extension of the photoSource.
 *
 * @author Tonia Le
 * @since 01-25-21
 */
public class PhotoMessage extends Message {

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";
    private static final String [] acceptableExtension = new String [] {"jpg",
                                                                        "jpeg",
                                                                        "gif",
                                                                        "png",
                                                                        "tif",
                                                                        "tiff",
                                                                        "raw"};

    // instance variable
    private String extension;

    /**
     * Constructor of PhotoMessage that extends the Message abstract class, initializes handles
     * exceptions
     *
     * @param sender: a given sender from the User class
     * @param photoSource: the filename of the photo, given in the form of [filename].[extension]
     * @throws OperationDeniedException when the sender is not a premium user and when the
     * photoSource is not of an acceptable file extension
     */
    public PhotoMessage(User sender, String photoSource)
                        throws OperationDeniedException {
        /* Constructor of PhotoMessage */
        super(sender);
        this.contents = photoSource;
        if (sender == null || photoSource == null){
            throw new IllegalArgumentException();
        }
        if (!(sender instanceof PremiumUser)){
            throw new OperationDeniedException(DENIED_USER_GROUP);
        }
        // find the dot before extension
        int dotIndex = photoSource.lastIndexOf(".");
        if (dotIndex > 0){
            this.extension = photoSource.substring(dotIndex + 1).toLowerCase();
        }
        // checking for valid extensions
        boolean isValid = false;
        for (int i = 0; i < acceptableExtension.length; i++){
            if (this.extension.equals(acceptableExtension[i]))
                isValid = true;
        }
        // throw operation is extension is not valid
        if (!isValid){
            throw new OperationDeniedException((INVALID_INPUT));
        }
    }

    /**
     * Method that returns the content of the message as a string
     *
     * @return returns the content in the form of:
     * “SenderName [date]: Picture at photoSource”
     */
    public String getContents() {
        /* Returns the extension of the message */
        String content = getSender().displayName() + " [" + getDate().toString() +
                         "]: " + "Picture at " + this.contents;
        return content;
    }

    /**
     * Method that returns the extension of the message
     *
     * @return photoSource's file extension
     */
    public String getExtension() {
        /* Returns the extension of the message */
        return this.extension;
    }
}
