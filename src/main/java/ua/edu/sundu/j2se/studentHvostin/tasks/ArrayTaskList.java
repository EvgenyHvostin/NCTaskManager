package ua.edu.sundu.j2se.studentHvostin.tasks;

/**
 * void add (Task task) - это метод, который добавляет указанную задачу в список.
 * boolean remove (Task task) - это метод, который удаляет задачу из списка и возвращает true,
 * если такая задача была в списке. Если в списке несколько задач одного типа, любую из них следует удалить.
 * int size()- это метод, который возвращает количество задач списка.
 * Task getTask(int index) - метод, возвращающий задачу, занимающую указанное место в списке;
 * индекс первой задачи 0.
 * <p>
 * Кроме того, приложение должно знать,
 * какие задачи из списка запланированы хотя бы один раз в определенном интервале,
 * например, какие задачи запланированы на следующую неделю. Чтобы реализовать это,
 * создайте the ArrayTaskList incoming(int from, int to) method in the ArrayTaskList class.
 * Этот метод возвращает подмножество задач, которые запланированы для выполнения
 * хотя бы один раз после the "from" время, и не позднее "to" time.
 */

public class ArrayTaskList {

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
        int size = this.size();

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

    public int size() {
        int i = 0;
        while (this.tasks[i] != null || i == this.tasks.length - 1) {
            i++;
        }
        return i;
    }

    public Task getTask(final int index) {
        if (index >= this.tasks.length || tasks[index] == null) {
            throw new IndexOutOfBoundsException(String.format("The Task with index %s doesn't exist", index));
        } else {
            return this.tasks[index];
        }
    }

    public ArrayTaskList incoming(final int from, final int to) {
        ArrayTaskList incomTasks = new ArrayTaskList();

        for (int index = 0; index <= this.size(); index++) {
            if (this.tasks[index] == null) {
                break;
            } else {
                if (from <= this.tasks[index].getEndTime() && this.tasks[index].getTime() <= to) {
                    incomTasks.add(this.tasks[index]);
                }
            }
        }
        return incomTasks;
    }

}
