package UI;

import UI.AdminExercisesPageForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomePageForm extends JFrame {

    private JPanel panel;
    private JButton usersButton;
    private JButton exercisesButton;
    private JButton createReportButton;

    public AdminHomePageForm(){

        add(panel);
        setSize(500,300);
        setTitle("Admin Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminUsersPageForm a = new AdminUsersPageForm();
                a.setVisible(true);
            }
        });

        exercisesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminExercisesPageForm a = new AdminExercisesPageForm();
                a.setVisible(true);
            }
        });

        createReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminReportUserFilteringPageForm a = new AdminReportUserFilteringPageForm();
                a.setVisible(true);
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
