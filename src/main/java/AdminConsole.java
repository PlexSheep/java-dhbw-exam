import backend.database.DatabaseController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminConsole extends JFrame{

    public JPanel contpan;
    private JPanel firstRowPanel;
    private JList list1;
    private JScrollPane scrollPane;
    private JButton button1;
    private JButton button2;

    public AdminConsole() throws SQLException {
        contpan.setLayout(new BorderLayout());
        contpan.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));

        JPanel firstRowPanel = new JPanel();
        contpan.add(firstRowPanel, BorderLayout.NORTH);

        firstRowPanel.setLayout(new BorderLayout());
        JLabel tLabel = new JLabel("Zeug");
        firstRowPanel.add(tLabel, BorderLayout.WEST);

        JPanel secondRowPanel = new JPanel(new BorderLayout());
        contpan.add(secondRowPanel, BorderLayout.CENTER);

        ResultSet trans = DatabaseController.readUsers("client");
        ArrayList<String> elements = new ArrayList<>();
        while (trans.next()){
            String zeug = trans.getString("name");
            elements.add(zeug);
            System.out.println(zeug);
        }
        String[] arr = new String[elements.size()];
        arr = elements.toArray(arr);

        list1.setListData(arr);
        scrollPane.add(list1);
        list1.setLayoutOrientation(JList.VERTICAL);
        secondRowPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel lastRowPanel = new JPanel();
        contpan.add(lastRowPanel, BorderLayout.SOUTH);

        lastRowPanel.add(button1);
        lastRowPanel.add(button2);

        this.setContentPane(contpan);
        this.setSize(400, 300);
        this.setVisible(true);
    }

    public static JFrame createAdminConsole(){
        try {
            AdminConsole adcon = new AdminConsole();
            adcon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            adcon.setTitle("Kontoübersicht");
            adcon.setSize(500, 200);
            adcon.setVisible(true);
            return adcon;
        }
        catch (SQLException e) {return null;}
    }

}
