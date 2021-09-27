import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ContactTest {

private List<String> storeList;

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

    public static List<Contact> showContacts(Path pathToOurFile, List<Contact> cList) throws IOException{
        System.out.println("Name        | Number\n");
        System.out.println("-----------------------");
        for (Contact c: cList) {
            System.out.printf("%5s %-10s | %s \n", c.getFirstName(), c.getLastName(), c.getNumber());
        }
        return cList;
    }

    public static void readFile(Path pathToOurFile) throws IOException {
        List<String> contacts = Files.readAllLines(pathToOurFile);
        for (int i = 0; i < contacts.size(); i += 1) {
            contacts.get(i);
        }
    }

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
//    public static void addFirstName(List<Contact> cList){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("please add the first name: ");
//        String fNameInput = sc.nextLine();
//        for(Contact c : cList){
//            c.getFirstName().equals(fNameInput);
//        }
//    }
//
//    public static void addLastName(List<Contact> cList){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("please add the last name: ");
//        String lNameInput = sc.nextLine();
//        for(Contact c : cList){
//            c.getLastName().equals(lNameInput);
//        }
//    }
//
//    public static void addNumber(List<Contact> cList){
//        Scanner sc = new Scanner(System.in);
//        System.out.println("please add the phone number: ");
//        String pNumberInput = sc.nextLine();
//        for(Contact c : cList){
//            c.getNumber().equals(pNumberInput);
//        }
//    }

    public static void addContact(List<Contact> cList){
        Scanner sc = new Scanner(System.in);
        System.out.println("add first name: ");
        String fNameInput = sc.nextLine();
        System.out.println("add last name: ");
        String lNameInput = sc.nextLine();
        System.out.println("add the phone number: ");
        String pNumberInput = sc.nextLine();

            Contact c1 = new Contact(fNameInput, lNameInput, pNumberInput);
            cList.add(c1);

            System.out.println(fNameInput + " " + lNameInput + " " + pNumberInput );
            System.out.println("This is a new contact");




    }


    public static List<String> saveListAsString(List<Contact> cList){
        List<String> saveList = new ArrayList<>();
        for(Contact c : cList){
            saveList.add(c.getFirstName()+ "| " + c.getLastName()+ " | " + c.getNumber());
        }
        return saveList;
    }



    public static void removeContact(Path pathToOurFile, List<Contact> cList){
        Scanner sc = new Scanner(System.in);
        System.out.println("choose a person you want to remove: ");
        String userInput = sc.nextLine();
        for (Contact c : cList){
            if(c.getLastName().contains(userInput) || c.getFirstName().contains(userInput) ){
                cList.remove(c);
            } else{
                System.out.println("That name does not exists");
                removeContact(pathToOurFile,  cList);
            }
        }
    }


public static void main(String[] args) throws IOException {
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
        readFile(pathToOurFile);
        showMenu();
        userInput = sc.nextLine();
        switch(userInput){
            case "1":
                showContacts(pathToOurFile, contactList);
                break;
            case"2":
                addContact(contactList);
                break;
            case "3":
                searchContactsByName(contactList);
            case"4":
                removeContact(pathToOurFile, contactList);
                break;
            case"5":
                Files.write(pathToOurFile, saveListAsString(contactList), StandardOpenOption.APPEND);
                System.out.println("Farewell");
                return;

            default:
                System.out.println("Wrong input, please choose an option between 1 through 5.");
        }

    }while(!userInput.equalsIgnoreCase("5"));

//
    }
}
