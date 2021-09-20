package com.netcracker.eductr.tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.LinkedList;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

public class TestLinkedList {

    @Test
    public void add() {
        LinkedList link = new LinkedList();

        link.add(new Task("task0", 10, 40, 5));
        link.add(new Task("task1", 20, 50, 10));
        link.add(new Task("task2", 30, 60, 20));

        Assertions.assertEquals(link.getTask(0).getTitle(), "task0");
        Assertions.assertEquals(link.getTask(1).getTitle(), "task1");
        Assertions.assertEquals(link.getTask(2).getTitle(), "task2");
    }

    @Test
    public void size() {
        LinkedList link = new LinkedList();

        link.add(new Task("1", 1));
        link.add(new Task("2", 2));
        link.add(new Task("3", 3));

        Assertions.assertEquals(link.getSize(), 3);
    }

    @Test
    public void getTask() {
        LinkedList link = new LinkedList();

        link.add(new Task("1", 1));
        link.add(new Task("2", 2));
        link.add(new Task("3", 3));


        Assertions.assertEquals(link.getTask(1).getTitle(), "2");
        Assertions.assertEquals(link.getTask(2).getTitle(), "3");
    }

    @Test
    public void remove() {
        LinkedList link = new LinkedList();

        Task remTask = new Task("remTask", 20, 50, 10);

        link.add(new Task("task0", 10, 40, 5));
        link.add(remTask);
        link.add(new Task("task2", 30, 60, 20));
        link.add(remTask);

        link.remove(remTask);

        Assertions.assertEquals(link.getSize(), 3);
        Assertions.assertEquals(link.getTask(0).getTitle(), "task0");
        Assertions.assertEquals(link.getTask(1).getTitle(), "task2");
        Assertions.assertEquals(link.getTask(2).getTitle(), "remTask");

    }

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
}
