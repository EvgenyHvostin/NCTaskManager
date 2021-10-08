package ua.edu.sundu.j2se.studentHvostin.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.LinkedList;

import java.time.LocalDateTime;

public class TestLinkedList {

    @Test
    public void add() {
        LinkedList link = new LinkedList();
        LocalDateTime time = LocalDateTime.of(1,1,1,1,1);

        link.add(new Task("task0", time));
        link.add(new Task("task1", time));

        Assertions.assertEquals(link.getTask(0).getTitle(), "task0");
        Assertions.assertEquals(link.getTask(1).getTitle(), "task1");
        Assertions.assertEquals(link.getSize(), 2);

    }

    @Test
    public void remove() {
        LinkedList link = new LinkedList();
        LocalDateTime time = LocalDateTime.of(1,1,1,1,1);
        Task remTask = new Task("remTask", time);

        link.add(remTask);
        link.remove(remTask);
        Assertions.assertEquals(link.getSize(), 0);

        link.add(new Task("task0", time));
        link.add(remTask);
        link.add(new Task("task2", time));
        link.add(remTask);

        link.remove(remTask);

        Assertions.assertEquals(link.getSize(), 3);
        Assertions.assertEquals(link.getTask(0).getTitle(), "task0");
        Assertions.assertEquals(link.getTask(1).getTitle(), "task2");
        Assertions.assertEquals(link.getTask(2).getTitle(), "remTask");

    }
/*
    @Test
    public void incoming() {
        LinkedList testArray = new LinkedList();
        LinkedList incomTasks = new LinkedList();

        Task incomTask0 = new Task("task0", 50);
        Task incomTask1 = new Task("task1", 40, 80 ,10);

        testArray.add(incomTask0);
        testArray.add(incomTask1);

        testArray.add(new Task("task", 40));
        testArray.add(new Task("task", 30));
        
        Assertions.assertEquals(testArray.incoming(49,60).getTask(0), incomTask0);
        Assertions.assertEquals(testArray.incoming(49,60).getTask(1), incomTask1);
    }
 */
}
