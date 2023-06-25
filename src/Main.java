import backend.people.Client;
import backend.Utils.Authentication;
import backend.database.DatabaseController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.*;
//import javax.swing.border.*;
import java.awt.*;
//import java.awt.event.*;
//import java.io.File;
//import java.awt.Container;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.Component;
//import java.util.ArrayList;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws SQLException, NoSuchAlgorithmException {
        Authentication auth = new Authentication();
        DatabaseController.connect();
        Client herbert = new Client("Herbert", new Date(1), "Here", "s", "e");
        //herbert.login("FFF");

        //DatabaseController.saveUsers(herbert, "test", "Employee");
        System.out.println(DatabaseController.readUsers("client").getString("Name"));

        JTextField username = new JTextField();
        JTextField password = new JPasswordField();
        Object[] message = {"Username:", username, "Password:", password};
        ImageIcon bankIcon = null;
        URL imgURL = Main.class.getResource("amogus.png");
        if (imgURL != null) {
            bankIcon = new ImageIcon((new ImageIcon(imgURL)).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
        }
        int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, bankIcon);
        if (option != JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            if (auth.password_authentication(username.getText(), password.getText(), "Employee")) {//check credentials here
                System.out.println("Login successful");
                Gui.createGUI();

            } else {
                System.out.println("login failed");
                //maybe repeat here
            }
        }

    }
}
