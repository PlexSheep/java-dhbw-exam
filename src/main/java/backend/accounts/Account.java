package backend.accounts;


import backend.database.DatabaseController;
import backend.people.Client;
import org.iban4j.CountryCode;
import org.iban4j.Iban;

import java.sql.SQLException;

/**
 *
 */
public abstract class Account {


    /**
     * specifies the owner of the account
     */
    private final Client owner;
    /**
     * whether the account is currently active.
     * <p>
     * New accounts have to be reviewed by an backend.People.Employee, only then will they be activated.
     * Accounts can be deactivated for various reasons.
     */
    private boolean active = false;
    /**
     * balance of the account
     * <p>
     * can be negative
     */
    private double balance = 0;
    /**
     * lower limit for the balance of the account
     */
    private double debtLimit = 0;
    /**
     * Unique identifier for any account, used as primary key in the database
     */
    public String accountNumber;

    /**
     * resulting from COUNTRY_CODE, BANK_CODE and accountNumber,
     * we generate an IBAN
     */
    public Iban iban;

    AccountType TYPE;

    /**
     * Create a new account
     *
     * @param owner
     */
    public Account(Client owner) {
        this.owner = owner;
        this.iban = Iban.random(CountryCode.DE);
        this.accountNumber = this.iban.getAccountNumber();
    }

    /**
     * Add a new account with existing data
     *
     * @param owner
     * @param iban
     * @param balance
     * @param debtLimit
     */
    public Account(Client owner, String iban, Double balance, Double debtLimit) {
        this.owner = owner;
        this.iban = Iban.valueOf(iban);
        this.accountNumber = this.iban.getAccountNumber();
        this.balance = balance;
        this.debtLimit = -debtLimit;
    }

    /**
     * save the account to the backend.database
     */
    public void save() {
        try {
            DatabaseController.updateAccount(this);
            System.out.println("Saving account" + this.getIBAN());
        } catch (SQLException e) {
            System.out.printf("could not save account %s%n", this.getIBAN());
        }
    }

    public Client getOwner() {
        return owner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) throws SQLException {
        this.active = active;
        this.save();
    }

    /**
     * @return current balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /**
     * overwrite the current balance
     *
     * @param balance new balance
     */
    public boolean setBalance(double balance) throws SQLException {
        this.balance = balance;
        this.save();
        return true;
    }

    /**
     * modify the current balance of an account by a specified amount.
     * <p>
     * TODO do some checks to see if the supposed changes are valid.
     *
     * @param amount the amount to modify the current balance by,
     *               can be negative.
     * @return the new balance of the account
     */
    public double modBalance(int amount) throws SQLException {
        this.balance += amount;
        this.save();
        return this.balance;
    }

    /**
     * @return get the current debt limit
     */
    public Double getDebtLimit() {
        return debtLimit;
    }

    /**
     * set the lower limit of the accounts balance.
     *
     * @param debtLimit new limit, must be positive
     */
    public void setDebtLimit(int debtLimit) {
        this.debtLimit = debtLimit;
    }

    /**
     * Get the account types
     *
     * @return Enum AccountType constant
     */
    public AccountType getTYPE() {
        return TYPE;
    }

    /**
     * Get the account IBAN
     *
     * @return String IBAN
     */
    public String getIBAN() {
        return iban.toString();
    }


}
