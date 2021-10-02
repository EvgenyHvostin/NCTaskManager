package ua.edu.sundu.j2se.studentHvostin.tasks;

import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Type;

public class TaskIO {

    public static void write(AbstractTaskList tasks, OutputStream out) throws IOException {
        try(ObjectOutputStream objectStream = new ObjectOutputStream(out)) {
            objectStream.writeObject(tasks);
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) throws IOException, ClassNotFoundException {
        try(ObjectInputStream objectStream = new ObjectInputStream(in)) {
            AbstractTaskList readTasks = tasks.getClass().cast(objectStream.readObject());
            for (int i = 0; i <= readTasks.getSize(); i++) {
                tasks.add(readTasks.getTask(i));
            }
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        com.google.gson.Gson gson = new GsonBuilder().registerTypeAdapter(Task.class, new TaskSerializer())
                .create();
        for (Task task : tasks) {
            out.append(gson.toJson(task));
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        AbstractTaskList readTasks = gson.fromJson(in, (Type) tasks.getClass());

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

    public static class TaskSerializer implements JsonSerializer<Task> {
        public JsonElement serialize(final Task task, final Type type, final JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("title", new JsonPrimitive(task.getTitle()));
            result.add("activity", new JsonPrimitive(task.isActive()));
            result.add("repetition", new JsonPrimitive(task.getRepeatInterval()));
            if (task.isRepeated()) {
                result.add("start", new JsonPrimitive(String.valueOf(task.getStartTime())));
                result.add("end", new JsonPrimitive(task.getEndTime().toString()));
            } else {
                result.add("time", new JsonPrimitive(task.getTime().toString()));
            }
            return result;
        }
    }

}