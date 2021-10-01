package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.util.Iterator;

public class TaskIterator implements Iterator<Task> {

    private AbstractTaskList taskList;
    private int index = 0;

    public TaskIterator (final AbstractTaskList taskList) {
        if (taskList == null) {
            throw new IllegalArgumentException("Empty task list");
        }
        this.taskList = taskList;
    }

    @Override
    public boolean hasNext() {
        return this.index < this.taskList.getSize();
    }

    @Override
    public Task next() {
        if (!this.hasNext()) {
            throw new IllegalArgumentException("No more elements");
        }
        return this.taskList.getTask(this.index++);
    }

    @Override
    public void remove() {
        this.taskList.remove(this.taskList.getTask(--this.index));

    }
}
