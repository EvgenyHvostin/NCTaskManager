package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Tasks {
    public static Iterable<Task> incoming(
            final Iterable<Task> tasks,final  LocalDateTime start,final  LocalDateTime end) {

        Iterator<Task> incomTaks = tasks.iterator();
        Task task;

        while (incomTaks.hasNext()) {
            task = incomTaks.next();
            if (!start.isBefore(task.getEndTime()) && !task.getTime().isBefore(end)) {
                incomTaks.remove();
            }
        }
        return tasks;
    }

    public static SortedMap<LocalDateTime, Set<Task>> calendar(
            final Iterable<Task> tasks, final LocalDateTime start, final LocalDateTime end) {

        Iterator<Task> incomTaks = tasks.iterator();
        Task task;
        SortedMap<LocalDateTime, Set<Task>> calendar = new TreeMap<>();
        Set<Task> keys = null;

        while (incomTaks.hasNext()) {
            task = incomTaks.next();
            if (!start.isBefore(task.getEndTime()) && !task.getTime().isBefore(end)) {
                keys.add(task);
                calendar.put(task.getTime(), keys);
            }
        }

        return calendar;
    }
}
