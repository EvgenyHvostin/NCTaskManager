package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import ua.edu.sundu.j2se.studentHvostin.tasks.Services.TaskIO;
import ua.edu.sundu.j2se.studentHvostin.tasks.Services.Tasks;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.ArrayTaskList;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

public class ModelTaskManager {

    private AbstractTaskList list;
    private File file;

    public ModelTaskManager () {
        this.list = new ArrayTaskList();
        this.file = new File("saves.out");
    }

    public AbstractTaskList getList() {
        return list;
    }

    public Task getTask(final int index) {
        return list.getTask(index);
    }

    public void addTask(final Task task) {
        list.add(task);
    }

    public void removeTask(final int index) {
        list.remove(list.getTask(index));
    }

    public void removeTask(final Task task) {
        list.remove(task);
    }

    public void editTask(final int index,final  boolean active,final  String title,
                         final LocalDateTime start,final  LocalDateTime end,
                         final int interval) {

        Task task = this.list.getTask(index);
        task.setTitle(title);
        task.setActive(active);
        if (interval == 0) {
            task.setTime(start);
        } else {
            task.setTime(start, end, interval);
        }
    }

    public ArrayList<String> calendar(final LocalDateTime start, final LocalDateTime end) {
        ArrayList<String> allElements = new ArrayList<String>();
        //SortedMap<LocalDateTime, Set<String>> map = Tasks.calendar(list, start, end);
        Iterator<Map.Entry<LocalDateTime, Set<String>>> it = Tasks.calendar(list, start, end).entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<LocalDateTime, Set<String>> pairs = it.next();
            allElements.add(pairs.getKey() + " " + pairs.getValue());
        }
        return allElements;
    }

    public void saveFile(File file) {
        TaskIO.writeBinary(this.list, file);
    }

    public void openFile(final File file) {
        TaskIO.readBinary(this.list, file);
    }

    public File getFile() {
        return this.file;
    }

    public void setFile(final File file) {
        this.file = file;
    }
}
