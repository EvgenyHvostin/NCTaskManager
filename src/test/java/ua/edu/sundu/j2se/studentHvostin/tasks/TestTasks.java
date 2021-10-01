package ua.edu.sundu.j2se.studentHvostin.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class TestTasks {

    @Test
    public void testIncoming() {

        ArrayTaskList testList = new ArrayTaskList();
        ArrayTaskList  incomTasks;

        LocalDateTime start = LocalDateTime.of(1,1,15,1,1);
        LocalDateTime end = LocalDateTime.of(1,1,20,1,1);

        Task incomTask0 = new Task("0", LocalDateTime.of(1,1,15,1,1));
        Task incomTask1 = new Task("1",
                LocalDateTime.of(1,1,10,1,1),
                LocalDateTime.of(1,1,30,1,1), 10);


        testList.add(incomTask0);
        testList.add(incomTask1);

        incomTasks = testList.clone();

        testList.add(new Task("task0", LocalDateTime.of(1,1,14,1,1)));
        testList.add(new Task("task1", LocalDateTime.of(1,1,21,1,1)));
        testList.add(new Task("task2",
                LocalDateTime.of(1,1,10,1,1),
                LocalDateTime.of(1,1,30,1,1), 20));

        Assertions.assertEquals(Tasks.incoming(testList,start,end), incomTasks);

    }
/*
    @Test
    public void testCalendar() {

        AbstractTaskList testList = new ArrayTaskList();
        Task incomTask0 = new Task("0", LocalDateTime.now().plusDays(50));
        Task incomTask1 = new Task("1",
                LocalDateTime.now().plusDays(40),
                LocalDateTime.now().plusDays(80), 10);
        LocalDateTime start = LocalDateTime.now().plusDays(49);
        LocalDateTime end = LocalDateTime.now().plusDays(60);

        testList.add(incomTask0);
        testList.add(incomTask0);
        testList.add(incomTask0);
        testList.add(incomTask0);
        testList.add(incomTask1);

        testList.add(new Task("task1", LocalDateTime.now().plusDays(40)));
        testList.add(new Task("task2", LocalDateTime.now().plusDays(30)));

        SortedMap<LocalDateTime, Set<String>> testCal = Tasks.calendar(testList,start,end);

        System.out.println("Key : " + testCal.keySet() + "\nValue : " + testCal.values());

    }

     */

}
