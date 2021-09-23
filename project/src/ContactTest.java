public class ContactTest {


    public static void main(String[] args) {
        Contact contact = new Contact("Norm Macdolald", "867-5309" );
        Contact spade = new Contact("David Spade", "588-2300");
        System.out.println(contact.getName());
        System.out.println(contact.getNumber());
    }
}
