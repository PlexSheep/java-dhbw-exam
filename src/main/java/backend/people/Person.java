package backend.people;

import backend.database.DatabaseController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
 * Abstract base class for any backend.People.Person
 */
public abstract class Person {
    //private UUID uuid = UUID.randomUUID();
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
     * <p>
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
    int id;
    /**
     * stores if the user is currently logged in
     */
    private final boolean isLoggedIn = false;

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

        SecureRandom secRan = new SecureRandom();
        secRan.setSeed(System.currentTimeMillis() % 1000);

        this.id = secRan.nextInt(9999999, 99999999);

        // finally, save to DB
        //this.save();
    }

    public Person(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber,
            Integer id) {
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.id = id;

        // finally, save to DB
        //this.save();
    }

    /**
     * save the backend.People.Person to the backend.database
     */
    public void save(String table) throws SQLException {
        DatabaseController.updateUsers(this, table);
    }

    /**
     * log the user in
     */
    public void login(String password) throws NoSuchAlgorithmException {
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hashedPassword = md.digest(password.getBytes(UTF_8));
            System.out.println(Arrays.toString(hashedPassword));
        } catch (Exception e) {

        }
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

    public int getId() {
        return id;
    }

    //public boolean transferMoney(int recipientID, double amount){}
    public abstract void save();

    /*
    public UUID getUuid() {
        return uuid;
    }

     */
}
