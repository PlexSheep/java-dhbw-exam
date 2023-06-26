import javax.swing.*;

public class ChangeCredential extends JFrame {
    private JLabel JTitle1;
    private JLabel JAdress1;
    private JLabel JEmail1;
    private JLabel JNumber1;
    private JLabel JNAme1;
    private JButton JChangeCredentials;
    private JTextField JNewName;
    private JTextField JNewAdress;
    private JTextField JNewEmail;
    private JTextField JNewNumber;
    private JTextField JNewIBAN;
    private JTextField JNewBIC;
    public JPanel JCredential;
    private JPanel JMain;
    private JLabel JIBAN1;
    private JLabel JBIC1;


    public static void ChangeCredential() {
        ChangeCredential test = new ChangeCredential();
        test.setContentPane(test.JCredential);
        test.setTitle("Sus");
        test.setSize(500, 400);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
