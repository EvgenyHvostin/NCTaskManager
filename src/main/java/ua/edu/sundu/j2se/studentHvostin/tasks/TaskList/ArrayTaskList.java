package ua.edu.sundu.j2se.studentHvostin.tasks.TaskList;

import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private Task[] tasks = new Task[8];

    public void add(final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Impossible to add an empty task");
        }
        for (int i = 0; i < this.tasks.length; ++i) {
            if (this.tasks[i] == null) {
                this.tasks[i] = task;
                break;
            }
        }
        this.reSize();
    }

    public boolean remove(final Task task) {
        boolean isRemoved = false;
        if (task == null) {
            return isRemoved;
        } else {
            for (int i = 0; i < this.tasks.length - 1; ++i) {
                if (this.tasks[i] == task || isRemoved) {
                    isRemoved = true;
                    this.tasks[i] = this.tasks[i + 1];
                }
            }
            this.reSize();
            return isRemoved;
        }
    }

    private void reSize() {
        int capacity = 0;
        int size = this.getSize();

        if (this.tasks.length - size < 2) {
            capacity = this.tasks.length + 8;
        } else if (this.tasks.length - size > 16) {
            capacity = this.tasks.length - 8;
        }

        if (capacity > 0) {
            Task[] copy = new Task[capacity];
            for (int j = 0; j <= this.tasks.length - 1 && j <= copy.length - 1; j++) {
                copy[j] = this.tasks[j];
            }
            this.tasks = copy;
        }
    }

    public int getSize() {
        int i = 0;
        while (i == this.tasks.length - 2 || this.tasks[i] != null) {
            i++;
        }
        return i;
    }

    public Task getTask(final int index) {
        if (index < 0 || index >= this.getSize()) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", index));
        } else {
            return this.tasks[index];
        }
    }

    @Override
    public int hashCode() {
        int index = 0;
        int result = 1;

        while (index < this.getSize()) {
            result *= this.getTask(index).hashCode();
            ++index;
        }

        return result;
    }

    @Override
    public boolean equals(final Object taskList) {
        if (taskList == null)
            return false;
        if (this == taskList)
            return true;
        if (getClass() != taskList.getClass())
            return false;

        ArrayTaskList other = (ArrayTaskList) taskList;
        Iterator<Task> otherIter = other.iterator();

        for (Task task : this) {
            if (!task.equals(otherIter.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("(");
        Iterator<Task> iter = this.iterator();

        while (iter.hasNext()) {
            result.append(iter.next().toString()).append(", ");
        }
        return result + ")";
    }

    @Override
    public ArrayTaskList clone() {
        ArrayTaskList newTaskList = new ArrayTaskList();

        for (Task task : this) {
            newTaskList.add(task.clone());
        }
        return newTaskList;
    }

    public Stream<Task> getStream() {
        Stream.Builder<Task> streamBuiderTask = Stream.<Task>builder();

        for (int index = 0; index < this.getSize() ; ++index) {
            streamBuiderTask.accept(tasks[index]);
        }
        return streamBuiderTask.build();
    }
}
