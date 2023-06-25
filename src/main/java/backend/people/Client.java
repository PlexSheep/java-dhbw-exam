package backend.people;

import backend.accounts.*;

import java.util.Date;
import java.util.List;

/**
 * Represent a client of the bank
 */
public class Client extends Person {

    /**
     * stores the accounts of a backend.Client
     */
    public List<Account> accounts;

    /**
     * Create a new Client
     *
     * @param name
     * @param birthday
     * @param address
     * @param email
     * @param telephoneNumber
     */
    public Client(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber)
    {
        super(name, birthday, address, email, telephoneNumber);
    }

    public Client(
            String name,
            Date birthday,
            String address,
            String email,
            String telephoneNumber,
            Integer id)
    {
        super(name, birthday, address, email, telephoneNumber, id);
    }

    /**
     * create a new account of type T for the user
     *
     * @param type any Class that extends Account (AccountType is an enum)
     * @return a newly created account of type T that has not been reviewed.
     */
    public Account createAccount(AccountType type) throws UnsupportedOperationException {
        Account a = null;
        switch (type) {
            case GIRO -> {
                a = new GiroAccount(this);
            }
            case DEBIT -> {
                a = new DebitAccount(this);
            }
            case CREDIT -> {
                a = new CreditAccount(this);
            }
            default -> {
                throw new UnsupportedOperationException();
            }
        }
        return a;
    }

    public void deleteAccount(String iban) {

    }

    /*
    public boolean transferMoney(int recipientID, double amount){
    }

     */
}
