import backend.people.Client;
import backend.people.Employee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeCredential extends JFrame {
    private JLabel JTitle1;
    private JLabel JAdress1;
    private JLabel JEmail1;
    private JLabel JNumber1;
    private JLabel JName;
    private JButton JChangeCredentials;
    private JTextField JNewName;
    private JTextField JNewAdress;
    private JTextField JNewEmail;
    private JTextField JNewNumber;
    public JPanel JCredential;
    private JPanel JMain;


    public ChangeCredential() {
        this.setContentPane(this.JCredential);
        this.setTitle("Change User Data");
        this.setSize(500, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JChangeCredentials.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Main.loggedIn instanceof Employee employee) {
                    System.out.printf("updating info of user %s%n", Main.loggedIn.getId());
                    employee.setName(JNewName.getText());
                    employee.setAddress(JNewAdress.getText());
                    employee.setEmail(JNewEmail.getText());
                    employee.setTelephoneNumber(JNewNumber.getText());
                    employee.save();
                } else if (Main.loggedIn instanceof Client client) {
                    System.out.printf("updating info of user %s%n", Main.loggedIn.getId());
                    client.setName(JNewName.getText());
                    client.setAddress(JNewAdress.getText());
                    client.setEmail(JNewEmail.getText());
                    client.setTelephoneNumber(JNewNumber.getText());
                    client.save();
                } else {
                    throw new UnsupportedOperationException();
                }
            }
        });

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
