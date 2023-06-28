package backend.accounts;

import backend.people.Client;

import java.sql.SQLException;

public class GiroAccount extends Account {
    public GiroAccount(Client owner) {
        super(owner);
        this.TYPE = AccountType.GIRO;
    }

    public GiroAccount(Client owner, String iban, Double balance, Double debtLimit) {
        super(owner, iban, balance, debtLimit);
        this.TYPE = AccountType.GIRO;
    }

    @Override
    public boolean setBalance(double balance) throws SQLException {
        if (!(balance < getDebtLimit())){
            return super.setBalance(balance);
        }
        return false;
    }
}
