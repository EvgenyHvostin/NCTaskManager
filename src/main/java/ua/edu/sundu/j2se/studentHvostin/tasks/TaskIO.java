package ua.edu.sundu.j2se.studentHvostin.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class TaskIO {
    public static void write(final AbstractTaskList tasks, OutputStream out) {
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            objectOutputStream.write(gson.toJson(tasks).getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void read(AbstractTaskList tasks, final InputStream in) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            AbstractTaskList readTasks;

            readTasks = gson.fromJson(String.valueOf(objectInputStream), AbstractTaskList.class);

            for (int i = 0; i <= readTasks.getSize(); i++) {
                tasks.add(readTasks.getTask(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBinary(final AbstractTaskList tasks, File file) {

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();

            objectOutputStream.write(gson.toJson(tasks).getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readBinary(AbstractTaskList tasks, final File file) {

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            AbstractTaskList readTasks;

            readTasks = gson.fromJson(String.valueOf(objectInputStream), AbstractTaskList.class);

            for (int i = 0; i <= readTasks.getSize(); i++) {
                tasks.add(readTasks.getTask(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
