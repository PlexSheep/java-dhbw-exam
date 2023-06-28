package backend.accounts;

import backend.people.Client;

public class GiroAccount extends Account {
    public GiroAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.GIRO;
    }

    public GiroAccount(Client owner, String iban, int balance, int debtLimit) {
        super(owner);
        this.TYPE = AccountType.CREDIT;
    }

}
