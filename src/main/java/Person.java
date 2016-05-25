import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Person {

    private String name;
    private String phoneNumber;
    private String email;

    public Person() {
    }

    public Person(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", phone number: " + this.phoneNumber
                + ", e-mail: " + this.email;
    }

    public static Person addNewPerson() {
        Person person = new Person();
        String buff = null;
        boolean flag = true;
        System.out.println("Введите, пожалуйста, данные контакта. Имя, телефонный номер, адрес электроной почты.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Имя: ");
            person.setName(reader.readLine());
            System.out.println("Телефонный номер: ");
            person.setPhoneNumber(reader.readLine());
            System.out.println("Адрес электронной почты: ");
            person.setEmail(reader.readLine());
        } catch (IOException e) {
            e.getMessage();
        }
        return person;
    }
}
