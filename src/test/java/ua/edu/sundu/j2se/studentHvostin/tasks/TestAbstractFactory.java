package ua.edu.sundu.j2se.studentHvostin.tasks;
import ua.edu.sundu.j2se.studentHvostin.tasks.factory.ListTypes;
import ua.edu.sundu.j2se.studentHvostin.tasks.factory.TaskListFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class TestAbstractFactory {

    @Test
    public void addArray() {
        AbstractTaskList array = new TaskListFactory().createTaskList(ListTypes.ARRAY);
        LocalDateTime time = LocalDateTime.of(0,0,0,0,0);
        Task testTask = new Task("task", time);

        array.add(testTask);

        Assertions.assertEquals(array.getTask(0), testTask);
    }

    @Test
    public void addLinked() {
        AbstractTaskList linked =new TaskListFactory().createTaskList(ListTypes.LINKED);
        LocalDateTime time = LocalDateTime.of(0,0,0,0,0);
        Task testTask = new Task("task", time);

        linked.add(testTask);

        Assertions.assertEquals(linked.getTask(0), testTask);

    }
}
