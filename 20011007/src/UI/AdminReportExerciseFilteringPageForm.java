package UI;

import Entities.*;
import FileOp.EntitiesSingleton;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminReportExerciseFilteringPageForm extends JFrame {

    private JPanel panel;
    private JPanel tablePanel;
    private JTable exerciseTable;
    private JPanel buttonPanel;
    private JButton allExercisesButton;
    private JButton selectedExerciseButton;

    public AdminReportExerciseFilteringPageForm(int userIndex){
        add(panel);
        setSize(500,300);
        setTitle("Admin Report Page");

        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //Set JTable
        String exerciseColumns [] = {"Exercise Id", "Title","Number of Questions","Min of A","Max of A","Min of B","Max of B"};
        DefaultTableModel exerciseModel = new DefaultTableModel (exerciseColumns,0) ;
        exerciseTable = new JTable (exerciseModel);
        JScrollPane exerciseScrollPane = new JScrollPane (exerciseTable);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(exerciseScrollPane, BorderLayout .CENTER);
        for (Exercise exercise : entities.exercises) {
            Object[] data = {exercise.getId(), exercise.getTitle(),exercise.getN(),exercise.getA1(),exercise.getA2(),exercise.getB1(),exercise.getB2()};
            exerciseModel.addRow(data);
        }


        selectedExerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Filter selected exercise
                if(exerciseTable.getSelectedRow() >= 0){
                    int exerciseIndex = exerciseTable.getSelectedRow();
                    AdminShowReportPageForm a = new AdminShowReportPageForm(userIndex,exerciseIndex);
                    a.setVisible(true);
                    dispose();
                }
            }
        });


        allExercisesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //No filter
                AdminShowReportPageForm a = new AdminShowReportPageForm(userIndex,-1);
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
