package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable {
    public abstract void add(final Task task);
    public abstract boolean remove(final Task task);
    public abstract int getSize();
    public abstract Task getTask(final int index);
    public abstract Stream<Task> getStream();

    public final AbstractTaskList incoming(final int from, final int to) {
        if (from < 0) {
            throw new IndexOutOfBoundsException(String.format("start time %s is invalid", from));
        } else if (to < 0) {
            throw new IndexOutOfBoundsException(String.format("end time %s is invalid", to));
        }
        AbstractTaskList incomTasks = new ArrayTaskList();

        getStream()
                .filter(task -> from <= task.getEndTime() && task.getTime() <= to)
                .forEach(task -> incomTasks.add(task));

        return incomTasks;
    }

    @Override
    public Iterator<Task> iterator(){
        return new TaskIterator(this);
    }
}
