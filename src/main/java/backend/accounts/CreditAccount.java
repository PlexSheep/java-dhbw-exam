package backend.accounts;

import backend.people.Client;

import java.sql.SQLException;

/**
 * account for a credit card
 */
public class CreditAccount extends Account {

    private double dueLimit;

    public CreditAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }

    public CreditAccount(Client owner, String iban, Double balance, Double debtLimit) {
        super(owner, iban, balance, debtLimit);
        this.TYPE = AccountType.CREDIT;
    }

    public CreditAccount(Client owner, Double dueLimit) {
        super(owner);
        this.dueLimit = dueLimit;
        this.TYPE = AccountType.CREDIT;

    }

    /**
     * Set the balance of the account without going under the debt limit
     * Cant go over 0 because it is a credit account. It is either 0 or negative.
     * @param balance new balance
     * @return
     * @throws SQLException
     */
    @Override
    public boolean setBalance(double balance) throws SQLException {
        if (balance < getDebtLimit()){
            return false;
        }
        else if (balance + getBalance() > 0) {
            return false;

        }
        else {
            super.setBalance(balance);
            return true;
        }
    }

    public double getDueLimit() {
        return dueLimit;
    }

    public void setDueLimit(double dueLimit) {
        this.dueLimit = dueLimit;
    }
}