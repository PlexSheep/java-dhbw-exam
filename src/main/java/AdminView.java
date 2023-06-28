import backend.people.Client;

import javax.swing.*;


public class AdminView extends JFrame {

    private JList JClientListt;
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

///