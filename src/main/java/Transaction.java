import backend.accounts.*;
import backend.database.DatabaseController;
import backend.people.Client;
import org.iban4j.Iban;

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

                Account recAcc;
                for (Client c : Main.CLIENT_LIST){
                    for (Account a : c.getAccounts()){
                        if (a.getIBAN().equals(JIBAN.getText())){
                            recAcc = a;
                            break;
                        }
                    }
                }

                // get and validate the sum of transfer
                // then make the transfer
                Double amount;

                try {

                    amount = Double.parseDouble(JAmountInput.getText());
                    Account acc = client.getAccounts().get(0);
                    switch (acc.getTYPE()) {
                        case GIRO:

                            break;
                        case DEBIT:
                            if (true) {
                                client.getAccounts().get(0).setBalance(acc.getBalance() - amount);
                                acc.setBalance(acc.getBalance() + amount);
                                DatabaseController.saveTransaction(client, iban.toString(), amount);
                                System.out.println(amount);

                                break;
                            }
                        case CREDIT:
                            if (true) {
                                client.getAccounts().get(0).setBalance(acc.getBalance() - amount);
                                DatabaseController.saveTransaction(client, iban.toString(), amount);
                                System.out.println(amount);
                                break;
                            }
                            break;
                        case FIXED:

                            break;
                        default:
                            //System.out.println(String.format("Could not find Account type for %s", account_list.getString("IBAN")));
                    }

                } catch (NumberFormatException | SQLException nfe) {
                    // bad amount format
                    System.exit(1);
                }
            }
        });
    }

    public static void createTransaction(){
        Transaction test=new Transaction();
        test.setContentPane(test.JTransaction);
        test.setTitle("Test");
        test.setSize(500,400);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

