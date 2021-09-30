package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.time.LocalDateTime;
import java.util.*;


public class Tasks {

    public static Iterable<Task> incoming(
            final Iterable<Task> tasks, final LocalDateTime start, final LocalDateTime end) {

        Iterator<Task> incomingTask = tasks.iterator();
        Task task;

        while (incomingTask.hasNext()) {
            task = incomingTask.next();
            if (!start.isBefore(task.getEndTime()) && !task.getStartTime().isBefore(end)) {
                if (task.isRepeated()) {
                    LocalDateTime time = task.nextTimeAfter(start);

                    while (time.isBefore(end)) {
                        if (time.isAfter(start)) {
                            incomingTask.remove();
                            break;
                        }
                        time = task.nextTimeAfter(time);
                    }
                } else {
                    incomingTask.remove();
                }
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

            if (start.isBefore(task.getEndTime()) && task.getStartTime().isBefore(end)) {
                if (task.isRepeated()) {
                    if (task.nextTimeAfter(start).isBefore(end)) {
                        LocalDateTime time = task.nextTimeAfter(start);

                        while (time.isBefore(task.getEndTime())) {
                            if (calendar.containsKey(task.getTime())) {
                                final Set<String> set = calendar.get(task.getTime());

                                set.add(task.getTitle());
                            } else {
                                final Set<String> set = new TreeSet<>();

                                set.add(task.getTitle());
                                calendar.put(task.getTime(), set);
                            }
                            time = task.nextTimeAfter(time);
                        }
                    }
                } else {
                    if (calendar.containsKey(task.getTime())) {
                        final Set<String> set = calendar.get(task.getTime());
                        set.add(task.getTitle());
                    } else {
                        final Set<String> set = new TreeSet<>();
                        set.add(task.getTitle());
                        calendar.put(task.getTime(), set);
                    }
                }
            }
        }
        return calendar;

    }

}
