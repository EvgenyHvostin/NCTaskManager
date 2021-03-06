package ua.edu.sundu.j2se.studentHvostin.tasks.Services;

import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

import java.time.LocalDateTime;
import java.util.*;

public class Tasks {

    public static Iterable<Task> incoming(
            final Iterable<Task> tasks, final LocalDateTime start, final LocalDateTime end) {

        Iterator<Task> incomingTask = tasks.iterator();
        Task task;

        while (incomingTask.hasNext()) {
            task = incomingTask.next();

            if (task.isRepeated()) {
                if (task.getEndTime().isBefore(start) || task.getStartTime().isAfter(end)) {
                    incomingTask.remove();
                } else {
                    if (task.nextTimeAfter(start).isAfter(end)) {
                        incomingTask.remove();
                    }
                }
            } else {
                if (task.getTime().isBefore(start) || task.getTime().isAfter(end)) {
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
            LocalDateTime time;

            if (task.isRepeated()) {
                time = task.nextTimeAfter(start);

                while ( ! time.isAfter(end)) {
                    if (calendar.containsKey(time)) {
                        final Set<String> set = calendar.get(time);

                        set.add(task.getTitle());
                    } else {
                        final Set<String> set = new TreeSet<>();

                        set.add(task.getTitle());
                        calendar.put(time, set);
                    }
                    time = time.plusDays(task.getRepeatInterval());
                }
            } else {
                time = task.getTime();

                if ( ! (time.isBefore(start) || time.isAfter(end))) {
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
