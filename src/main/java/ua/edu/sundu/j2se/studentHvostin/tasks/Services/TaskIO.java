package ua.edu.sundu.j2se.studentHvostin.tasks.Services;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.AbstractTaskList;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

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
                //.registerTypeAdapter(Task.class, new TaskSerializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .setPrettyPrinting()
                .create();

        ArrayList<Task> ser = new ArrayList<>();

        for (Task task : tasks) {

            ser.add(task);
        }
        out.append(gson.toJson(ser));
        //out.append(gson.toJson(ser));
    }

    public static void read(AbstractTaskList tasks, final Reader in) throws IOException {
        com.google.gson.Gson gson = new GsonBuilder()
             //   .registerTypeAdapter(Task.class, new TaskDeserializer())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .setPrettyPrinting()
                .create();
        ArrayList<Task> read = gson.fromJson(in, new TypeToken<ArrayList<Task>>(){}.getType());
        for (Task task : read) {
            tasks.add(task);
        }
    }

    public static void writeText(final AbstractTaskList tasks, File file) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(String.valueOf(file)))) {
            write(tasks, w);
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks,final File file) {
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

    public static void readBinary(AbstractTaskList tasks, final File file) {
        try (ObjectInputStream i = new ObjectInputStream(new FileInputStream(String.valueOf(file)))) {
            read(tasks, i);
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static class TaskSerializer implements JsonSerializer<Task> {
        @Override
        public JsonElement serialize(final Task task, final Type type, final JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("title", new JsonPrimitive(task.getTitle()));
            result.add("activity", new JsonPrimitive(task.isActive()));
            result.add("repetition", new JsonPrimitive(task.getRepeatInterval()));
            if (task.isRepeated()) {
                result.add("start",new JsonPrimitive(String.valueOf(task.getStartTime())));
                result.add("end", new JsonPrimitive(String.valueOf(task.getEndTime())));
            } else {
                result.add("time", new JsonPrimitive(task.getTime().toString()));
            }
            return result;
        }
    }
/*
    private static class TaskDeserializer implements JsonDeserializer<Task> {
        @Override
        public Task deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Task task = new Task();



            return task;
        }
    }
 */
    private static class LocalDateTimeSerializer implements JsonSerializer < LocalDateTime > {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss");
        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    private static class LocalDateTimeDeserializer implements JsonDeserializer < LocalDateTime > {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("d::MMM::uuuu HH::mm::ss").withLocale(Locale.ENGLISH));
        }
    }



}