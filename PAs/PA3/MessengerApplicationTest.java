/*
  Name: Tonia Le
  PID:  A15662706
 */

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Messenger Application Test
 * @author Tonia Le
 * @since  01-21-20
 */

public class MessengerApplicationTest {

    /*
      Messages defined in starter code. Remember to copy and paste these strings to the
      test file if you cannot directly access them. DO NOT change the original declaration
      to public.
     */
    private static final String INVALID_INPUT =
            "The source path given cannot be parsed as photo.";
    private static final String DENIED_USER_GROUP =
            "This operation is disabled in your user group.";
    private static final String sampleText = "A sample text message.";
    private static final String sampleText2 = "Another one";
    private static final String testText = "Text Text Text";
    private static final String testPhotoSource = "PA02.zip";
    private static final String testPhotoFail = "mc.donald";
    private static final String telephoneSticker = "default-objects/telephone";
    private static final String starStick = "starbar/stick";
    private static final String longText =
            "4qLqbvXzx6FyTU9qn8gHzmDufzUat" +
            "8pVsrphd2JVVAMNMdD4Uh3jOfxWuZ" +
            "YX9E92HqKRcgx0MJzY4H6u4CISFEN" +
            "cez39NGqW5hMn0CzUyw2TiD4BB6sQ" +
            "JEVqhkosBumeA0VKLqujgBKAcVEzV" +
            "TPocduRcdvwDyga64ilrJmItu0ZMb" +
            "BvSm8ww6VcoOvokU6JolMNO23KvgU" +
            "fc4ksFUseXOrFpQwawtu8Jm2Skm0k" +
            "B4Rk05gA6pDH4BGJ3snFCRfsbJxzq" +
            "7et1ZQtPJS4IiBJXQOnD3E9x1T5Nx" +
            "ngsP4QjWV0Hz3hhLjdoS0Poi3hhjn" +
            "HecDWKpWcoN8kM0BdmjHCYxn23zDo" +
            "iOB0wjTgcZudkwH3eX15BOKi7zqKT" +
            "czWNI1I5O0NHplz0OLgIMQuZtSWQY" +
            "n34LO4x5PQXYgJhQRE3fVQqtgHk3D" +
            "nKPomNRn8qr9OnCXVxdDxOfrL0cXp" +
            "Zr2r7cz6UQ9FX7GOyj46cgWuuAQf2" +
            "2y2OOahe";


    /*
      Global test variables. Initialize them in @Before method.
     */
    User tonia = new PremiumUser("tonia", "le");
    User div = new PremiumUser("div", "ya");
    User gar = new PremiumUser("gar", "bar");
    PremiumUser hobo = new PremiumUser("hobo", "yessir");
    PremiumUser jack = new PremiumUser("jack", "in the box");
    PremiumUser marina;
    PremiumUser justin;
    PremiumUser janine;
    PremiumUser onlyPrem;
    StandardUser onlyStan;
    StandardUser obbo;
    StandardUser twobbo;
    StandardUser threebo;

    // rooms
    MessageExchange room;
    ChatRoom testRoom = new ChatRoom();
    ChatRoom chattyOne = new ChatRoom();
    ChatRoom chattyTwo = new ChatRoom();
    ChatRoom chattyThree = new ChatRoom();
    PhotoRoom photoOne = new PhotoRoom();
    PhotoRoom photoTwo = new PhotoRoom();
    PhotoRoom photoThree = new PhotoRoom();


    /*
      The date used in Message and its subclasses. You can directly
      call this in your test methods.
     */
    LocalDate date = LocalDate.now();

    /*
     * Setup
     */
    @Before
    public void setup() {
        room = new ChatRoom();
        marina = new PremiumUser("Marina", "Instructor");
        justin = new PremiumUser("Justin", "Professor");
        janine = new PremiumUser("Janine", "Racer");
        onlyPrem = new PremiumUser("Premium User", "Ultimate");
        onlyStan = new StandardUser("Standard User", "Basic");
        obbo = new StandardUser("Obbo", "First");
        twobbo = new StandardUser("Twobbo", "Second");
        threebo = new StandardUser("Threebo", "Third");
    }

    /*
      Recap: Assert exception without message
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPremiumUserThrowsIAE() {
        marina = new PremiumUser("Marina", null);
        justin = new PremiumUser(null, "beep");

    }

    /*
      Assert exception with message
     */
    @Test
    public void testPhotoMessageThrowsODE() {
        try {
            PhotoMessage pm = new PhotoMessage(marina, testPhotoSource);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (OperationDeniedException ode) {
            assertEquals(INVALID_INPUT, ode.getMessage());
        }
    }

    /*
     * Assert message content without hardcoding the date
     */
    @Test
    public void testTextMessageGetContents() {
        try {
            TextMessage tm = new TextMessage(marina, sampleText);

            // concatenating the current date when running the test
            String expected = "<Premium> Marina [" + date + "]: A sample text message.";
            assertEquals(expected, tm.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void messageLengthTest() {
        User marina = new PremiumUser("Marina", "Instructor");
        try {
            Message testMess = new TextMessage(marina, longText);
        } catch (OperationDeniedException ode) {
            System.out.println("OperationDeniedException: Text exceeds 500 characters");
        }
    }

    @Test
    public void correctLengthTest() {
        User marina = new PremiumUser("Marina", "Instructor");
        try {
            Message testMess = new TextMessage(marina, "this should work");
            System.out.println("Valid text length!");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void nullTextTest() {
        User marina = new PremiumUser("Marina", "Instructor");
        try {
            Message testMess = new TextMessage(marina, null);

        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException: text cannot be null");
        }
    }

    @Test
    public void nullSenderTest() {
        try {
            Message testMess = new TextMessage(null, "beep");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        } catch (IllegalArgumentException iae) {
            System.out.println("IllegalArgumentException: sender cannot be null");
        }
    }

    @Test
    public void contentTest() {
        try {
            TextMessage textMess = new TextMessage(marina, "Cup of Joe please.");

            // concatenating the current date when running the test
            String expected = "<Premium> Marina [" + date + "]: Cup of Joe please.";
            assertEquals(expected, textMess.getContents());
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void MessageGetContentsTest() {
        ;
        try {
            Message testMess1 = new TextMessage(janine, "hi");
            Message testMess2 = new TextMessage(justin, "WTF");
            Message testMess3 = new TextMessage(marina, testText);
            String expectedMess1 = "<Premium> Janine [" + date + "]: hi";
            assertEquals(expectedMess1, testMess1.getContents());
            String expectedMess2 = "<Premium> Justin [" + date + "]: WTF";
            assertEquals(expectedMess2, testMess2.getContents());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }

    @Test
    public void photoSenderNullTest() {
        try {
            PhotoMessage photoMess = new PhotoMessage(null, "PA02.png");
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (IllegalArgumentException iae) {
            System.out.println("Illegal Argument Exception: sender cannot be null");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void photoSourceNullTest() {
        try {
            PhotoMessage photoMess = new PhotoMessage(marina, null);
            fail("Exception not thrown"); // will execute if last line didn't throw exception
        } catch (IllegalArgumentException iae) {
            System.out.println("Illegal Argument Exception: Photo Source cannot be null");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
            System.out.println(ode);
        }
    }

    @Test
    public void nonPremium() {
        try {
            PhotoMessage photoMess = new PhotoMessage(obbo, "PA02.png");
        } catch (OperationDeniedException ode) {
            assertEquals(DENIED_USER_GROUP, ode.getMessage());
        }
    }

    @Test
    public void photoExtension() {
        try {
            PhotoMessage photoMess = new PhotoMessage(marina, "PA02.png");
            String ext = "png";
            assertEquals(ext, photoMess.getExtension());
            String ext2 = "tif";
            PhotoMessage photoMess2 = new PhotoMessage(janine, "Happy.tif");
            assertEquals(ext2, photoMess2.getExtension());
            String ext3 = "jpeg";
            PhotoMessage photoMess3 = new PhotoMessage(justin, "40B.jpeg");
            assertEquals(ext3, photoMess3.getExtension());
        } catch (IllegalArgumentException iae) {
            fail("IAE should not be thrown");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void photoFail() {
        try {
            Message photoMessy = new PhotoMessage(marina, testPhotoFail);
            System.out.println(photoMessy.getContents());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }

    @Test
    public void getPhotoContents() {
        try {
            PhotoMessage photoMess = new PhotoMessage(marina, "PA02.png");
            String expected = "<Premium> Marina [" + date + "]: Picture at PA02.png";
            assertEquals(expected, photoMess.getContents());
            PhotoMessage photoMess2 = new PhotoMessage(janine, "Happy.tif");
            String expected2 = "<Premium> Janine [" + date + "]: Picture at Happy.tif";
            assertEquals(expected2, photoMess2.getContents());
            PhotoMessage photoMess3 = new PhotoMessage(justin, "40B.jpeg");
            String expected3 = "<Premium> Justin [" + date + "]: Picture at 40B.jpeg";
            assertEquals(expected3, photoMess3.getContents());
            assertNotEquals(expected2, photoMess.getContents());
        } catch (IllegalArgumentException iae) {
            fail("IAE should not be thrown");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void PhotoNotPremiumTest() {
        try {
            Message photoMess = new PhotoMessage(div, testPhotoSource);
            assertNotNull(photoMess);
            System.out.println(photoMess.getContents());
            Message photoMess2 = new PhotoMessage(gar, testPhotoFail);
            System.out.println(photoMess2.getContents());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }


    @Test
    public void morePhotoConstructorTest() {
        try {
            Message photoMessage2 = new TextMessage(janine, testPhotoSource);
            Message photoMessage3 = new TextMessage(justin, "beep.png");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void stickerTest() {
        try {
            StickerMessage stickerMess =
                    new StickerMessage(marina, telephoneSticker);
            String expected = "<Premium> Marina [" + date + "]: Sticker [telephone] from " +
                              "Pack [default-objects]";
            assertEquals(expected, stickerMess.getContents());
        } catch (IllegalArgumentException iae) {
            fail("IAE should not be thrown");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void getPackName() {
        try {
            StickerMessage stickerMess =
                    new StickerMessage(marina, "default-objects/telephone");
            String expected = "default-objects";
            assertEquals(expected, stickerMess.getPackName());
        } catch (IllegalArgumentException iae) {
            fail("IAE should not be thrown");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void getStickerContents() {
        try {
            StickerMessage stickerMess =
                    new StickerMessage(marina, "default-objects/telephone");
            String expected =
                    "<Premium> Marina [" + date + "]: Sticker [telephone] from Pack " +
                    "[default-objects]";
            String unexpected =
                    "<Premium> Marina [" + date + "]: Sticker [pool] from Pack " +
                    "[party]";
            assertEquals(expected, stickerMess.getContents());
            assertNotEquals(unexpected, stickerMess.getContents());
        } catch (IllegalArgumentException iae) {
            fail("IAE should not be thrown");
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    @Test
    public void anotherStickerTest() {
        try {
            Message stickerMess2 = new StickerMessage(marina, starStick);
            System.out.println(stickerMess2.getContents());
            String expectedVal = "<Premium> Marina [" + date + "]: Sticker " +
                                 "[stick] from Pack [starbar]";
            assertEquals(expectedVal, stickerMess2.getContents());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }

    @Test
    public void stickerNotPremiumTest() {
        try {
            Message stickerMess = new StickerMessage(janine, starStick);
            assertNotNull(stickerMess);
            System.out.println(stickerMess.getContents());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }

    /* redo some tests */
    @Test
    public void MessageTest() {
        try {
            Message textMessUno = new TextMessage(tonia, longText);
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }

    @Test
    public void getSenderTest() {
        try {
            Message textMessage1 = new TextMessage(obbo, "This is a test.");
            assertEquals(obbo, textMessage1.getSender());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }


    @Test
    public void getExtensionTest() {
        try {
            PhotoMessage photoMessage1 = new PhotoMessage(justin, testPhotoSource);
            assertEquals("zip", photoMessage1.getExtension());
        } catch (OperationDeniedException ode) {
            System.out.println(ode);
        }
    }

    @Test
    public void textConstructorTest() {
        try {
            Message textMess1 = new TextMessage(obbo, sampleText);
            Message textMess2 = new TextMessage(tonia, testText);
            Message textMess3 = new TextMessage(div, sampleText2);
        } catch (OperationDeniedException ode) {
            fail("ODE should not be thrown");
        }
    }

    // room related tests
    @Test
    public void joinRoomTest() {

        try {
            obbo.joinRoom(testRoom);
            assertTrue(testRoom.getUsers().contains(obbo));
            twobbo.joinRoom(testRoom);
            assertTrue(testRoom.getUsers().contains(twobbo));
            assertFalse(testRoom.getUsers().contains(threebo));
            threebo.joinRoom(testRoom);
            assertTrue(testRoom.getUsers().contains(threebo));
            obbo.joinRoom(testRoom);
            fail("slickery sneaky snake");
        } catch (OperationDeniedException ode) {
            System.out.println("got 'em");
        }
    }

    @Test
    public void QuitRoomTest() {
        try {
            twobbo.joinRoom(testRoom);
            threebo.joinRoom(testRoom);
            twobbo.quitRoom(testRoom);
            assertFalse(testRoom.getUsers().contains(obbo));
            assertFalse(testRoom.getUsers().contains(twobbo));
            assertTrue(testRoom.getUsers().contains(threebo));
            threebo.quitRoom(testRoom);
            assertFalse(testRoom.getUsers().contains(threebo));
            obbo.quitRoom(null);
            fail("No null room exception");
        } catch (OperationDeniedException ode) {
            fail("should not throw an exception");
        } catch (IllegalArgumentException e) {
            System.out.println("exceptions work");
        }
    }

    @Test
    public void createChatRoomTest() {
        ArrayList<User> chatRoomUsers = new ArrayList<>();
        chatRoomUsers.add(obbo);
        chatRoomUsers.add(justin);
        MessageExchange createdRoom = janine.createChatRoom(chatRoomUsers);
        assertFalse(createdRoom.getUsers().contains(twobbo));
        assertTrue(createdRoom.getUsers().contains(obbo));
        assertTrue(createdRoom.getUsers().contains(janine));
        // can't add present user twice
        ArrayList<User> chatRoomError = new ArrayList<>();
        chatRoomError.add(obbo);
        chatRoomError.add(obbo);
        chatRoomError.add(justin);
        MessageExchange errorRoom = janine.createChatRoom(chatRoomError);
        assertTrue(errorRoom.getUsers().contains(obbo));
        assertTrue(errorRoom.getUsers().contains(justin));
        try {
            janine.createChatRoom(null);
            fail("IAE not Caught");
        } catch (IllegalArgumentException e) {
            System.out.println("exceptions work");
        }
    }

    @Test
    public void sendMessageTest() {
        ArrayList<User> chatRoomUsers = new ArrayList<>();
        chatRoomUsers.add(obbo);
        chatRoomUsers.add(justin);
        chatRoomUsers.add(threebo);
        MessageExchange createdRoom = janine.createChatRoom(chatRoomUsers);
        obbo.sendMessage(createdRoom, MessageType.TEXT, "hello one");
        assertFalse(createdRoom.getLog().isEmpty());
        assertEquals(1, createdRoom.getLog().size());
        threebo.sendMessage(createdRoom, MessageType.TEXT, "hello two");
        assertEquals(2, createdRoom.getLog().size());
        justin.sendMessage(createdRoom, MessageType.TEXT, "hello three");
        assertNotEquals(100, createdRoom.getLog().size());
        try {
            obbo.sendMessage(null, MessageType.PHOTO, "plop.jpg");
            fail("hi, null error didn't catch?");
        } catch (IllegalArgumentException e) {
            System.out.println("got 'em");
        }
        try {
            threebo.sendMessage(createdRoom, MessageType.PHOTO, null);
            fail("catch the nullz");
        } catch (IllegalArgumentException e) {
            System.out.println("got 'em");
        }
    }

    @Test
    public void stanTests() {
        try {
            User stanError = new StandardUser(null, "my name is null bro");
            fail("catch the nullz");
        } catch (IllegalArgumentException e) {
            System.out.println("got 'em");
        }
    }

        @Test
    public void standardFetchMessageTest() {
            ArrayList<User> chatRoomUsers = new ArrayList<>();
            chatRoomUsers.add(obbo);
            chatRoomUsers.add(twobbo);
            MessageExchange newRoom = jack.createChatRoom(chatRoomUsers);
            jack.sendMessage(newRoom, MessageType.TEXT, "its late");
            assertNotEquals("<Premium> jack [2021-01-26]: its late",
                            jack.fetchMessage(newRoom));
            jack.sendMessage(newRoom, MessageType.TEXT, "hi");
            assertEquals(2, newRoom.getLog().size());

            // standard user can't get more than 100
            String fetchedContent = obbo.fetchMessage(newRoom);
            for (int i = 0; i < 100; i++) {
                obbo.sendMessage(newRoom, MessageType.TEXT, "my message" + (i));
            }

            try {
                obbo.sendMessage(newRoom, null, "This is a message.");
                fail("Exception not thrown");
            } catch (IllegalArgumentException e) {
                System.out.println("sneaky caught");
            }
            try{
                obbo.quitRoom(newRoom);
                obbo.sendMessage(newRoom, MessageType.TEXT, null);
                fail("Exception not thrown");
            }
            catch(IllegalArgumentException e){
                System.out.println("sneaky caught");
            }
        }

    @Test
    public void stanDisplayName() {
        assertEquals("Obbo", obbo.displayName());
        System.out.println("CORRECT NAME");
    }

    @Test
    public void premConstructorTest(){
        User tonia = new PremiumUser("tonia", "le");
        User div = new PremiumUser("div", "ya");
        User gar = new PremiumUser("gar", "bar");
    }

    @Test
    public void premFetchMessageTest(){
        ArrayList<User> chatRoomUsers = new ArrayList<>();
        chatRoomUsers.add(obbo);
        chatRoomUsers.add(twobbo);
        chatRoomUsers.add(threebo);
        chatRoomUsers.add(janine);
        MessageExchange createdRoom = justin.createChatRoom(chatRoomUsers);
        for(int i = 1; i < 101; i++){
            justin.sendMessage(createdRoom, MessageType.TEXT, "please work");
        }
        String fetchedContent = obbo.fetchMessage(createdRoom);
        String[] lines = fetchedContent.split("\n");
        System.out.println(lines.length);
        try{
            janine.sendMessage(null, MessageType.TEXT, "this too shall pass");
            fail("no exception for this");
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception!");
        }
        try{
            obbo.sendMessage(createdRoom, MessageType.TEXT, null);
            fail("no exception for this");
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception!");
        }
        try{
            justin.quitRoom(createdRoom);
            justin.sendMessage(createdRoom, MessageType.TEXT, null);
            fail("no exception for this");
        }
        catch(IllegalArgumentException e){
            System.out.println("Exception!");
        }
    }
    @Test
    public void premDisplayNameTest(){
        assertEquals("<Premium> tonia", tonia.displayName());
        System.out.println("das me");
        assertEquals("<Premium> div", div.displayName());
        System.out.println("das my first RA");
        assertNotEquals("<Premium> josh", gar.displayName());
        System.out.println("who dat");
    }

    @Test
    public void premSetCustomTitleTest(){
        janine.setCustomTitle("Lightning Mc");
        assertEquals("<Lightning Mc> Janine", janine.displayName());
    }

    @Test
    public void premCreatePhotoRoomTest(){
        ArrayList<User> testRoomery = new ArrayList<>();
        testRoomery.add(jack);
        testRoomery.add(marina);
        testRoomery.add(hobo);
        MessageExchange createdRoom = hobo.createPhotoRoom(testRoomery);
        assertTrue(createdRoom.getUsers().contains(marina));
        assertTrue(createdRoom.getUsers().contains(hobo));
        assertFalse(createdRoom.getUsers().contains(gar));
    }

    @Test
    public void chatRoomConst(){
        ChatRoom chattyOne = new ChatRoom();
        ChatRoom chattyTwo = new ChatRoom();
        ChatRoom chattyThree = new ChatRoom();
    }

    @Test
    public void getLogTest(){
        ArrayList<User> chatRoomUsers = new ArrayList<>();
        chatRoomUsers.add(twobbo);
        chatRoomUsers.add(hobo);
        chatRoomUsers.add(obbo);
        MessageExchange  newRoom = div.createChatRoom(chatRoomUsers);
        div.sendMessage(newRoom, MessageType.TEXT, "im really tired of tests");
        twobbo.sendMessage(newRoom, MessageType.TEXT, "heres another");
        assertEquals(2, newRoom.getLog().size());
    }

    @Test
    public void addAndGetUsersAndRemoveUserTest(){
        chattyOne.addUser(tonia);
        assertEquals(1, chattyOne.getUsers().size());
        chattyOne.addUser(div);
        chattyOne.addUser(gar);
        chattyOne.removeUser(gar);
        chattyOne.removeUser(div);
        assertEquals(1, chattyOne.getUsers().size());
        chattyOne.addUser(twobbo);
        assertEquals(2, chattyOne.getUsers().size());
        chattyOne.addUser(hobo);
        chattyOne.addUser(threebo);
        assertNotEquals(3, chattyOne.getUsers().size());
        assertEquals(4, chattyOne.getUsers().size());
        chattyOne.removeUser(hobo);
        assertEquals(3, chattyOne.getUsers().size());
        assertEquals(3, chattyOne.getUsers().size());
    }


    @Test
    public void chatRoomRecordMessageTest(){
        try{
            Message sample1 = new TextMessage(obbo, "This is a text.");
            Message sample2= new TextMessage(twobbo, "This is a text.");
            Message sample3 = new PhotoMessage(tonia, "veggienugs.png");
            chattyTwo.recordMessage(sample1);
            assertEquals(1, chattyTwo.getLog().size());
            chattyTwo.recordMessage(sample2);
            assertEquals(2, chattyTwo.getLog().size());
            chattyTwo.recordMessage(sample3);
            assertEquals(3, chattyTwo.getLog().size());
        }
        catch(OperationDeniedException ode){
            fail("should not throw exception");
        }
    }

    @Test
    public void photoRoomConst(){
        PhotoRoom photoOne = new PhotoRoom();
        PhotoRoom photoTwo = new PhotoRoom();
        PhotoRoom photoThree = new PhotoRoom();
    }

    @Test
    public void getLogTestPhoto() {
        PremiumUser lola = new PremiumUser("Lola", "mama");
        photoOne.addUser(lola);
        lola.sendMessage(photoOne, MessageType.PHOTO, "WHY.jpg");
        assertEquals(1, photoOne.getLog().size());
    }

    @Test
    public void photoRoomAddAndRemoveAndGetUser(){
        assertFalse(photoTwo.addUser(obbo));
        assertTrue(photoTwo.addUser(hobo));
        assertTrue(photoTwo.addUser(jack));
        assertEquals(2, photoTwo.getUsers().size());
        photoTwo.removeUser(hobo);
        photoTwo.addUser(hobo);
        assertEquals(2, photoTwo.getUsers().size());
        photoTwo.addUser(div);
        photoTwo.removeUser(div);
        photoTwo.removeUser(jack);
        assertEquals(1, photoTwo.getUsers().size());
        assertNotEquals(3, photoTwo.getUsers().size());
    }

    @Test
    public void photoRoomRecordTest(){
        try{
            Message sample1 = new PhotoMessage(hobo, "makeitstop.png");
            Message sample2 = new PhotoMessage(hobo, "imtired.gif");
            Message sample3 = new PhotoMessage(hobo, "please.jpg");
            photoTwo.recordMessage(sample1);
            assertEquals(1, photoTwo.getLog().size());
            photoTwo.recordMessage(sample2);
            photoTwo.recordMessage(sample3);
            assertEquals(3, photoTwo.getLog().size());
            assertEquals(0, photoThree.getLog().size());
        }
        catch(OperationDeniedException ode){
            fail("no exceptions expected");
        }
    }
}


