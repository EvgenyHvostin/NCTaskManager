package ua.edu.sundu.j2se.studentHvostin.tasks;

public class LinkedList {

    private static class Node {
        private Task task;
        private Node next;

        Node(final Task element) {
            this.task = element;
            this.next = null;
        }

        public void setTask(final Task task) {
            this.task = task;
        }

        public Task getTask() {
            return this.task;
        }

        public void setNext(final Node next) {
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

        if (head.getTask() == task) {
            if (this.size == 1) {
                this.head.setTask(null);
                this.size = 0;
            } else {
                this.head = this.head.getNext();
                this.size--;
            }
            isRemoved = true;
        } else {
            Node previous = null;
            Node current = this.head;

            while (current != null) {
                if (current.getTask() == task) {
                    if (previous == null) {
                        this.head = current.getNext();
                        --this.size;
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
}
