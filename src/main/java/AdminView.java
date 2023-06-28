import backend.accounts.Account;
import backend.accounts.AccountType;
import backend.database.DatabaseController;
import backend.people.Client;
import backend.utils.Authentication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminView extends JFrame {

    private JList JClientList;
    private JPanel JMain;

    public AdminView(int userid) {
        for (Client c : Main.CLIENT_LIST) {
            if (c.getId() == userid) {

            }
        }
    }

    public static JFrame createAdminView(int userid) {
        AdminView adview = new AdminView(userid);
        adview.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adview.setTitle("Kontoübersicht");
        adview.setSize(500, 200);
        adview.setVisible(true);
        return adview;
    }
}

