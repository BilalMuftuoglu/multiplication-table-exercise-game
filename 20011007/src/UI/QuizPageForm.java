package UI;

import Entities.*;
import FileOp.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QuizPageForm extends JFrame {

    private JPanel panel;
    private JButton nextButton;
    private JTextField textField;
    private JLabel questionCounterLabel;
    private JLabel timeLabel;
    private JLabel questionLabel;

    private Exercise exercise;//Exercise type
    private ExerciseResult exerciseResult = new ExerciseResult();

    private int time = 0;//total time (second)
    private int tmp;
    private int questionCounter = 1;
    private int correctAnswerCounter = 0;
    private int A;
    private int B;

    public void setDefaultQuestion(){
        tmp = time;
        textField.setText("");
        A = (int)Math.floor(Math.random() * (exercise.getA2() - exercise.getA1() + 1) + exercise.getA1());
        B = (int)Math.floor(Math.random() * (exercise.getB2() - exercise.getB1() + 1) + exercise.getB1());
        questionLabel.setText(A + " * " + B);
        questionCounterLabel.setText("Question "+ questionCounter++);
    }

    public QuizPageForm(int index,int userId){

        EntitiesSingleton entities = EntitiesSingleton.getInstance();
        exercise = entities.exercises.get(index);

        timeLabel.setText("Time  0:0");
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int minute = time / 60;
                int second = time % 60;
                timeLabel.setText("Time  " + minute + ":" + second);
                time++;
            }
        });
        timer.start();

        setDefaultQuestion();

        add(panel);
        setSize(500,300);
        setTitle("Quiz");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Start time
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);

        if(entities.exerciseResults.size() == 0){
            exerciseResult.setId(0);
        }else {
            exerciseResult.setId(entities.exerciseResults.get(entities.exerciseResults.size() -1 ).getId() + 1);
        }
        exerciseResult.setExerciseId(exercise.getId());
        exerciseResult.setUserId(userId);
        exerciseResult.setStartTime(formattedDate);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Question detail
                ExerciseResultDetail exerciseResultDetail = new ExerciseResultDetail();
                if(Integer.parseInt(textField.getText()) == A * B){
                    correctAnswerCounter++;
                    exerciseResultDetail.setTrueFalse(true);

                }else{
                    exerciseResultDetail.setTrueFalse(false);
                }

                if(entities.exerciseResultDetails.size() == 0){
                    exerciseResultDetail.setId(0);
                }else{
                    exerciseResultDetail.setId(entities.exerciseResultDetails.get(entities.exerciseResultDetails.size() -1 ).getId() + 1);
                }
                exerciseResultDetail.setExerciseResultId(exerciseResult.getId());
                exerciseResultDetail.setExerciseId(exercise.getId());
                exerciseResultDetail.setUserId(userId);
                exerciseResultDetail.setA(A);
                exerciseResultDetail.setB(B);
                exerciseResultDetail.setAnswerValue(Integer.parseInt(textField.getText()));
                exerciseResultDetail.setTime(time - tmp);

                entities.exerciseResultDetails.add(exerciseResultDetail);

                if(questionCounter <= exercise.getN()){
                    setDefaultQuestion();
                }else {
                    timer.stop();
                    //End time
                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDate = myDateObj.format(myFormatObj);

                    //Score formula based on time and number of correct answers
                    double score =  ((double) correctAnswerCounter / (double) exercise.getN()) * (100 / ((double) time / ((double) exercise.getN())));

                    exerciseResult.setNumberOfCorrectAnswers(correctAnswerCounter);
                    exerciseResult.setEndTime(formattedDate);
                    exerciseResult.setScore((int) score);

                    entities.exerciseResults.add(exerciseResult);

                    System.out.println(exerciseResult);
                    //Show score
                    ScorePageForm s = new ScorePageForm((int) score,userId,exercise,exerciseResult);
                    s.setVisible(true);
                    dispose();
                }
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
