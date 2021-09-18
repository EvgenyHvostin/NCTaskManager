package com.netcracker.eductr.tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

public class TestTask {

    @Test
    public void getTask() {

        String trueTitle = "task";
        int trueTime = 21;
        int trueStart = 20;
        int trueEnd = 40;
        int trueInterval = 5;
        Task testTask;

        testTask = new Task(trueTitle, trueTime);
        String title = testTask.getTitle();
        int time = testTask.getTime();

        Assertions.assertEquals(title, trueTitle);
        Assertions.assertEquals(time, trueTime);
        Assertions.assertFalse(testTask.isRepeated());

        testTask = new Task(trueTitle, trueStart, trueEnd, trueInterval);
        title = testTask.getTitle();
        int start = testTask.getStartTime();
        int end = testTask.getEndTime();
        int interval = testTask.getRepeatInterval();

        Assertions.assertEquals(title, trueTitle);
        Assertions.assertEquals(start, trueStart);
        Assertions.assertEquals(end, trueEnd);
        Assertions.assertEquals(interval, trueInterval);
        Assertions.assertTrue(testTask.isRepeated());
    }

    @Test
    public void setTask() {
        Task t = new Task("task", 1, 2, 1); //test task

        t.setTime(25);
        Assertions.assertFalse(t.isRepeated());
        Assertions.assertEquals(t.getTime(), 25);

        t.setTime(20, 40, 5);
        Assertions.assertTrue(t.isRepeated());
        Assertions.assertEquals(t.getStartTime(), 20);
        Assertions.assertEquals(t.getEndTime(), 40);
        Assertions.assertEquals(t.getRepeatInterval(), 5);

        Assertions.assertFalse(t.isActive());
        t.setActive(true);
        Assertions.assertTrue(t.isActive());
    }

    @Test
    public void nextTimeAfter() {
        Task t = new Task("task", 50, 100, 10); //test task

        Assertions.assertEquals(t.nextTimeAfter(49), 50);
        Assertions.assertEquals(t.nextTimeAfter(51), 60);
        Assertions.assertEquals(t.nextTimeAfter(101), -1);
    }
}
