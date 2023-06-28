import backend.accounts.*;
import backend.database.DatabaseController;
import backend.people.Client;
import org.iban4j.Iban;

import backend.database.DatabaseController;
import backend.people.Client;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Transaction extends JFrame{
    public JPanel JTransaction;

    private JLabel JAmountLabel;
    private JTextField JAmountInput;
    private JButton JTransactionButton;
    private JLabel JTitle;
    private JLabel JRecipientLabel;
    private JTextField JIBAN;


    public Transaction() {
        JTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // TODO
                if (!(Main.loggedIn instanceof Client)) {
                    // this is not supposed to happen
                    System.exit(1);
                }
                Client client = (Client) Main.loggedIn;

                // get and validate the iban
                Iban iban = null;
                try {
                    iban = Iban.valueOf(JIBAN.getText());
                } catch (Exception e) {
                    System.out.println(String.format("Bad Iban: %s", JIBAN.getText()));
                    System.exit(1);
                }

                // get and validate the sum of transfer
                // then make the transfer
                Double amount;

                /*
                try {

                    amount = Double.parseDouble(JAmountInput.getText());
                    AccountType type;
                    Account acc = client.getAccounts().get(0);
                    switch (acc.getTYPE()) {
                        case AccountType.GIRO:

                            break;
                        case AccountType.DEBIT:
                            acc = (DebitAccount) acc;
                            if ((acc.getDebtLimit() + amount) < acc.getDebtLimit()) {
                                DatabaseController.changeBalance(client.getId(), client.getId() - amount);
                                DatabaseController.saveTransaction(client, iban.toString(), amount);
                                break;
                            }
                        case AccountType.CREDIT:
                            acc = (CreditAccount) acc;
                            if ((acc.getDebtLimit() + amount) < acc.getDebtLimit()) {
                                DatabaseController.changeBalance(client.getId(), client.getId() - amount);
                                DatabaseController.saveTransaction(client, iban.toString(), amount);
                                break;
                            }
                            break;
                        case AccountType.FIXED:

                            break;
                        default:
                            System.out.println(String.format("Could not find Account type for %s", account_list.getString("IBAN")));
                    }

                } catch (NumberFormatException nfe) {
                    // bad amount format
                    System.exit(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

                     */
            }
        });
    }

    public static void createTransaction(){
        Transaction test=new Transaction();
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        test.setContentPane(test.JTransaction);
        test.setTitle("Test");
        test.setSize(500,200);
        test.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

