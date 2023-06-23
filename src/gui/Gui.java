package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    public static void createFrame() {
        JDialog dialog = new JDialog();
        dialog.setTitle("My Frame");
        dialog.setSize(400, 300);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fancy GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(new Insets(10, 20, 10, 20)));

        JPanel firstRowPanel = new JPanel();
        panel.add(firstRowPanel, BorderLayout.NORTH);

        JLabel label = new JLabel();
        ImageIcon imgThisImg = new ImageIcon(Gui.class.getResource(""));
        label.setIcon(imgThisImg);
        firstRowPanel.add(label);

        JTextField textField = new JTextField(20); // Specify the width of the text field
        firstRowPanel.add(textField);

        JPanel secondRowPanel = new JPanel(new BorderLayout());
        panel.add(secondRowPanel, BorderLayout.CENTER);

        String[] elements = {"Hallo", "Schüss", "Auf Wiedersehen", ".", ".", "Hallo", "Schüss", "Auf Wiedersehen", ".", ".", "Hallo", "Schüss", "Auf Wiedersehen", ".", "."};
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
                createFrame();
            }
        });
        JButton button1 = new JButton("Überweisung");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createFrame();
            }
        });
        lastRowPanel.add(button);
        lastRowPanel.add(button1);

        frame.getContentPane().add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}
