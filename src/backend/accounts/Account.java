package backend.accounts;


import backend.people.Client;
import backend.database.DatabaseController;
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
     *
     * New accounts have to be reviewed by an backend.People.Employee, only then will they be activated.
     * Accounts can be deactivated for various reasons.
     */
    private boolean active = false;
    /**
     * balance of the account
     *
     * can be negative
     */
    private int balance = 0;
    /**
     * lower limit for the balance of the account
     */
    private int debtLimit = 0;
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

    public Account(Client owner) {
        this.owner = owner;

        /*
        Iban iban = new Iban.Builder()
            // this line is bugged vvvvvv
            .countryCode(CountryCode.DE)
            // ^^^^^^^^^^^^^^^^^^^
            // iban4j checks for wrong padding with some codes, like DE.
            // see https://github.com/arturmkrtchyan/iban4j/issues/33
            .bankCode("19043")
            .buildRandom();
         */

        this.iban = Iban.random(CountryCode.DE);
        this.accountNumber = this.iban.getAccountNumber();

        // finally, save to DB
        this.save();
    }

    /**
     * save the account to the backend.database
     */
    public void save() {
        // TODO
    }

    public Client getOwner() {
        return owner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        this.save();
    }

    /**
     * @return current balance of the account
     */
    public int getBalance() {
        return balance;
    }

    /**
     * overwrite the current balance
     *
     * @param balance new balance
     */
    public void setBalance(int balance) throws SQLException {
        DatabaseController.changeBalance(this.iban.toString(), balance);
        this.balance = balance;
    }

    /**
     * modify the current balance of an account by a specified amount.
     *
     * TODO do some checks to see if the supposed changes are valid.
     *
     * @param amount the amount to modify the current balance by,
     *               can be negative.
     * @return the new balance of the account
     */
    public void modBalance(int amount) throws SQLException {
        int newBalance = this.balance + amount;
        this.setBalance(newBalance);
    }

    /**
     * @return get the current debt limit
     */
    public int getDebtLimit() {
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
