import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class ContactTest {


//showmenu

    public static void showMenu(){
        System.out.println(
                "        1. View contacts.\n" +
                "        2. Add a new contact.\n" +
                "        3. Search a contact by name.\n" +
                "        4. Delete an existing contact.\n" +
                "        5. Exit.\n" +
                "        Enter an option (1, 2, 3, 4 or 5):" );
    }

// showContacts

    public static List<Contact> showContacts(Path filePath, List<Contact> cList) throws IOException{
        System.out.println("Name        | Number\n");
        System.out.println("-----------------------");
        for (Contact c: cList) {
            System.out.printf("%5s %-10s | %d \n", c.getFirstName(), c.getLastName(), c.getNumber());
        }
        return cList;
    }


//searchcontacts

//addmethods

//deletecontact
public static void main(String[] args) {

//          PATH TO OUR PROJECT FILE
//        Path path = Paths.get("src");
//        System.out.println(path.toAbsolutePath());


//        PATH TO OUR SOURCE
//        Path pathToThisFile = Paths.get("src", "Contact.java");
//        System.out.println(pathToThisFile);

//        CREATION OF OUR DIRECTORY DATA
    Path pathToOurDataDirectory = Paths.get("project/src/data");
    try{
        if(Files.notExists(pathToOurDataDirectory)){
            Files.createDirectories(pathToOurDataDirectory);
        } else{
            System.out.println(pathToOurDataDirectory + " already exists.");
        }
    }catch(IOException ioe){
        System.out.println("Issue with the create path to directory method.");
        ioe.printStackTrace();
    }

//         CREATION OF OUR CONTACT.TXT FILE

    Path pathToOurFile = Paths.get("project/src/data", "contacts.txt");
    try{
        if (Files.notExists(pathToOurFile)){
            Files.createFile(pathToOurFile);
        }else{
            System.out.println(pathToOurFile + "Already exists");
        }
    }catch(IOException ioe){
        System.out.println("There was an issue with the text file.");
        ioe.printStackTrace();
    }

    List<String> Contacts = Arrays.asList(
            "Harry",
            "Sally",
            "Dick",
            "Jane",
            "Bruce"

    );
        try{
            Files.write(pathToOurFile, Contacts);
        } catch(FileAlreadyExistsException fae){
            System.out.println(fae + " already exists.");
        } catch(IOException ioe){
            System.out.println("there was an issue with Contact Array List");
            ioe.printStackTrace();
        }
        showMenu();
    }
}
