package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.io.Serializable;
import java.util.Iterator;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    public abstract void add(final Task task);
    public abstract boolean remove(final Task task);
    public abstract int getSize();
    public abstract Task getTask(final int index);
    public abstract AbstractTaskList clone();

    @Override
    public Iterator<Task> iterator(){
        return new TaskIterator(this);
    }
}
