package UI;

import Entities.*;
import FileOp.EntitiesSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminExercisesPageForm extends JFrame{
    private JPanel panel;
    private JTextField a1TextField;
    private JTextField b1TextField;
    private JTextField a2TextField;
    private JTextField b2TextField;
    private JTextField nTextField;
    private JTextField titleTextField;
    private JButton addButton;
    private JButton deleteButton;
    private JList list;

    public AdminExercisesPageForm(){
        add(panel);
        setSize(500,300);
        setTitle("Admin Exercises Page");

        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //set to list exercises on screen
        ArrayList<String> data = new ArrayList<String>();
        for (Exercise e: entities.exercises) {
            data.add(e.getTitle() + "   |   A= "+e.getA1() + "..." + e.getA2() + "   B= " + e.getB1() + "..." + e.getB2() + "   |   " + e.getN() + " Questions");
        }
        list.setListData(data.toArray());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add new exercise
                int n,a1,a2,b1,b2;
                String title;
                title = titleTextField.getText();

                if(title.length() >= 3){
                    try {
                        n = Integer.parseInt(nTextField.getText());
                        a1 = Integer.parseInt(a1TextField.getText());
                        a2 = Integer.parseInt(a2TextField.getText());
                        b1 = Integer.parseInt(b1TextField.getText());
                        b2 = Integer.parseInt(b2TextField.getText());

                        if(n > 0){
                            Exercise exercise = new Exercise();
                            if(entities.exercises.size() != 0){
                                exercise.setId(entities.exercises.get(entities.exercises.size() - 1).getId() + 1);
                            }else{
                                exercise.setId(0);
                            }
                            exercise.setN(n);
                            exercise.setA1(a1);
                            exercise.setA2(a2);
                            exercise.setB1(b1);
                            exercise.setB2(b2);
                            exercise.setTitle(title);

                            entities.exercises.add(exercise);
                            data.add(exercise.getTitle() + "   |   A= "+exercise.getA1() + "..." + exercise.getA2() + "   B= " + exercise.getB1() + "..." + exercise.getB2() + "   |   " + exercise.getN() + " Questions");
                            list.setListData(data.toArray());
                        }

                    } catch (NumberFormatException exception) {
                        System.out.println(exception.getLocalizedMessage());
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //delete the exercise and all information about it except the default exercise
                if(list.getSelectedIndex() != -1 && list.getSelectedIndex() != 0){
                    Exercise exercise = entities.exercises.get(list.getSelectedIndex());

                    ArrayList<Score> scores = new ArrayList<>();
                    for (Score s: entities.scores) {
                        if(s.getExerciseId() != exercise.getId()){
                            scores.add(s);
                        }
                    }
                    entities.scores =scores;

                    ArrayList<ExerciseResult> exerciseResults = new ArrayList<>();
                    for (ExerciseResult exerciseResult : entities.exerciseResults ) {
                        if(exerciseResult.getExerciseId() != exercise.getId()){
                            exerciseResults.add(exerciseResult);
                        }
                    }
                    entities.exerciseResults = exerciseResults;

                    ArrayList<ExerciseResultDetail> exerciseResultDetails = new ArrayList<>();
                    for (ExerciseResultDetail exerciseResultDetail : entities.exerciseResultDetails ) {
                        if(exerciseResultDetail.getExerciseId() != exercise.getId()){
                            exerciseResultDetails.add(exerciseResultDetail);
                        }
                    }
                    entities.exerciseResultDetails = exerciseResultDetails;

                    data.remove(list.getSelectedIndex());
                    entities.exercises.remove(exercise);
                    list.setListData(data.toArray());
                }
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
