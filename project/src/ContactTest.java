import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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


// searchContactsByName

    public static void searchContactsByName(List<Contact> cList){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please type in the name you are looking for: ");
        String userInput =sc.nextLine();
        for (Contact c : cList){
            if(c.getFirstName().contains(userInput) || c.getLastName().contains(userInput)){
                System.out.printf("%1s %5s | %d \n", c.getFirstName(), c.getLastName(), c.getNumber());
            } else{
                System.out.println("The name " + userInput + " does not exist in our file.");
                searchContactsByName(cList);
            }
        }
    }


//addmethods MUST FINISH
    public static void addContact(List<Contact> cList){
        Scanner sc = new Scanner(System.in);
        System.out.println("please add the first name");
        String fNameInput = sc.nextLine();
        for(Contact c : cList){
            c.getFirstName();
        }
    }


    // MUST FINISH
    public static void saveListAsString(){}


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

    List<Contact> contactList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    String userInput;
    do{

        showMenu();
        userInput = sc.nextLine();
        switch(userInput){
            case "1":
                showContacts(pathToOurFile, contactList);
                break;
            case"2":
                addContact(pathToOurFile, contactList);
                break;
            case "3":
                searchContactsByName(contactList);
            case"4":
                //Delete contact
                break;
            case"5":
                Files.write(pathToOurFile, saveListAsString(contactList), StandardOpenOption.APPEND);
                System.out.println("Farewell");
                return;

            default:
                System.out.println("Wrong input, please choose an option between 1 through 5.");
        }

    }while(!userInput.equalsIgnoreCase("5"));

    List<Contact> Contacts = Arrays.asList(
//            "Harry",
//            "Sally",
//            "Dick",
//            "Jane",
//            "Bruce",
            new Contact("Joe","Demagio", "444-4444"),
            new Contact("Lisa","Simpson","588-2300"),
            new Contact("Eva","Green","773-2424")

    );
        try{
            Files.write(pathToOurFile, (Iterable<? extends CharSequence>) Contacts);
        } catch(FileAlreadyExistsException fae){
            System.out.println(fae + " already exists.");
        } catch(IOException ioe){
            System.out.println("there was an issue with Contact Array List");
            ioe.printStackTrace();
        }
//        showContacts("","")
    }
}
