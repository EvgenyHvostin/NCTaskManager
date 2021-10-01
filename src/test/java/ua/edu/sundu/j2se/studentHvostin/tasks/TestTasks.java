package ua.edu.sundu.j2se.studentHvostin.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.*;

public class TestTasks {

    @Test
    public void testIncoming() {

        ArrayTaskList testList = new ArrayTaskList();
        ArrayTaskList incomTasks;
        LocalDateTime time = LocalDateTime.of(1,1,1,1,1);

        LocalDateTime start = time.plusDays(15);
        LocalDateTime end = time.plusDays(20);

        Task incomTask0 = new Task("0", time.plusDays(15));
        Task incomTask1 = new Task("1", time.plusDays(10), time.plusDays(30), 10);

        testList.add(incomTask0);
        testList.add(incomTask1);

        incomTasks = testList.clone();

        testList.add(new Task("fail0", time.plusDays(14)));
        testList.add(new Task("fail1", time.plusDays(21)));
        testList.add(new Task("fail2", time.plusDays(10), time.plusDays(30), 20));

        Assertions.assertEquals(Tasks.incoming(testList,start,end), incomTasks);

    }

    @Test
    public void testCalendar() {

        ArrayTaskList testList = new ArrayTaskList();
        SortedMap<LocalDateTime, Set<String>> trueCalendar = new TreeMap<>();
        LocalDateTime time = LocalDateTime.of(1,1,1,1,1);

        LocalDateTime start = time.plusDays(10);
        LocalDateTime end = time.plusDays(30);

        testList.add(new Task("0", time.plusDays(10)));
        testList.add(new Task("1", time.plusDays(20), time.plusDays(40), 10));
        testList.add(new Task("fail0", time.plusDays(9)));
        testList.add(new Task("fail1", time.plusDays(31)));

        trueCalendar.put(time.plusDays(10), new TreeSet<String>(Collections.singleton("0")));
        trueCalendar.put(time.plusDays(20), new TreeSet<String>(Collections.singleton("1")));
        trueCalendar.put(time.plusDays(30), new TreeSet<String>(Collections.singleton("1")));

        Assertions.assertEquals(Tasks.calendar(testList,start,end), trueCalendar);

        //SortedMap<LocalDateTime, Set<String>> testCal = Tasks.calendar(testList,start,end);
        //System.out.println("Key : " + testCal.keySet() + "\nValue : " + testCal.values());

    }

}
