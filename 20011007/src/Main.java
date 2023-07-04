import Entities.*;
import FileOp.EntitiesSingleton;
import FileOp.MyFileWriter;
import UI.LoginPageForm;

import javax.swing.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        //Information is read from files for the first time as a singleton structure
        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        //if the program is run for the first time, parent information is added to the database
        if(entities.users.isEmpty()){
            User user= new User();
            user.setId(0);
            user.setRole("Parent");
            user.setUsername("parent");
            user.setPassword("123456");
            entities.users.add(user);
        }

        //If the program is run for the first time, the default exercise is added to the database
        if(entities.exercises.isEmpty()){
            Exercise exercise =  new Exercise();
            exercise.setId(0);
            exercise.setN(5);
            exercise.setA1(1);
            exercise.setA2(10);
            exercise.setB1(1);
            exercise.setB2(10);
            exercise.setTitle("Default mode");
            entities.exercises.add(exercise);
        }

        for (User model : entities.users) {
            System.out.println(model);
        }

        for (Exercise model : entities.exercises) {
            System.out.println(model);
        }

        for (ExerciseResult model : entities.exerciseResults) {
            System.out.println(model);
        }

        for (ExerciseResultDetail model : entities.exerciseResultDetails) {
            System.out.println(model);
        }

        for (Score model : entities.scores) {
            System.out.println(model);
        }

        //When the program terminates, all information is written to the files.
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                MyFileWriter.writeEntitiesToTXT();
                System.out.println("Program terminated");
            }
        });

        //When the program runs, the login screen opens.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginPageForm lp = new LoginPageForm();
                lp.setVisible(true);
            }
        });
    }
}