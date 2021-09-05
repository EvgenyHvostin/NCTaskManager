package ua.edu.sundu.j2se.studentHvostin.tasks;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // у тебя не запускается проект из-за класса ArrayTaskList. Не нужно коммитить,
        // если проект сломан. Пушить нужно только то, что работает. Если есть вопросы,
        // заводи pull-request.
        Task t = new Task();
        //Calendar calendar = new GregorianCalendar(2021, 9 , 1, 8, 00);

        String nameTask = "Работа";
        int time = 24;
        int start = 0, end = 48, interval = 24;

        int currentTime = 23;

        // так делать не нужно. Нужно срау вызвать конструктор, в котором можно посетить все неоюходимые
        // поля
        // Task t = new Task(nameTask, time);
        t.Task(nameTask, time);

        //t.Task(nameTask, start, end, interval);

        // Слишком длинные строки. Так делать тоже нежелательно. На работе не пройдёшь код-ревью,
        // упадёт локальный билд, а главное, это просто неудобно читать. Добавил пример ниже, как возможно.
        System.out.print("Задача: " + t.getTitle() + " Активность: "+ t.isActive() + " Следующие исполнение: " + t.nextTimeAfter(currentTime));

        // Пример:
        System.out.println(String.format("\nЗадача: %s Активность: %s Следующие исполнение: %s",
            t.getTitle(), t.isActive(), t.nextTimeAfter(currentTime)));
        // Либо так:
        System.out.print("Задача: " + t.getTitle() + " Активность: "+ t.isActive()
            + " Следующие исполнение: " + t.nextTimeAfter(currentTime));
    }
}
