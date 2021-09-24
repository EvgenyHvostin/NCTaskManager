package ua.edu.sundu.j2se.studentHvostin.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class TestArrayTaskList {


    @Test
    public void add() {
        ArrayTaskList array = new ArrayTaskList();
        LocalDateTime time = LocalDateTime.of(0,0,0,0,0);

        array.add(new Task("task0", time));
        array.add(new Task("task1", time));

        Assertions.assertEquals(array.getTask(0).getTitle(), "task0");
        Assertions.assertEquals(array.getTask(1).getTitle(), "task1");
        Assertions.assertEquals(array.getSize(), 2);

    }

    @Test
    public void remove() {
        ArrayTaskList array = new ArrayTaskList();
        LocalDateTime time = LocalDateTime.of(0,0,0,0,0);
        Task remTask = new Task("remTask", time);

        array.add(remTask);
        array.remove(remTask);
        Assertions.assertEquals(array.getSize(), 0);

        array.add(new Task("task0", time));
        array.add(remTask);
        array.add(new Task("task2", time));
        array.add(remTask);

        array.remove(remTask);

        Assertions.assertEquals(array.getSize(), 3);
        Assertions.assertEquals(array.getTask(0).getTitle(), "task0");
        Assertions.assertEquals(array.getTask(1).getTitle(), "task2");
        Assertions.assertEquals(array.getTask(2).getTitle(), "remTask");

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
