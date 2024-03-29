package backend.accounts;

import backend.people.Client;

/**
 * account for a credit card
 */
public class CreditAccount extends Account {

    private double dueLimit;

    public CreditAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }

    public CreditAccount(Client owner, String iban, int balance, int debtLimit) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }
    public CreditAccount(Client owner, Double dueLimit) {
        super(owner);
        this.dueLimit = dueLimit;
        this.TYPE = AccountType.CREDIT;

    }

    public double getDueLimit() {
        return dueLimit;
    }

    public void setDueLimit(double dueLimit) {
        this.dueLimit = dueLimit;
    }
}