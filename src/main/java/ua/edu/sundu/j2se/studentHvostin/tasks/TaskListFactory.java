package ua.edu.sundu.j2se.studentHvostin.tasks;

public class TaskListFactory {
    public AbstractTaskList createTaskList(ListTypes type) {
        AbstractTaskList TaskList = null;

        switch (type) {
            case ARRAY:
                TaskList = new ArrayTaskList();
                break;
            case LINKED:
                TaskList = new LinkedList();
                break;
        }

        return TaskList;
    }
}
