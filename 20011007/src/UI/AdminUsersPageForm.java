package UI;

import Entities.*;
import FileOp.EntitiesSingleton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminUsersPageForm extends JFrame {
    private JPanel panel;
    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JList userList;
    private JButton addButton;
    private JButton deleteButton;

    public AdminUsersPageForm(){
        add(panel);
        setSize(500,300);
        setTitle("Admin Users Page");

        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //set to list users on screen
        ArrayList<String> users = new ArrayList<String>();
        for (User u: entities.users) {
            users.add("Id= "+u.getId() + "   |   Username: " + u.getUsername() + "   |   Password: " + u.getPassword());
        }
        userList.setListData(users.toArray());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Add new user
                String username, password;
                username = usernameTextField.getText();
                password = passwordTextField.getText();

                if(User.usernamePasswordCompatibility(username,password) == null){
                     int flag = 0;

                     for (User u: entities.users) {
                         //check if there is someone with the same username
                         if(u.getUsername().equals(username)){
                             flag = 1;
                             break;
                         }
                     }
                     if(flag == 0){
                         User user = new User();
                         user.setRole("Child");
                         user.setUsername(username);
                         user.setPassword(password);
                         user.setId(entities.users.get(entities.users.size() -1).getId() + 1);

                         users.add("Id= "+user.getId() + "   |   Username: " + username + "   |   Password: " + password);
                         entities.users.add(user);

                         userList.setListData(users.toArray());
                     }
                 }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userList.getSelectedIndex() != -1){
                    User user = entities.users.get(userList.getSelectedIndex());
                    //Delete user and all information about it
                    if(user.getRole().equals("Child")){
                        ArrayList<Score> scores = new ArrayList<>();
                        for (Score s: entities.scores) {
                            if(s.getUserId() != user.getId()){
                                scores.add(s);
                            }
                        }
                        entities.scores =scores;

                        ArrayList<ExerciseResult> exerciseResults = new ArrayList<>();
                        for (ExerciseResult exerciseResult : entities.exerciseResults ) {
                            if(exerciseResult.getUserId() != user.getId()){
                                exerciseResults.add(exerciseResult);
                            }
                        }
                        entities.exerciseResults = exerciseResults;

                        ArrayList<ExerciseResultDetail> exerciseResultDetails = new ArrayList<>();
                        for (ExerciseResultDetail exerciseResultDetail : entities.exerciseResultDetails ) {
                            if(exerciseResultDetail.getUserId() != user.getId()){
                                exerciseResultDetails.add(exerciseResultDetail);
                            }
                        }
                        entities.exerciseResultDetails = exerciseResultDetails;

                        users.remove(userList.getSelectedIndex());
                        entities.users.remove(user);
                        userList.setListData(users.toArray());
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
