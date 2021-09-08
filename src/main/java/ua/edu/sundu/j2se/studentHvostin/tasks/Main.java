package ua.edu.sundu.j2se.studentHvostin.tasks;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String nameTask = "Работа";
        int currentTime = 23;
        //int time = 24;
        int start = 0, end = 48, interval = 24;

        //Task t = new Task(nameTask, time);
        Task t = new Task(nameTask, start, end, interval);

        System.out.print("Задача: " + t.getTitle() + " Активность: "+ t.isActive() + " Следующие исполнение: " + t.nextTimeAfter(currentTime));
    }
}
