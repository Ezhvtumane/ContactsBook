import com.thoughtworks.xstream.XStream;

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

    public String convertToXmlString() {
        XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        xstream.alias("ContactsBook", ContactsBook.class);
        xstream.addImplicitCollection(ContactsBook.class, "persons");

        String xml = "<ContactsBook>\n" +
                "  <person>\n" +
                "    <name>Name1</name>\n" +
                "    <phoneNumber>+1888999</phoneNumber>\n" +
                "    <email>111ru@ru.ru</email>\n" +
                "  </person>\n" +
                "  <person>\n" +
                "    <name>Name2</name>\n" +
                "    <phoneNumber>+2888999</phoneNumber>\n" +
                "    <email>22ru@ru.ru</email>\n" +
                "  </person>\n" +
                "  <person>\n" +
                "    <name>Name3</name>\n" +
                "    <phoneNumber>+3888999</phoneNumber>\n" +
                "    <email>3ru@ru.ru</email>\n" +
                "  </person>\n" +
                "</ContactsBook>";
        ContactsBook pList = (ContactsBook)xstream.fromXML(xml);

pList.showAllContacts();
        return xstream.toXML(this);
    }

   /* public ContactsBook convertFromXmlString() {
        XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        xstream.alias("ContactsBook", ContactsBook.class);
        ContactsBook cb = (ContactsBook)xstream.fromXML("<ContactsBook>\n" +
                "  <person>\n" +
                "    <name>Name1</name>\n" +
                "    <phoneNumber>+1888999</phoneNumber>\n" +
                "    <email>111ru@ru.ru</email>\n" +
                "  </person>\n" +
                "  <person>\n" +
                "    <name>Name2</name>\n" +
                "    <phoneNumber>+2888999</phoneNumber>\n" +
                "    <email>22ru@ru.ru</email>\n" +
                "  </person>\n" +
                "  <person>\n" +
                "    <name>Name3</name>\n" +
                "    <phoneNumber>+3888999</phoneNumber>\n" +
                "    <email>3ru@ru.ru</email>\n" +
                "  </person>\n" +
                "</ContactsBook>");
        return cb;
    }*/

    public static void main(String args[]) {

        Person prs1 = new Person("Name1", "+1888999", "111ru@ru.ru");
        Person prs2 = new Person("Name2", "+2888999", "22ru@ru.ru");
        Person prs3 = new Person("Name3", "+3888999", "3ru@ru.ru");

        ContactsBook cb = new ContactsBook();

        cb.addContact(prs1);
        cb.addContact(prs2);
        cb.addContact(prs3);

        System.out.println(cb.convertToXmlString());

        cb.showAllContacts();



        for (Person person : cb.findContactByName("Name1")) {
            System.out.println(person.toString());
        }

        cb.deleteContact(prs2);

        cb.showAllContacts();

        cb.deleteContact(prs1);

        cb.showAllContacts();

        cb.deleteContact(prs3);

       /* cb.convertFromXmlString();*/
        cb.showAllContacts();

    }

}
