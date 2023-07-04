package FileOp;

import FileOp.EntitiesSingleton;
import com.sun.tools.javac.Main;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.URISyntaxException;

public class MyFileWriter {

    public static void writeEntitiesToTXT() {
        EntitiesSingleton entities = EntitiesSingleton.getInstance();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath("users.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities.users);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath("exercises.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities.exercises);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath("exerciseResults.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities.exerciseResults);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath("exerciseResultDetails.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities.exerciseResultDetails);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(getFilePath("scores.txt"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(entities.scores);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeReportToCSV(DefaultTableModel model,String fileName) throws IOException {
        try {
            FileWriter writer = new FileWriter(getFilePath(fileName));
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            int numberOfRow = model.getRowCount();
            int numberOfColumns = model.getColumnCount();

            for (int i = 0; i < numberOfColumns; i++) {
                bufferedWriter.write(model.getColumnName(i));
                if (i != numberOfColumns - 1) {
                    bufferedWriter.write(";");
                }
            }
            bufferedWriter.newLine();

            for (int i = 0; i < numberOfRow; i++) {
                for (int j = 0; j < numberOfColumns; j++) {
                    bufferedWriter.write(model.getValueAt(i, j).toString());
                    if (j != numberOfColumns - 1) {
                        bufferedWriter.write(";");
                    }
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFilePath(String fileName) throws URISyntaxException {
        String jarFilePath = MyFileWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        return new File(jarFilePath).getParent() + File.separator + fileName;
    }
}
