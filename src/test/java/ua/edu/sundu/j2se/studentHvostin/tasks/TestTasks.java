package ua.edu.sundu.j2se.studentHvostin.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TestTasks {

    @Test
    public void testIncoming() {
        AbstractTaskList testList = new ArrayTaskList();
        Task incomTask0 = new Task("0", LocalDateTime.now().plusDays(50));
        Task incomTask1 = new Task("1",
                LocalDateTime.now().plusDays(40),
                LocalDateTime.now().plusDays(80), 10);
        LocalDateTime start = LocalDateTime.now().plusDays(49);
        LocalDateTime end = LocalDateTime.now().plusDays(60);

        testList.add(incomTask0);
        testList.add(incomTask1);

        AbstractTaskList incomTasks = testList;

        testList.add(new Task("task", LocalDateTime.now().plusDays(40)));
        testList.add(new Task("task", LocalDateTime.now().plusDays(30)));

        Assertions.assertEquals(Tasks.incoming(testList,start,end), incomTasks);

    }

    @Test
    public void testSortedMap() {

        //Tasks.calendar();

    }

}
