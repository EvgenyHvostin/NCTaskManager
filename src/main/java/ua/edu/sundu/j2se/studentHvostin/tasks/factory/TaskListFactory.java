package ua.edu.sundu.j2se.studentHvostin.tasks.factory;

import ua.edu.sundu.j2se.studentHvostin.tasks.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.ArrayTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.LinkedList;

public class TaskListFactory {

    public AbstractTaskList createTaskList(ListTypes type) {
        AbstractTaskList taskList = null;

        switch (type) {
            case ARRAY:
                taskList = new ArrayTaskList();
                break;
            case LINKED:
                taskList = new LinkedList();
                break;
        }

        return taskList;
    }
}
