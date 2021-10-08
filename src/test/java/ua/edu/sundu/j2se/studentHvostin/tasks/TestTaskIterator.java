package ua.edu.sundu.j2se.studentHvostin.tasks;

import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.factory.ListTypes;
import ua.edu.sundu.j2se.studentHvostin.tasks.factory.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Iterator;

public class TestTaskIterator {

    @Test
    public void TestIt() {
        AbstractTaskList array = new TaskListFactory().createTaskList(ListTypes.ARRAY);
        LocalDateTime time = LocalDateTime.of(1,1,1,1,1);
        Task testTask = new Task("testTask", time);

        array.add(new Task("task", time));
        array.add(new Task("task", time));
        array.add(testTask);
        array.add(new Task("task", time));

        Iterator<Task> iter = array.iterator();

        iter.next();
        iter.next();
        iter.remove();

        Assertions.assertEquals(array.getTask(1).getTitle(), "testTask");
/*
        int counter = 0;
        while (counter < 4) {
            Assertions.assertEquals(iter.next(), array.getTask(counter));
            ++counter;
        }

        iter.remove();
        Assertions.assertEquals(array.getSize(), 3);
        iter.remove();
        Assertions.assertEquals(array.getSize(), 2);


 */
    }
}
