import java.util.Date;
import java.util.List;

/**
 * Represent a client of the bank
 */
public class Client extends Person {

    /**
     * stores the accounts of a Client
     */
    public List<Account> accounts;
    public Client(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber)
    {
        super(name, birthday, address, email, telephoneNumber);
    }

    /**
     * create a new account of type T for the user
     *
     * @param <T> any Class that extends Account
     * @return a newly created account that has not been reviewed.
     */
    public <T extends Account> Account createAccount() {
        // create a new object of type T
        // T a = new T(this); does not work. FIXME
        //T a = new T(this);
        //return a;
        return null;
    }
}
