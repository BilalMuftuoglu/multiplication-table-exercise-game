package UI;

import Entities.*;
import FileOp.EntitiesSingleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminReportUserFilteringPageForm extends JFrame {


    private JPanel panel;
    private JButton selectedUserButton;
    private JTable userTable;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JButton allUsersButton;

    public AdminReportUserFilteringPageForm(){
        add(panel);
        setSize(500,300);
        setTitle("Admin Report Page");

        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //Set JTable
        String userColumns [] = {"User Id", "Username"};
        DefaultTableModel userModel = new DefaultTableModel (userColumns,0) ;
        userTable = new JTable (userModel);
        JScrollPane exerciseScrollPane = new JScrollPane (userTable);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(exerciseScrollPane, BorderLayout .CENTER);
        for (User user : entities.users) {
            if(user.getRole().equals("Child")) {
                Object[] data = {user.getId(), user.getUsername()};
                userModel.addRow(data);
            }
        }


        selectedUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Filter selected user
                if(userTable.getSelectedRow() >= 0){
                    int userIndex = userTable.getSelectedRow();
                    AdminReportExerciseFilteringPageForm a = new AdminReportExerciseFilteringPageForm(userIndex);
                    a.setVisible(true);
                    dispose();
                }
            }
        });
        allUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //No filter
                AdminReportExerciseFilteringPageForm a = new AdminReportExerciseFilteringPageForm(-1);
                a.setVisible(true);
                dispose();
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
