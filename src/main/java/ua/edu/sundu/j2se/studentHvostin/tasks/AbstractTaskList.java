package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task> {
    public abstract void add(final Task task);
    public abstract boolean remove(final Task task);
    public abstract int getSize();
    public abstract Task getTask(final int index);
    public abstract AbstractTaskList incoming(final int from, final int to);

    @Override
    public Iterator<Task> iterator(){
        return new TaskIterator(this);
    }
}
