package ua.edu.sundu.j2se.studentHvostin.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.time.LocalDateTime;

public class TestTaskIO {

    @Test
    public void testIO() throws IOException {

        AbstractTaskList tasks = new ArrayTaskList();
        AbstractTaskList newTasks = new ArrayTaskList();
        File file = new File("testTaskIO.out");
        Task task = new Task("task0", LocalDateTime.of(1,1,1,1,1));

        tasks.add(task);
        tasks.add(task);

        TaskIO.writeText(tasks, file);
        TaskIO.readText(newTasks, file);


/*


        TaskIO.writeText(tasks, file);
        TaskIO.readText(tasks, file);



        try(ObjectOutputStream objectStream = new ObjectOutputStream(out)) {
            objectStream.writeObject(tasks);
            TaskIO.write(AbstractTaskList tasks, out);
            TaskIO.read(AbstractTaskList tasks, in);
        }

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream("testTaskIO.out"));
            objectOutputStream.writeObject(task);
            objectOutputStream.writeObject(task);
            objectOutputStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("testTaskIO.out"));
            Task igorTask = (Task) objectInputStream.readObject();
            objectInputStream.close();

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream2.writeObject(task);
            objectOutputStream2.flush();

            ObjectInputStream objectInputStream2 = new ObjectInputStream(
                    new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            Task igorRestoredFromByte = (Task) objectInputStream2.readObject();
            objectInputStream2.close();

            System.out.println("Before Serialize: " + "\n" + task);
            System.out.println("After Restored From Byte: " + "\n" + igorRestoredFromByte);
            System.out.println("After Restored: " + "\n" + igorTask);

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }


 */
    }//Assertions.assertEquals(tasks, newTasks);
}
