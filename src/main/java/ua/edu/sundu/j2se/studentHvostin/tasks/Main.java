package ua.edu.sundu.j2se.studentHvostin.tasks;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Task t = new Task();
        //Calendar calendar = new GregorianCalendar(2021, 9 , 1, 8, 00);

        String nameTask = "Работа";
        int time = 24;
        int start = 0, end = 48, interval = 24;

        int currentTime = 23;

        t.Task(nameTask, time);
        //t.Task(nameTask, start, end, interval);41651651651651651651

        System.out.print("Задача: " + t.getTitle() + " Активность: "+ t.isActive() + " Следующие исполнение: " + t.nextTimeAfter(currentTime));
    }
}
