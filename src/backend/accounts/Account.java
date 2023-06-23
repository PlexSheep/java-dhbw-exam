package backend.accounts;

import backend.People.Client;

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
     * primary key of backend.accounts.Account
     */
    private int id;

    public Account(Client owner) {
        this.owner = owner;

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
    public void setBalance(int balance) {
        this.balance = balance;
        this.save();
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
    public int modBalance(int amount) {
        this.balance += amount;
        this.save();
        return this.balance;
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
}
