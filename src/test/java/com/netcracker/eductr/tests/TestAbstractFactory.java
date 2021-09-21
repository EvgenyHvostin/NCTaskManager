package com.netcracker.eductr.tests;

import Factory.ListTypes;
import Factory.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ua.edu.sundu.j2se.studentHvostin.tasks.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

public class TestAbstractFactory {
    @Test
    public void addArray() {
        AbstractTaskList array = new TaskListFactory().createTaskList(ListTypes.ARRAY);
        Task testTask = new Task("task", 0, 48, 24);

        array.add(testTask);

        Assertions.assertEquals(array.getTask(0), testTask);

    }

    @Test
    public void addLinked() {
        AbstractTaskList linked =new TaskListFactory().createTaskList(ListTypes.LINKED);
        Task testTask = new Task("task", 0, 48, 24);

        linked.add(testTask);

        Assertions.assertEquals(linked.getTask(0), testTask);

    }
}
