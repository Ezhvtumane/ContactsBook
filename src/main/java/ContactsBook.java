import java.util.ArrayList;

/**
 * Created by ubuntu on 21.05.16.
 */
public class ContactsBook {

    private ArrayList<Person> persons;

    public ContactsBook() {
        this.persons = new ArrayList<Person>();
    }

    public void addContact(Person person) {
        this.persons.add(person);
    }

    public void deleteContact(Person person) {
        this.persons.remove(person);
    }

    public Person getContact(Person person){
        //Iterator need
    }

}
