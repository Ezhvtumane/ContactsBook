import com.thoughtworks.xstream.XStream;

import java.io.*;
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
        }
        return arrayOfFoundedContacts;
    }

    public void showAllContacts() {
        int i = 0;
        for (Person person : this.persons) {
            i++;
            System.out.println(i + ") " + person.toString() + "\n");
        }
    }

    public String convertFromContactsBookToXmlString() {
        XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        xstream.alias("ContactsBook", ContactsBook.class);
        xstream.addImplicitCollection(ContactsBook.class, "persons");

        return xstream.toXML(this);
    }

    public void convertFromXmlStringToContactsBook(String s) {
        XStream xstream = new XStream();
        xstream.alias("person", Person.class);
        xstream.alias("ContactsBook", ContactsBook.class);
        xstream.addImplicitCollection(ContactsBook.class, "persons");
        try {
            ContactsBook cb = (ContactsBook) xstream.fromXML(s);
            for (Person person : cb.persons) {
                this.persons.add(person);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void saveXmlStringToFile() {
        try {
            FileWriter fileWriter = new FileWriter("ContactsBook.xml");

            try {
                if (!this.persons.isEmpty())
                    fileWriter.write(this.convertFromContactsBookToXmlString());
                fileWriter.close();
            } finally {
                fileWriter.close();
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String openXmlStringFromFile() {

        String s = "";

        try {
            FileReader reader = new FileReader("ContactsBook.xml");
            int c;
            while ((c = reader.read()) != -1) {
                s = s + (char) c;
            }
            reader.close();
        } catch (IOException ex) {
            System.out.println("\n" + ex.getMessage());
            System.out.println("Файл ContactsBook.xml не найден. Создаю новый." + "\n");
        }
        return s;
    }

    public static void main(String args[]) {

        //boolean isExit = false;
        System.out.println("Приложение Записная книжка.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operation = "";
        String buff = "";

        ContactsBook contactsBook = new ContactsBook();
        contactsBook.convertFromXmlStringToContactsBook(contactsBook.openXmlStringFromFile());

        while (true) {
            System.out.println("Введите код операции:\n" +
                    "1 - Добавить новый контакт;\n" +
                    "2 - Показать все записанные контакты;\n" +
                    "3 - Найти контакт по имени;\n" +
                    "4 - Удалить контакт;\n" +
                    "exit - Выйти из программы с сохранением текущей записной книжки.");
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
                contactsBook.saveXmlStringToFile();
                System.exit(-1);
            }

            switch (Integer.parseInt(operation)) {
                case 1: {
                    Person p = Person.addNewPerson();
                    contactsBook.addContact(p);
                    System.out.println("\n" + "Добавлен контакт: " + p.toString() + "\n");
                }
                break;
                case 2: {
                    System.out.println("\n" + "Контакты: ");
                    contactsBook.showAllContacts();
                }
                break;
                case 3: {
                    try {
                        System.out.print("Введите имя для поиска: ");
                        buff = reader.readLine();
                        System.out.println("\n" + "Найдены следующие контакты:");
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
                            System.out.println("Вы уверены, что хотите удалить контакт? y/n");
                            buff = reader.readLine();
                            if (buff.equals("y")) {
                                contactsBook.deleteContact(person);
                                System.out.println("Выполнено удаление!");
                            } else if (buff.equals("n"))
                                System.out.println("Отмена удаления!");
                            else {
                                System.out.println("Некорректный ввод. Операция отменена.");
                                break;
                            }
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
