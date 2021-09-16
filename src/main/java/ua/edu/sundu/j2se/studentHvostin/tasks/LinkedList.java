package ua.edu.sundu.j2se.studentHvostin.tasks;

public class LinkedList {

    private Task task;
    private LinkedList nextTask;
    private int index = 0;

    private LinkedList getNextTask() {
        return nextTask;
    }

    public int getIndex() {
        return this.index;
    }

    public int size() {
        if (this.task != null) {
            return this.nextTask.size() + 1;
        } else {
            return 0;
        }
    }

    public void setNextTask(LinkedList nextTask) {
        this.nextTask = nextTask;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Task getTask(final int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", index));
        } else if (index == this.index) {
            return this.task;
        } else if (this.task == null) {
            return null;
        } else {
            return this.nextTask.getTask(index);
        }
    }

    public Task getTask() {
        return this.task;
    }

    public void add(final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Impossible to add an empty task");
        }

        if (this.task == null) {
            this.task = task;
            this.nextTask = new LinkedList();
        } else {
            this.nextTask.add(task);
            this.nextTask.setIndex(this.index + 1);
        }
    }

    public boolean remove(final Task task) {
        boolean isRemoved = false;

        if (this.task == task) {
            this.task = this.nextTask.getTask();
            this.nextTask = this.nextTask.getNextTask();
            this.nextTask.reIndex(1);
            isRemoved = true;
        } else if (this.nextTask.getTask() == null) {
            return isRemoved;
        } else if (this.nextTask.getTask() == task) {
            this.setNextTask(this.nextTask.getNextTask());
            this.nextTask.reIndex(this.index + 1);
            isRemoved = true;
        } else {
            isRemoved = this.nextTask.remove(task);
        }

        return isRemoved;
    }

    private void reIndex (int index) {
        if (this.task != null) {
            this.nextTask.reIndex(this.index);
        }
        this.setIndex(index);
    }

    public LinkedList incoming(final int from, final int to) {
        LinkedList incomTasks = new LinkedList();

        if (this.task == null) {
            return incomTasks;
        } else {
            incomTasks = this.nextTask.incoming(from, to);
            if (from <= this.task.getEndTime() && this.task.getTime() <= to) {
                incomTasks.add(this.task);
            }
        }

        return incomTasks;
    }
}
