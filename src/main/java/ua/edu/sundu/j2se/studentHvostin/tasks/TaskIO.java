package ua.edu.sundu.j2se.studentHvostin.tasks;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

public class TaskIO {

    public static void write(final AbstractTaskList tasks, OutputStream out) throws IOException {
        try(ObjectOutputStream objectStream = new ObjectOutputStream(out)) {
            objectStream.writeObject(tasks);
        }
    }

    public static void read(AbstractTaskList tasks, final InputStream in) throws IOException, ClassNotFoundException {
        try(ObjectInputStream objectStream = new ObjectInputStream(in)) {
            AbstractTaskList readTasks = tasks.getClass().cast(objectStream.readObject());
            for (int i = 0; i < readTasks.getSize(); i++) {
                tasks.add(readTasks.getTask(i));
            }
        }
    }

    public static void write(final AbstractTaskList tasks, Writer out) throws IOException {
        com.google.gson.Gson gson = new GsonBuilder()
                .registerTypeAdapter(Task.class, new TaskSerializer()).create();

        //out.append(gson.toJson(tasks.getTask(0)));
        ArrayList<Task> ser = new ArrayList<>();

        for (Task task : tasks) {
            ser.add(task);
        }
        out.append(gson.toJson(ser));
    }

    public static void read(AbstractTaskList tasks, final Reader in) {
        com.google.gson.Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                    @Override
                    public LocalDateTime deserialize(
                            JsonElement json,
                            Type type,
                            JsonDeserializationContext jsonDeserializationContext)
                            throws JsonParseException {
                        Instant instant = Instant.ofEpochMilli(json.getAsJsonPrimitive().getAsCharacter());
                        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                    }
                }).create();

        ArrayList<Task> arr = gson.fromJson(in, new TypeToken<ArrayList<Task>>(){}.getType());

        for (Task task : arr) {
            tasks.add(task);
        }

        //System.out.println(tasks.toString());
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

    public static void writeBinary(final AbstractTaskList tasks, File file) {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(String.valueOf(file)))) {
            write(tasks, o);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks,final File file) {
        try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(String.valueOf(file)))) {
            read(tasks, i);
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static class TaskSerializer implements JsonSerializer<Task> {
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