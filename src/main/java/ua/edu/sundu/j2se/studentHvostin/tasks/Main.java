package ua.edu.sundu.j2se.studentHvostin.tasks;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

public class Main {
    public static void main(String[] args) {

        String nameTask = "Работа";
        int currentTime = 23;
        //int time = 24;
        int start = 0, end = 48, interval = 24;

        //Task t = new Task(nameTask, time);
        Task t = new Task(nameTask, start, end, interval);

        //System.out.print("Задача: " + t.getTitle() +
         //               " Активность: "+ t.isActive() +
          //              " Следующие исполнение: " + t.nextTimeAfter(currentTime));

        ArrayTaskList arr = new ArrayTaskList();

        arr.add(new Task("aa1aa", 22));
        arr.add(new Task("aa2aa", 55));
        arr.add(new Task("aa3aa", 0));
        System.out.print(arr.getTask(0).getTitle());
        System.out.print(arr.getTask(1).getTime());
        System.out.print(arr.getTask(2).getTitle());

        System.out.print(
                "Задача: " + t.getTitle() +
                " Активность: "+ t.isActive() +
                " Следующие исполнение: " + t.nextTimeAfter(currentTime));

        System.out.print(" \n ");

        ArrayTaskList arrList = new ArrayTaskList();

        arrList.add(new Task("pss", 0, 48, 24));
        arrList.add(new Task("asadddddd", 24));
        arrList.add(new Task("asdad", 72));
        //System.out.print(arrList.getTask(0));
        arrList.remove(new Task("pss", 0, 48, 24));
        System.out.print(arrList.getTask(0).getTime());
        System.out.print(arrList.getTask(1).getTime());
        System.out.print(arrList.getTask(2).getTime());

        ArrayTaskList incArr = arrList.incoming(0,48);

        System.out.print(incArr.getTask(0).getTime());
        System.out.print(incArr.getTask(1).getTime());

    }
}
