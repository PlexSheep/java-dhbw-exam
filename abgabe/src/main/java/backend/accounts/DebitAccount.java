package backend.accounts;

import backend.people.Client;

public class DebitAccount extends Account {
    public DebitAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.DEBIT;
    }

    public DebitAccount(Client owner, String iban, int balance, int debtLimit) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }
}
