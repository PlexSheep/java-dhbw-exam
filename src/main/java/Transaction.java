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
    private JLabel JBICLabel;
    private JTextField JBIC;


    public Transaction() {
        JTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //try {
                ////    currently bugged
                ////    if ((Client) Main.loggedIn.getA)
                ////    DatabaseController.saveTransaction((Client) Main.loggedIn, JIBAN.getText(), Double.parseDouble(JAmountInput.getText()));
                //} catch (SQLException e) {
                //    throw new RuntimeException(e);
                //}
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

