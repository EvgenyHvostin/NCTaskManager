package ua.edu.sundu.j2se.studentHvostin.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.Services.TaskIO;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.ArrayTaskList;

import java.io.*;
import java.time.LocalDateTime;

public class TestTaskIO {

    @Test
    public void testIO() throws IOException {

        AbstractTaskList tasks = new ArrayTaskList();
        AbstractTaskList newBinaryTasks = new ArrayTaskList();
        AbstractTaskList newTextTasks = new ArrayTaskList();
        File file = new File("testTaskIO.out");
        Task task = new Task("task0", LocalDateTime.of(1,1,1,1,1));

        tasks.add(task);
        tasks.add(task);

        TaskIO.writeBinary(tasks, file);
        TaskIO.readBinary(newBinaryTasks, file);

        Assertions.assertEquals(newBinaryTasks.toString(), tasks.toString());

        TaskIO.writeText(tasks, file);
        TaskIO.readText(newTextTasks, file);

        //Assertions.assertEquals(newTextTasks.toString(), tasks.toString());

    }
}
