import javax.swing.*;

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

