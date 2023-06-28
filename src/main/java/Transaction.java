import backend.accounts.Account;
import backend.database.DatabaseController;
import backend.people.Client;
import org.iban4j.Iban;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Transaction extends JFrame {
    public JPanel JTransaction;
    private JLabel JAmountLabel;
    private JTextField JAmountInput;
    private JButton JTransactionButton;
    private JLabel JTitle;
    private JLabel JRecipientLabel;
    private JTextField JIBAN;
    private JTextField JstatusField;


    /**
     * Function to transfer funds from one account to another one
     */
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
                    System.out.printf("Bad Iban: %s%n", JIBAN.getText());
                    JstatusField.setText("Bad Iban:" + JIBAN.getText());
                }

                /*
                * Get the recipients account
                * */
                Account recAcc = null;
                for (Client c : Main.CLIENT_LIST) {
                    for (Account a : c.getAccounts()) {
                        if (a.getIBAN().equals(JIBAN.getText())) {
                            recAcc = a;
                            break;
                        }
                    }
                }

                assert recAcc != null;
                //System.out.println(recAcc);

                // get and validate the sum of transfer
                // then make the transfer
                Double amount;

                try {
                    amount = Double.parseDouble(JAmountInput.getText());
                    if (UserData.selecedAcc == null) {
                        JstatusField.setText("No account selected");
                    }
                    else if(!UserData.selecedAcc.setBalance(UserData.selecedAcc.getBalance() - amount)){
                        JstatusField.setText("Failed");
                    }
                    else {
                        recAcc.setBalance(recAcc.getBalance() + amount);
                        DatabaseController.saveTransaction(client, iban.toString(), amount);
                        System.out.println(amount);
                        JstatusField.setText("Success");
                    }
                    } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void createTransaction() {
        Transaction test = new Transaction();
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        test.setContentPane(test.JTransaction);
        test.setTitle("Transaktion");
        test.setSize(500, 400);
        test.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

