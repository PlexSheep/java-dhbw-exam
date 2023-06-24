import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserData extends JFrame {
    private JLabel JAdress;
    private JLabel JTitle;
    private JLabel JEmail;
    private JLabel JNumber;
    private JPanel JNamePanel;
    private JPanel JEmailPanel;
    private JPanel JNumberPanel;
    private JLabel JNAme;
    private JPanel JAdressPanel;
    private JLabel profilePicturePanel;
    private JPanel JIban;
    private JPanel JBic;
    private JLabel JNameVariable;
    private JLabel JAdressVariable;
    private JLabel JEmailVar;
    private JLabel JNumverVar;
    private JLabel JIbanVar;
    private JLabel JBicVar;
    private JButton JChangePassword;
    private JButton JChangeCredentials;
    private JButton JDeleteAccount;
    private JButton JLogout;
    public JPanel JMain;
    private JTextField JNewPassword;


    public UserData() {
        JChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void createUser(){
        UserData test=new UserData();
        test.setContentPane(test.JMain);
        test.setTitle("Test");
        test.setSize(500,400);
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
}

