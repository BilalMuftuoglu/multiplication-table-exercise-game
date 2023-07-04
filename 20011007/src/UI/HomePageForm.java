package UI;

import Entities.*;
import FileOp.EntitiesSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HomePageForm extends JFrame {
    private JPanel panel;
    private JList list;
    private JButton startButton;


    public HomePageForm(int userId){

        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        add(panel);
        setSize(500,300);
        setTitle("HomePage");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<String> a = new ArrayList<String>();
        for (Exercise e: entities.exercises) {
            //Finding the current user's highest score in each exercise
            int scorePoint = 0;
            for (Score score: entities.scores){
                if(score.getUserId() == userId && score.getExerciseId() == e.getId()){
                    scorePoint = score.getScore();
                }
            }
            a.add(e.getTitle() + "   |   A= "+e.getA1() + "..." + e.getA2() + "   B= " + e.getB1() + "..." + e.getB2() + "   |   " + e.getN() + " Questions" + "   |   High Score: " + scorePoint);
        }

        list.setListData(a.toArray());

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndex() != -1){
                    //Starting an exercise
                    QuizPageForm q = new QuizPageForm(list.getSelectedIndex(),userId);
                    q.setVisible(true);
                    dispose();
                }
                System.out.println(list.getSelectedIndex());
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);

    }
}
