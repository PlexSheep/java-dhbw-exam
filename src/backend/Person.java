package backend;

import java.util.Date;

/**
 * Abstract base class for any backend.Person
 */
public abstract class Person {

    /**
     * name as per requirement
     */
    private String name;
    /**
     * birthday as per requirement
     */
    private Date birthday;
    /**
     * physical location as per requirement
     *
     * (this is not a MAC address)
     */
    private String address;
    /**
     * email as per requirement
     */
    private String email;
    /**
     * telephone number as per requirement
     */
    private String telephoneNumber;
    /**
     * the primary key of the account
     */
    private int id;
    /**
     * stores if the user is currently logged in
     */
    private boolean isLoggedIn = false;

    public Person(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;

        // finally, save to DB
        this.save();
    }

    /**
     * save the backend.Person to the database
     */
    public void save() {
        // TODO
    }

    /**
     * log the user in
     */
    public void login() {
        // TODO
    }

    /**
     * log the user out
     */
    public void logout() {
        // TODO
    }

    /**
     * check if the user is currently logged in
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
