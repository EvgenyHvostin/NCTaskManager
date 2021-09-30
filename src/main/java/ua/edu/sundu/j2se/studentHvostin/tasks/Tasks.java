package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.time.LocalDateTime;
import java.util.*;


public class Tasks {

    public static Iterable<Task> incoming(
            final Iterable<Task> tasks, final LocalDateTime start, final LocalDateTime end) {

        Iterator<Task> incomTaks = tasks.iterator();
        Task task;

        while (incomTaks.hasNext()) {
            task = incomTaks.next();
            if (!start.isBefore(task.getEndTime()) && !task.getStartTime().isBefore(end)) {
                incomTaks.remove();
            }
        }
        return tasks;

    }

    public static SortedMap<LocalDateTime, Set<String>> calendar(
            final Iterable<Task> tasks, final LocalDateTime start, final LocalDateTime end) {

        SortedMap<LocalDateTime, Set<String>> calendar = new TreeMap<>();
        Iterator<Task> iter = tasks.iterator();
        Task task;

        while (iter.hasNext()) {

            task = iter.next();
            if (task.isRepeated()) {
                if (start.isBefore(task.getEndTime()) && task.getStartTime().isBefore(end)) {

                    LocalDateTime time = task.nextTimeAfter(start);
                    final Set<String> set = new TreeSet<>();

                    set.add(task.getTitle());
                    while (time.isBefore(task.getEndTime())) {
                        calendar.put(time, set);
                        time = time.plusDays(task.getRepeatInterval());
                    }
                }
            } else {

                if (start.isBefore(task.getTime()) && task.getTime().isBefore(end)) {
                    final Set<String> set = new TreeSet<>();
                    set.add(task.getTitle());
                    calendar.put(task.getTime(), set);
                }
            }
        }

        return calendar;
    }
}
