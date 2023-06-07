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
  public static void main(String[] args){
    JTextField username = new JTextField();
    JTextField password = new JPasswordField();
    Object[] message = {"Username:", username,"Password:", password};
    ImageIcon bankIcon = null;
    URL imgURL = Main.class.getResource("amogus.png");
    if (imgURL != null) {
      bankIcon = new ImageIcon((new ImageIcon(imgURL)).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH));
    }
    int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, bankIcon);
    if (option != JOptionPane.OK_OPTION) {
      System.exit(0);
    } else {
      if (username.getText().equals("test") && password.getText().equals("test")) {//check credentials here
        System.out.println("Login successful");
      } else {
        System.out.println("login failed");
        //maybe repeat here
      }
    }    
  }
}
