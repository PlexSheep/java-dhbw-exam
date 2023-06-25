package backend.accounts;

import backend.people.Client;
public class GiroAccount extends Account{
    public GiroAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.GIRO;
    }

}
