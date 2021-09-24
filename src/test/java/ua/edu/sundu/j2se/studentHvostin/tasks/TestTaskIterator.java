package ua.edu.sundu.j2se.studentHvostin.tasks;

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
        LocalDateTime time = LocalDateTime.of(0,0,0,0,0);
        Task testTask = new Task("testTask", time);

        array.add(testTask);
        array.add(new Task("task", time));
        array.add(testTask);

        Iterator iter = array.iterator();
        int counter = 0;
        while (counter < 3) {
            Assertions.assertEquals(iter.next(), array.getTask(counter));
            ++counter;
        }
//        Assertions.assertEquals(iter.next(), testTask);
//        iter.next();
//        Assertions.assertEquals(iter.next(), testTask);
    }
}
