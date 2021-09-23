package ua.edu.sundu.j2se.studentHvostin.tasks;

public class LinkedList extends AbstractTaskList {

    private static class Node {
        private Task task;
        private Node next;

        private Node(final Task element) {
            this.task = element;
            this.next = null;
        }

        private void setTask(final Task task) {
            this.task = task;
        }

        public Task getTask() {
            return this.task;
        }

        private void setNext(final Node next) {
            this.next = next;
        }

        private Node getNext() {
            return this.next;
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void add(final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Impossible to add an empty task");
        }
        Node current = this.head;

        if (current == null) {
            this.head = new Node(task);
        } else {
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node(task));
        }
        this.size++;

    }

    public boolean remove(final Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Impossible to remove an empty task");
        } else if (size == 0) {
            return false;
        }
        boolean isRemoved = false;
        Node previous = null;
        Node current = this.head;

        while (current != null) {
            if (current.getTask() == task) {
                if (previous == null) {
                    this.head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                --this.size;
                isRemoved = true;
                break;
            }
            previous = current;
            current = current.getNext();
        }
        return isRemoved;
    }

    public Task getTask(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", index));
        } else {
            Node current = this.head;

            while (index != 0) {
                --index;
                current = current.getNext();
            }

            return current.getTask();
        }
    }

    public LinkedList incoming(final int from, final int to) {
        if (from < 0) {
            throw new IndexOutOfBoundsException(String.format("start time %s doesn't is invalid", from));
        } else if (to < 0) {
            throw new IndexOutOfBoundsException(String.format("end time %s doesn't is invalid", to));
        }

        LinkedList incomTasks = new LinkedList();
        Node current = this.head;

        while (current != null) {
            if (from <= current.getTask().getEndTime() && current.getTask().getTime() <= to) {
                incomTasks.add(current.getTask());
            }
            current = current.getNext();
        }
        return incomTasks;
    }

    @Override
    public int hashCode() {
        int index = 0;
        Task task;
        int result = 1;

        Node current = this.head;

        while (index < this.getSize()) {
            task = current.getTask();
            result *= task.getStartTime() + task.getEndTime() + task.getRepeatInterval();
            current = current.getNext();
            ++index;
        }

        return result;
    }

    @Override
    public boolean equals(Object taskList) {
        if (taskList == null)
            return false;
        if (this == taskList)
            return true;
        if (getClass() != taskList.getClass())
            return false;
        /*
        LinkedList other = (LinkedList) taskList;
        int index = 0;

        while (index < this.getSize()) {
            if (!this.getTask(index).equals(other.getTask(index))) {
                return false;
            }
            ++index;
        }
        return true;
         */

        LinkedList other = (LinkedList) taskList;

        while (this.iterator().hasNext()) {
            if (!this.iterator().next().equals(other.iterator().next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("(" + this.getTask(0).toString() + ", ");

        while (this.iterator().hasNext()) {
            result.append(this.iterator().next().toString()).append(", ");
        }
        return result + ")";
    }

}
