import com.thoughtworks.xstream.XStream;

import java.awt.peer.SystemTrayPeer;
import java.io.*;
import java.nio.channels.Pipe;
import java.util.ArrayList;

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

        return xstream.toXML(this);
    }

    public void convertFromXmlString() {
        XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        xstream.alias("ContactsBook", ContactsBook.class);
        xstream.addImplicitCollection(ContactsBook.class, "persons");

        ContactsBook cb = (ContactsBook) xstream.fromXML(this.openXmlStringFromFile());
        for (Person person : cb.persons) {
            this.persons.add(person);
        }
    }

    public void saveXmlStringToFile() {
        try {
            FileWriter fileWriter = new FileWriter("ContactsBook.xml", false);

            try {
                fileWriter.write(this.convertToXmlString());
                fileWriter.flush();
            } finally {
                fileWriter.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String openXmlStringFromFile() {
        String s = null;

        try {
            FileReader reader = new FileReader("ContactsBook.xml");
            int c;
            while ((c = reader.read()) != -1) {
                s += (char) c;
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    public static void main(String args[]) {

        boolean isExit = false;
        System.out.println("Приложение Записная книжка.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation = null;
        String buff = null;

        ContactsBook contactsBook = new ContactsBook();
        contactsBook.openXmlStringFromFile();

        while (!isExit) {
            System.out.println("Введите код операции:\n" +
                    "1 - Добавить новый контакт;\n" +
                    "2 - Показать все записанные контакты;\n" +
                    "3 - Найти контакт по имени;\n" +
                    "4 - Удалить контакт\n");
            try {
                operation = reader.readLine();
            } catch (IOException e) {
                e.getMessage();
            }

            while (!operation.equals("1") & !operation.equals("2")
                    & !operation.equals("3") & !operation.equals("4")
                    & !operation.equals("exit")) {
                System.out.println("Введите код операции, попробуйте еще раз:");
                try {
                    operation = reader.readLine();        // если ввели некорректное значение - переспрашиваем
                } catch (IOException e) {
                    e.getMessage();
                }
            }
            if (operation.equals("exit")) {
                System.out.println("Всего доброго! Ждем Вас снова!");
                System.exit(-1);
            }

            switch (Integer.parseInt(operation)) {
                case 1: {
                    contactsBook.addContact(Person.addNewPerson());
                }
                break;
                case 2: {
                    contactsBook.showAllContacts();
                }
                break;
                case 3: {
                    try {
                        System.out.println("Введите имя для поиска: ");
                        buff = reader.readLine();
                        System.out.println("Найдены следующие контакты:");
                        for (Person person : contactsBook.findContactByName(buff)) {
                            System.out.println(person.toString() + "\n");
                        }

                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
                break;
                case 4: {
                    try {
                        System.out.println("Введите имя контакта для удаления: ");
                        buff = reader.readLine();
                        for (Person person : contactsBook.findContactByName(buff)) {
                            System.out.println("Будет удален следующий контакт: " + person.toString() + "\n");
                            contactsBook.deleteContact(person);
                        }
                    } catch (IOException e) {
                        e.getMessage();
                    }
                }
                break;
            }
        }

    }

}
