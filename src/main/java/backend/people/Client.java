package backend.people;

import backend.accounts.*;
import backend.database.DatabaseController;

import java.util.ArrayList;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

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
        this.accounts.add(a);
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
        for (int i = 0; i < this.accounts.size(); i++) {
            if (Objects.equals(this.accounts.get(i).iban.toString(), iban)) {
                this.accounts.remove(i);
            }
        }
    }

    public ArrayList<Account> getAccounts() {
        System.out.println(accounts.size());
        return accounts;
    }

    public boolean addAccount(Account a){
        accounts.add(a);
        return true;
    }


    //public boolean transferMoney(int recipientID, double amount){}

    @Override
    public void save(String table) {
        try {
            System.out.println("Saving inner clients");
            DatabaseController.updateUsers(this, table);
            for (Account a : accounts){
                a.save();
            }
        } catch (SQLException e) {
            System.out.println(String.format("could not save user %s", this.getName()));
        }
    }
}
