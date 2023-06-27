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
                }
                catch (Exception e) {
                    System.out.println(String.format("Bad Iban: %s", JIBAN.getText()));
                    System.exit(1);
                }

                // get and validate the sum of transfer
                // then make the transfer
                Double amount;
                try {
                    //amount = Double.parseDouble(JAmountInput.getText());
                    //
                    //if ()
                    //DatabaseController.changeBalance(account.getId(), client.getId() - amount);
                    //DatabaseController.saveTransaction(client, iban.toString(), amount);
                }
                catch (NumberFormatException nfe) {
                    // bad amount format
                    System.exit(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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

