package _ReadAndWriteFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOFile<E> {
    public void writerFileData(List<E> arrayData, String pathname) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathname);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayData);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    public List<E> readFileData(String pathname) {
        List<E> userList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathname);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userList = (List<E>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return userList;
    }

    public void clearFileData(List<E> arrayData, String pathname) throws IOException {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(pathname);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(arrayData);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
