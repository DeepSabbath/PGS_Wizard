/**
 * Created by Amadeusz on 21.04.2016.
 */
public class Person {

    private static String firstName;
    private static String lastName;
    private static String city;
    private static String street;
    private static int number;

    public static String getFirstName() {return firstName;}
    public static void setFirstName(String firstname) {
        Person.firstName = firstname;
    }

    public static String getLastName() {return lastName;}
    public static void setLastName(String lastname) {
        Person.lastName = lastname;
    }

    public static String getCity() {
        return city;
    }
    public static void setCity(String city) {
        Person.city = city;
    }

    public static int getNumber() {return number;}
    public static void setNumber(int number) {
        Person.number = number;
    }

    public static String getStreet() {
        return street;
    }
    public static void setStreet(String street) {
        Person.street = street;
    }
}
