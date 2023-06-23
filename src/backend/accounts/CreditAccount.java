package backend.accounts;

import backend.Client;

/**
 * account for a credit card
 */
public class CreditAccount extends Account {
    public CreditAccount(Client owner) {
        super(owner);
    }
}