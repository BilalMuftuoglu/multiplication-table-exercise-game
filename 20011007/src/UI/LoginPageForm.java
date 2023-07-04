package UI;

import Entities.User;
import FileOp.*;
import UI.AdminHomePageForm;
import UI.HomePageForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginPageForm extends JFrame {
    private JTextField nameTextField;
    private JPanel panel1;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JLabel validationLabel;


    public LoginPageForm(){


        EntitiesSingleton entities = EntitiesSingleton.getInstance();
        ArrayList<User> users = entities.users;

        add(panel1);
        setSize(500,300);
        setTitle("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checking the accuracy of the entered information
                String username,password;
                username = nameTextField.getText();
                password = new String(passwordField.getPassword());

                for (User user: users) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)){
                        if(user.getRole().equals("Parent")){
                            //Admin login
                            AdminHomePageForm a = new AdminHomePageForm();
                            a.setVisible(true);
                        }else {
                            HomePageForm h = new HomePageForm(user.getId());
                            //Child login
                            h.setVisible(true);
                        }
                        dispose();
                    }
                }
                validationLabel.setText("Could not login");
            }
        });


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username,password;
                username = nameTextField.getText();
                password = new String(passwordField.getPassword());

                int flag = 0;
                for (User user: entities.users) {
                    if(user.getUsername().equals(username)){
                        flag = 1;
                        break;
                    }
                }
                //checks for successful membership
                String message = User.usernamePasswordCompatibility(username,password);
                if(flag == 1){
                    validationLabel.setText("A user with this username already exists");
                }else if(message != null){
                    validationLabel.setText(message);
                }else{
                    User user = new User();
                    user.setRole("Child");
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setId(entities.users.get(entities.users.size()-1).getId() + 1);
                    entities.users.add(user);
                    validationLabel.setText("Successfully signed up");
                }
            }
        });

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((d.getWidth() - this.getWidth()) / 2);
        int y = (int) ((d.getHeight() - this.getHeight()) / 2);
        this.setLocation(x,y);
    }
}
