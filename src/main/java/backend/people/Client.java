package backend.people;

import backend.accounts.*;
import backend.database.DatabaseController;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Date;

/**
 * Represent a client of the bank
 */
public class Client extends Person {

    /**
     * stores the accounts of a backend.Client
     */
    public ArrayList<Account> accounts = new ArrayList<>();

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

    public Account loadAccount(AccountType type, String iban, int balance, int debtLimit) {
        Account a = null;
        switch (type) {
            case GIRO -> {
                a = new GiroAccount(this, iban, balance, debtLimit);
            }
            case DEBIT -> {
                a = new DebitAccount(this, iban, balance, debtLimit);
            }
            case CREDIT -> {
                a = new CreditAccount(this, iban, balance, debtLimit);
            }
            case FIXED -> {
                a = new FixedAccount(this, iban, balance, debtLimit);
            }
            default -> {
                throw new UnsupportedOperationException();
            }
        }
        return a;
    }

    public void deleteAccount(String iban) {

    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public boolean addAccount(Account a){
        accounts.add(a);
        return true;
    }


    //public boolean transferMoney(int recipientID, double amount){}
    @Override
    public void save() {
        try {
            DatabaseController.updateUsers(this, DatabaseController.TABLE_CLIENTS);
            for (Account a : accounts){
                a.save();
            }
        } catch (SQLException e) {
            System.out.println(String.format("could not save user %s", this.getName()));
        }
    }
}
