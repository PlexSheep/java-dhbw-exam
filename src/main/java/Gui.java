import backend.database.DatabaseController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Gui {

    public static JFrame createGUI() throws SQLException {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));

        JPanel firstRowPanel = new JPanel();
        panel.add(firstRowPanel, BorderLayout.NORTH);



        JPanel textjpanel = new JPanel(new BorderLayout());
        JLabel tlabel = new JLabel("Transaktionen");
        textjpanel.add(tlabel, BorderLayout.WEST);
        firstRowPanel.add(textjpanel);

        JPanel secondRowPanel = new JPanel(new BorderLayout());
        panel.add(secondRowPanel, BorderLayout.CENTER);

        ResultSet trans = DatabaseController.readTransactionByClient(Main.loggedIn);
        ArrayList<String> elements = new ArrayList<String>();
        System.out.println("Transaction  elements: " + elements);
        while (trans.next()){
            elements.add(new String("User " + trans.getInt("sender") + " Sent " + trans.getInt("amount") + " to: " +trans.getString("recipient")));
        }
        String[] arr = new String[elements.size()];
        arr = elements.toArray(arr);

        JList<String> list = new JList<>(arr);
        JScrollPane scrollPane = new JScrollPane(list);
        list.setLayoutOrientation(JList.VERTICAL);
        secondRowPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel lastRowPanel = new JPanel();
        panel.add(lastRowPanel, BorderLayout.SOUTH);

        JLabel resultLabel = new JLabel();
        lastRowPanel.add(resultLabel);

        JButton button = new JButton("Überweisung");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transaction.createTransaction();       }
        });
        JButton button1 = new JButton("Konto Center");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserData.createUser();
            }
        });

        lastRowPanel.add(button);
        lastRowPanel.add(button1);

        frame.getContentPane().add(panel);
        frame.setSize(400, 300);
        frame.setVisible(true);
        return frame;
    }
}
