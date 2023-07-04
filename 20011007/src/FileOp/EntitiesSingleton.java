package FileOp;

import Entities.*;
import UI.AdminHomePageForm;
import UI.LoginPageForm;
import com.sun.tools.javac.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class EntitiesSingleton {
    private static EntitiesSingleton reader_instance = null;

    public ArrayList<User> users;
    public ArrayList<Exercise> exercises;
    public ArrayList<ExerciseResult> exerciseResults;
    public ArrayList<ExerciseResultDetail> exerciseResultDetails;
    public ArrayList<Score> scores;

    private EntitiesSingleton(){

        users = new ArrayList<User>();
        try{
            File usersFile = new File( getFilePath("users.txt"));
            if(usersFile.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(getFilePath("users.txt"));
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    users = (ArrayList<User>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        exercises = new ArrayList<Exercise>();
        try{
            File exercisesFile = new File( getFilePath("exercises.txt"));
            if(exercisesFile.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(getFilePath("exercises.txt"));
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    exercises = (ArrayList<Exercise>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        exerciseResults = new ArrayList<ExerciseResult>();
        try{
            File exerciseResultsFile = new File( getFilePath("exerciseResults.txt"));
            if(exerciseResultsFile.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(getFilePath("exerciseResults.txt"));
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    exerciseResults = (ArrayList<ExerciseResult>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        exerciseResultDetails = new ArrayList<ExerciseResultDetail>();
        try{
            File exerciseResultDetailsFile = new File(getFilePath("exerciseResultDetails.txt"));
            if(exerciseResultDetailsFile.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(getFilePath("exerciseResultDetails.txt"));
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    exerciseResultDetails = (ArrayList<ExerciseResultDetail>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        scores = new ArrayList<Score>();
        try{
            File scoresFile = new File( getFilePath("scores.txt"));
            if(scoresFile.exists()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(getFilePath("scores.txt"));
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    scores = (ArrayList<Score>) objectInputStream.readObject();
                    objectInputStream.close();
                    fileInputStream.close();
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    // Static method to create instance of Singleton class
    public static synchronized EntitiesSingleton getInstance(){
        if (reader_instance == null)
            reader_instance = new EntitiesSingleton();

        return reader_instance;
    }

    private static String getFilePath(String fileName) throws URISyntaxException {
        String jarFilePath = EntitiesSingleton.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        return new File(jarFilePath).getParent() + File.separator + fileName;
    }
}
