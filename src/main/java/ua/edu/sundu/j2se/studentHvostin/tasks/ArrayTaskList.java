package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.util.ArrayList;

public class ArrayTaskList {

    /*
    void add (Task task) - это метод, который добавляет указанную задачу в список.
    boolean remove (Task task) - это метод, который удаляет задачу из списка и возвращает true,
    если такая задача была в списке. Если в списке несколько задач одного типа, любую из них следует удалить.
    int size()- это метод, который возвращает несколько задач из списка.
    Task getTask(int index) - метод, возвращающий задачу, занимающую указанное место в списке; индекс первой задачи 0.

     Кроме того, приложение должно знать,
     какие задачи из списка запланированы хотя бы один раз в определенном интервале,
     например, какие задачи запланированы на следующую неделю. Чтобы реализовать это,
     создайте the ArrayTaskList incoming(int from, int to) method in the ArrayTaskList class.
     Этот метод возвращает подмножество задач, которые запланированы для выполнения
     хотя бы один раз после the "from" время, и не позднее "to" time.
     */

    private ArrayList<Task> Tasks = new ArrayList<Task>();

    void add (final Task task) {
        if( task.getRepeatInterval() > 0 ) {
            this.Tasks.add(new Task(
                    task.getTitle(),
                    task.getStartTime(),
                    task.getEndTime(),
                    task.getRepeatInterval()));
        } else {
            this.Tasks.add(new Task(
                    task.getTitle(),
                    task.getTime()));
        }
    }

    public boolean remove (final Task task) {
        if (task != null) {
            this.Tasks.remove(task);
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return Tasks.size();
    }

    public Task getTask(final int index) {
        return Tasks.get(index);
    }

    ArrayList<Task> incoming(final int from, final int to) {
        ArrayList<Task> incomTasks = new ArrayList<Task>();
        for (int index = 0; index < Tasks.size(); index += 1) {
            Task task = getTask(index);
            int time = task.getTime();
            if (from <= time & time <= to) {
                incomTasks.add(task);
            }
        }
        return incomTasks;
    }

}
