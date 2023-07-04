package UI;

import Entities.*;
import FileOp.EntitiesSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScorePageForm extends JFrame {

    private JPanel panel;
    private JLabel scoreLabel;
    private JButton homePageButton;

    public ScorePageForm(int scorePoint, int userId, Exercise exercise, ExerciseResult exerciseResult){
        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        add(panel);
        setSize(500,300);
        setTitle("Score Page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int flag = 0;

        for (Score score: entities.scores) {
            //Find out if the same game as the current user has been played before
            if (score.getUserId() == userId && score.getExerciseId() == exercise.getId()) {
                if (score.getScore() < scorePoint) {
                    //newly played game score is the best score
                    score.setScore(scorePoint);
                    flag = 1;
                }else {
                    //user already has a high score
                    flag = 2;
                }
                break;
            }
        }

        if(flag == 0){
            //this game is played for the first time by current user
            Score score = new Score();
            score.setScore(scorePoint);
            if(entities.scores.size() == 0){
                score.setId(0);
            }else{
                score.setId(entities.scores.get(entities.scores.size() -1 ).getId() + 1);
            }

            score.setUserId(userId);
            score.setExerciseId(exercise.getId());
            entities.scores.add(score);
            scoreLabel.setText("New High Score: " + scorePoint);
        }else if(flag == 1){
            scoreLabel.setText("New High Score: " + scorePoint);
        }else{
            scoreLabel.setText("Score: " + scorePoint);
        }

        homePageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HomePageForm h = new HomePageForm(userId);
                h.setVisible(true);
                dispose();
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
