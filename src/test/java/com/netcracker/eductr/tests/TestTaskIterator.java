package com.netcracker.eductr.tests;

import ua.edu.sundu.j2se.studentHvostin.tasks.factory.ListTypes;
import ua.edu.sundu.j2se.studentHvostin.tasks.factory.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

import java.util.Iterator;

public class TestTaskIterator {

    @Test
    public void TestIt() {
        AbstractTaskList array = new TaskListFactory().createTaskList(ListTypes.ARRAY);
        Task testTask = new Task("task", 0, 48, 24);

        array.add(testTask);
        array.add(new Task("task", 1, 48, 24));
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
