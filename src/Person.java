/**
 * Created by Amadeusz on 21.04.2016.
 */
public class Person {

    private String firstName;
    private String lastName;
    private String city;
    private String street;
    private int number;

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {return lastName;}
    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public int getNumber() {return number;}
    public void setNumber(int number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
}
