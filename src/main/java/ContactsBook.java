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

    public ArrayList<Person> findContactByName(String name) {
        ArrayList<Person> arrayOfFoundedContacts = new ArrayList<Person>();
        for (Person person : this.persons) {
            if (name.equals(person.getName())) {
                arrayOfFoundedContacts.add(person);
            }
            //System.out.println(person.toString() + "\n");
        }
        return arrayOfFoundedContacts;
    }

    public void showAllContacts() {
        for (Person person : this.persons) {
            System.out.println(person.toString() + "\n");
        }
    }

    public void loadToXmlFile() {

    }

    public static void main(String args[]) {

        Person prs1 = new Person("Name1", "+1888999", "111ru@ru.ru");
        Person prs2 = new Person("Name2", "+2888999", "22ru@ru.ru");
        Person prs3 = new Person("Name3", "+3888999", "3ru@ru.ru");

        ContactsBook cb = new ContactsBook();

        cb.addContact(prs1);
        cb.addContact(prs2);
        cb.addContact(prs3);

        cb.showAllContacts();

        for (Person person : cb.findContactByName("Name1")) {
            System.out.println(person.toString());
        }

        cb.deleteContact(prs2);

        cb.showAllContacts();

        cb.deleteContact(prs1);

        cb.showAllContacts();

        cb.deleteContact(prs3);

    }

}
