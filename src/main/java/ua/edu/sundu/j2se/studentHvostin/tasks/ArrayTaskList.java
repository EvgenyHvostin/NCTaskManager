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

    private Task[] tasks = new Task[1];

    void add(final Task task) {
        if (task != null) {
            this.tasks[this.tasks.length - 1] = task;
            resize(this.tasks.length + 1);
        }
    }

    public boolean remove(final Task task) {
        boolean contains = false;
        if (task == null) {
            return contains;
        } else {
            for (int i = 0; i < this.tasks.length; ++i) {
                if (this.tasks[i] == task) {
                    contains = true;
                    this.tasks[i] = this.tasks[this.tasks.length];
                    resize(this.tasks.length - 1);
                }
            }
            return contains;
        }
    }

    private void resize(int capacity) {
        Task[] copy = new Task[capacity];
        for (int i = 0; i < this.tasks.length & i < copy.length; i++) {
            copy[i] = this.tasks[i];
        }
        this.tasks = copy;
    }

    public int size() {
        return this.tasks.length;
    }

    public Task getTask(final int index) {
        if (index > this.tasks.length || tasks[index] == null) {
            return null;
        } else {
            return this.tasks[index];
        }
    }

    ArrayTaskList incoming(final int from, final int to) {
        ArrayTaskList incomTasks = new ArrayTaskList();

        for (int index = 0; index < this.tasks.length; index++) {
            if (tasks[index] == null) {
                break;
            } else {
                int time = tasks[index].getTime();
                if (from >= time & time <= to) {
                    incomTasks.add(tasks[index]);
                }
            }
        }
        return incomTasks;
    }

}
