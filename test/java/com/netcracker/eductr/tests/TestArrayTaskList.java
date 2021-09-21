package com.netcracker.eductr.tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.ArrayTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

public class TestArrayTaskList {

    @Test
    public void add() {
        ArrayTaskList arr = new ArrayTaskList();
        Task testTask = new Task("task", 0, 48, 24);

        arr.add(testTask);

        Assertions.assertEquals(arr.getTask(0), testTask);
    }

    @Test
    public void getTask() {
        ArrayTaskList arr = new ArrayTaskList();

        Task task0 = new Task("task0", 0, 40, 10);
        Task task1 = new Task("task1", 1, 41, 11);

        arr.add(task0);
        arr.add(task1);

        Assertions.assertEquals(arr.getTask(0), task0);
        Assertions.assertEquals(arr.getTask(1), task1);
    }

    @Test
    public void size() {
        ArrayTaskList arr = new ArrayTaskList();

        Task task = new Task("task", 1);

        arr.add(task);
        arr.add(task);

        Assertions.assertEquals(arr.size(), 2);

        arr.add(task);

        Assertions.assertEquals(arr.size(), 3);
    }

    @Test
    public void remove() {
        ArrayTaskList arr = new ArrayTaskList();

        Task task0 = new Task("task", 0);
        Task task1 = new Task("task", 1);

        arr.add(task0);
        arr.add(new Task("task", 10));
        arr.add(task1);
        arr.add(new Task("task", 20));

        arr.remove(task0);

        Assertions.assertEquals(arr.size(), 3);
        Assertions.assertEquals(arr.getTask(1), task1);
    }

    @Test
    public void incoming() {
        ArrayTaskList testArray = new ArrayTaskList();
        ArrayTaskList incomTasks = new ArrayTaskList();

        Task incomTask0 = new Task("task0", 50);
        Task incomTask1 = new Task("task1", 40, 80 ,10);

        testArray.add(incomTask0);
        testArray.add(incomTask1);

        incomTasks = testArray;

        testArray.add(new Task("task", 40));
        testArray.add(new Task("task", 30));

        Assertions.assertEquals(testArray.incoming(49,60).getTask(1).getTime(), 40);
        //Assertions.assertEquals(testArray.incoming(49,60).getTask(0), incomTask1);
    }
}
