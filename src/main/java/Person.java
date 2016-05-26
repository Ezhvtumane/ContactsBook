import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return "Имя: " + this.name + ", телефонный номер: " + this.phoneNumber
                + ", e-mail: " + this.email;
    }

    public static Person addNewPerson() {
        Pattern patternEmail = Pattern.compile("^.+@.{1,30}$");
        Pattern patternPhoneNumber = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{5,15}$");
        Pattern patternName = Pattern.compile("^.{1,30}.$");

        Person person = new Person();
        String buff = "";
        //boolean flag = true;
        System.out.println("Введите, пожалуйста, данные контакта. Имя, телефонный номер, адрес электроной почты.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Имя: ");
            buff = reader.readLine();
            Matcher matcherName = patternName.matcher(buff);
            while (!matcherName.find()) {
                System.out.println("Введите от 1 до 30 любых символов с клавиатуры.");
                buff = reader.readLine();
                matcherName = patternName.matcher(buff);
            }
            person.setName(buff);

            System.out.print("Телефонный номер: ");
            buff = reader.readLine();
            Matcher matcherPhoneNumber = patternPhoneNumber.matcher(buff);
            while (!matcherPhoneNumber.find()) {
                System.out.println("Введите от 5 до 15 символов с клавиатуры. Допускаются: цифры,пробелы, (, ), -, + в начале номера.");
                buff = reader.readLine();
                matcherPhoneNumber = patternPhoneNumber.matcher(buff);
            }
            person.setPhoneNumber(buff);

            System.out.print("Адрес электронной почты: ");
            buff = reader.readLine();
            Matcher matcherEmail = patternEmail.matcher(buff);
            while (!matcherEmail.find()) {
                System.out.println("Введите от 1 до 30 символов. Символ @ обязателен.");
                buff = reader.readLine();
                matcherEmail = patternEmail.matcher(buff);
            }
            person.setEmail(buff);

        } catch (IOException e) {
            e.getMessage();
        }
        return person;
    }
}
