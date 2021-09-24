package ua.edu.sundu.j2se.studentHvostin.tasks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class TestTask {

    @Test
    public void getTask() {
        String trueTitle = "task";
        LocalDateTime trueTime = LocalDateTime.now().plusDays(10);
        LocalDateTime trueStart = LocalDateTime.now().plusDays(10);
        LocalDateTime trueEnd = LocalDateTime.now().plusDays(20);
        LocalDateTime trueInterval = LocalDateTime.of(0,0,3,0,0);
        Task testTask;

        testTask = new Task(trueTitle, trueTime);
        String title = testTask.getTitle();
        LocalDateTime time = testTask.getTime();

        Assertions.assertEquals(title, trueTitle);
        Assertions.assertEquals(time, trueTime);
        Assertions.assertFalse(testTask.isRepeated());

        testTask = new Task(trueTitle, trueStart, trueEnd, trueInterval);
        title = testTask.getTitle();
        LocalDateTime start = testTask.getStartTime();
        LocalDateTime end = testTask.getEndTime();
        LocalDateTime interval = testTask.getRepeatInterval();

        Assertions.assertEquals(title, trueTitle);
        Assertions.assertEquals(start, trueStart);
        Assertions.assertEquals(end, trueEnd);
        Assertions.assertEquals(interval, trueInterval);
        Assertions.assertTrue(testTask.isRepeated());
    }

    @Test
    public void setTask() {
        Task t = new Task("task", LocalDateTime.of(0,0,0,0,0));
        LocalDateTime trueTime = LocalDateTime.now().plusDays(10);
        LocalDateTime trueStart = LocalDateTime.now().plusDays(10);
        LocalDateTime trueEnd = LocalDateTime.now().plusDays(20);
        LocalDateTime trueInterval = LocalDateTime.of(0,0,3,0,0);

        t.setTime(trueTime);
        Assertions.assertFalse(t.isRepeated());
        Assertions.assertEquals(t.getTime(), trueTime);

        t.setTime(trueStart, trueEnd, trueInterval);
        Assertions.assertTrue(t.isRepeated());
        Assertions.assertEquals(t.getStartTime(), trueStart);
        Assertions.assertEquals(t.getEndTime(), trueEnd);
        Assertions.assertEquals(t.getRepeatInterval(), trueInterval);

        Assertions.assertFalse(t.isActive());
        t.setActive(true);
        Assertions.assertTrue(t.isActive());
    }

    @Test
    public void nextTimeAfter() {
        LocalDateTime current = LocalDateTime.now();
        Task t = new Task("task", current.plusDays(3));

        Assertions.assertEquals(t.nextTimeAfter(), current.plusDays(3));
    }

}
