package backend.accounts;

/**
 * Enumerate all possible account types
 * <p>
 * mainly used in Client.createAccount()
 */
public enum AccountType {
    GIRO,
    DEBIT,
    CREDIT,
    FIXED
}
