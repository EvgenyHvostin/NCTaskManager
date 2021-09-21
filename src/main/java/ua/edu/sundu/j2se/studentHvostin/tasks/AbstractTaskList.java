package ua.edu.sundu.j2se.studentHvostin.tasks;

public abstract class AbstractTaskList {
    public abstract void add(final Task task);
    public abstract boolean remove(final Task task);
    public abstract int getSize();
    public abstract Task getTask(final int index);
    public abstract AbstractTaskList incoming(final int from, final int to);
}
