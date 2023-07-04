package UI;

import Entities.*;
import FileOp.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminShowDetailedReportPageForm extends JFrame {
    private JPanel panel;
    private JPanel buttonPanel;
    private JTextField fileNameTextField;
    private JButton createFileButton;
    private JPanel tablePanel;
    private JTable reportTable;

    public AdminShowDetailedReportPageForm(int exerciseResultId){
        add(panel);
        setSize(1000,600);
        setTitle("Admin Report Page");

        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //Set JTable
        String exerciseColumns [] = {"First Number","Second Number","Answer","True False","Time(s)"};
        DefaultTableModel reportModel = new DefaultTableModel (exerciseColumns,0) ;
        reportTable = new JTable (reportModel);
        JScrollPane exerciseScrollPane = new JScrollPane (reportTable);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(exerciseScrollPane, BorderLayout .CENTER);

        for (ExerciseResultDetail e : entities.exerciseResultDetails) {
            if(e.getExerciseResultId() == exerciseResultId){
                Object[] data = {e.getA(),e.getB(),e.getAnswerValue(),e.isTrue(),e.getTime()};
                reportModel.addRow(data);
            }
        }

        createFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Create csv file (detailed report of the chosen exercise)
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

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
