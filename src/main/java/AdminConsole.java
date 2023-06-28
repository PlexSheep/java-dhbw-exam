import backend.database.DatabaseController;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminConsole extends JFrame {

    public JList list1;
    public JPanel contpan;
    private JScrollPane scrollPane;

    public AdminConsole() throws SQLException {
        ResultSet trans = DatabaseController.readUsers("client");
        ArrayList<String> elements = new ArrayList<>();
        System.out.println("Transaction  elements: " + elements);
        while (trans.next()){
            elements.add(trans.getString("name"));
        }
        String[] arr = new String[elements.size()];
        arr = elements.toArray(arr);
        list1.setListData(arr);
        list1.setLayoutOrientation(JList.VERTICAL);
        scrollPane.add(list1);
        this.getContentPane().add(scrollPane);
    }

    public static void createAdminConsole(){
        try {
            AdminConsole verwaltfenst = new AdminConsole();
            verwaltfenst.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            verwaltfenst.setTitle("Kundenübersicht");
            verwaltfenst.setSize(500, 200);
            verwaltfenst.setVisible(true);
        }
        catch (SQLException e) {}
    }
}
