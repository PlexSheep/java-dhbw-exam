import backend.database.DatabaseController;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminConsole extends JFrame {


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
        JList list1 = new JList<>(arr);
        list1.setLayoutOrientation(JList.VERTICAL);
        scrollPane.add(list1);
        contpan.add(scrollPane);
    }

    public static void createAdminConsole(){
        try {
            AdminConsole test = new AdminConsole();
            test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            test.setContentPane(test.contpan);
            test.setTitle("Kundenübersicht");
            test.setSize(500, 200);
            test.setVisible(true);
        }
        catch (SQLException e) {}
    }
}
