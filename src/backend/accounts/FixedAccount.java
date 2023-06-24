package backend.accounts;

import backend.people.Client;

/**
 * _Festgeldkonto_ store only a given sum of money
 */
public class FixedAccount extends Account {
    public FixedAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.FIXED;

    }
}
