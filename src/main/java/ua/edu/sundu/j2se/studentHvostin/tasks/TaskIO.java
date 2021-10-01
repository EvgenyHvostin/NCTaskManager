package ua.edu.sundu.j2se.studentHvostin.tasks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        ObjectOutputStream objectStream = new ObjectOutputStream(out);
        objectStream.writeObject(tasks);
        objectStream.flush();
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectStream = new ObjectInputStream(in);
        ArrayTaskList readTasks = (ArrayTaskList) objectStream.readObject();
        objectStream.close();

        for (int i = 0; i <= readTasks.getSize(); i++) {
            tasks.add(readTasks.getTask(i));
        }
    }

    public static void writeBinary( AbstractTaskList tasks, File file) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(String.valueOf(file)))) {
            write(tasks, w);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) {
        try (BufferedReader r = new BufferedReader(new FileReader(String.valueOf(file)))) {
            read(tasks, r);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }


    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        out.append(gson.toJson(tasks));
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        AbstractTaskList readTasks = gson.fromJson(in, AbstractTaskList.class);

        for (int i = 0; i <= readTasks.getSize(); i++) {
            tasks.add(readTasks.getTask(i));
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(String.valueOf(file)))) {
            write(tasks, w);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) {
        try (BufferedReader r = new BufferedReader(new FileReader(String.valueOf(file)))) {
            read(tasks, r);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
