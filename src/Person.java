/**
 * Created by Amadeusz on 21.04.2016.
 */
public class Person {

    private static String firstName = "";
    private static String lastName = "";
    private static String address = "";
    private static int number;

    public static String getFirstName() {return firstName;};
    public static void setFirstName(String firstname) {
        Person.firstName = firstname;
    }

    public static String getLastName() {return lastName;};

    public static void setLastName(String lastname) {
        Person.lastName = lastname;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String adres) {
        Person.address = adres;
    }

    public static int getNumber() {return number;}

    public static void setNumber(int number) {
        Person.number = number;
    }
}
