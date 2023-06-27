import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui {

    public static JFrame createGUI() {
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

        String[] elements = {"Amogus sent you: 10000000$", "Whatcolorisyourbugatti withdrew: 0.1$", "Placeholder", "Placeholder", "Placeholder", "Placeholder", "Placeholder", "Auf Placeholder", "Placeholder", "Placeholder", "Placeholder", "Placeholder", "Placeholder", "Placeholder", "Placeholder"};
        JList<String> list = new JList<>(elements);
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
