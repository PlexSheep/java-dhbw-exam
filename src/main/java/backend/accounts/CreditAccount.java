package backend.accounts;

import backend.people.Client;

/**
 * account for a credit card
 */
public class CreditAccount extends Account {
    public CreditAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }

    public CreditAccount(Client owner, String iban, int balance, int debtLimit) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }
}