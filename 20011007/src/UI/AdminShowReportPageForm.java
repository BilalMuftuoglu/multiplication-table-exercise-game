package UI;

import Entities.*;
import FileOp.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminShowReportPageForm extends JFrame {
    private JTable reportTable;
    private JPanel panel;
    private JPanel tablePanel;
    private JPanel buttonPanel;
    private JTextField fileNameTextField;
    private JButton createFileButton;
    private JButton detailedReportButton;

    DefaultTableModel reportModel;

    public void createTable(int userIndex, int exerciseIndex){
        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //Set JTable (Report)
        String reportColumns [] = {"Result Id","Username", "Title", "Number of Questions","Range of A","Range of B","Start Time","End Time","Number of Correct Answers","Score"};
        reportModel = new DefaultTableModel (reportColumns,0) ;
        reportTable = new JTable (reportModel);
        JScrollPane exerciseScrollPane = new JScrollPane (reportTable);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(exerciseScrollPane, BorderLayout .CENTER);

        if(userIndex == -1 && exerciseIndex == -1){
            //No filters for user and exercise
            for (ExerciseResult result : entities.exerciseResults) {
                Exercise exercise = new Exercise();
                User user = new User();

                for (Exercise e: entities.exercises) {
                    if(e.getId() == result.getExerciseId()){
                        exercise = e;
                        break;
                    }
                }
                for (User u: entities.users) {
                    if(u.getId() == result.getUserId()){
                        user = u;
                        break;
                    }
                }
                reportModel.addRow(getObjects(user,exercise,result));
            }
        }else if(userIndex != -1 && exerciseIndex == -1){
            //filter for user but no filter for exercise
            User user = entities.users.get(userIndex + 1);

            for (ExerciseResult result : entities.exerciseResults) {
                if(result.getUserId() == user.getId()){
                    Exercise exercise = new Exercise();

                    for (Exercise e: entities.exercises) {
                        if(e.getId() == result.getExerciseId()){
                            exercise = e;
                            break;
                        }
                    }
                    reportModel.addRow(getObjects(user,exercise,result));
                }
            }
        }else if(userIndex == -1 && exerciseIndex != -1){
            //filter for exercise but no filter for user
            Exercise exercise = entities.exercises.get(exerciseIndex);

            for (ExerciseResult result : entities.exerciseResults) {
                if(exercise.getId() == result.getExerciseId()){
                    User user = new User();

                    for (User u: entities.users) {
                        if(u.getId() == result.getUserId()){
                            user = u;
                            break;
                        }
                    }
                    reportModel.addRow(getObjects(user,exercise,result));
                }
            }
        }else if(userIndex != -1 && exerciseIndex != -1){
            //There are filters for user and exercise
            User user = entities.users.get(userIndex + 1);
            Exercise exercise = entities.exercises.get(exerciseIndex);

            for (ExerciseResult result : entities.exerciseResults) {
                if(result.getUserId() == user.getId() && result.getExerciseId() == exercise.getId()) {
                    reportModel.addRow(getObjects(user,exercise,result));
                }
            }
        }

    }

    public Object[] getObjects(User user,Exercise exercise,ExerciseResult result){
        Object[] data = {result.getId(),user.getUsername(), exercise.getTitle(),exercise.getN(),exercise.getA1() + "..." + exercise.getA2(),exercise.getB1() + "..." + exercise.getB2(),result.getStartTime(),result.getEndTime(),result.getNumberOfCorrectAnswers(),result.getScore()};
        return data;
    }

    public AdminShowReportPageForm(int userIndex, int exerciseIndex){
        add(panel);
        setSize(1000,600);
        setTitle("Admin Report Page");

        createTable(userIndex,exerciseIndex);

        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create csv file (report)
                String fileName = fileNameTextField.getText();
                if (fileName.length() > 0){
                    fileName = fileName + ".csv";
                    try {
                        MyFileWriter.writeReportToCSV(reportModel,fileName);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        detailedReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //show details of selected exercise
                if(reportTable.getSelectedRow() >= 0){
                    int exerciseResultId = Integer.parseInt(reportModel.getDataVector().get(reportTable.getSelectedRow()).get(0).toString());
                    AdminShowDetailedReportPageForm a = new AdminShowDetailedReportPageForm(exerciseResultId);
                    a.setVisible(true);
                }
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
