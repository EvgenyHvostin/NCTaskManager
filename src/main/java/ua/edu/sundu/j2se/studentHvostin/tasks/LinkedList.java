package ua.edu.sundu.j2se.studentHvostin.tasks;

public class LinkedList {

    private static class Node<E> {
        private Task task;
        private Node<Task> next;

        Node(Task element) {
            this.task = element;
            this.next = null;
        }

        public void setTask(Task task) {
            this.task = task;
        }

        public Task getTask() {
            return this.task;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
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
            this.size++;
        }
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(new Node(task));
        this.size++;
    }

    public boolean remove(final Task task) {

        boolean isRemoved = false;

        if (head.getTask() == task) {
            if (this.size < 2) {
                this.head.setTask(task);
                this.size++;
                isRemoved = true;
            } else {
                this.head.setTask(task);
                this.head = this.head.getNext();
                this.size++;
                isRemoved = true;
            }
        } else {

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
                }
                previous = current;
                current = current.getNext();
            }
        }
        return isRemoved;
    }

    public Task getTask(int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", index));
        } else {
            Node current = this.head;

            while (index != 0 || current != null) {
                --index;
                current = current.getNext();
            }

            return current.getTask();
        }
    }

    public LinkedList incoming(final int from, final int to) {
        if (from < 0) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", from));
        } else if (to < 0) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", to));
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
}
