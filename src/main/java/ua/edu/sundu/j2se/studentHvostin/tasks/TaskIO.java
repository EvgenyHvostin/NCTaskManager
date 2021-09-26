package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class TaskIO {
    public static void write(AbstractTaskList tasks, OutputStream out) {
        try{
            out.write(tasks.toString().getBytes());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }//– записывает задачи из списка в поток в двоичном формате.

    public static void read(AbstractTaskList tasks, InputStream in) {

    }//– считывает задачи из потока в текущий список задач.

    public static void writeBinary(AbstractTaskList tasks, File file) {

    }//– записывает задачи из списка в файл.

    public static void readBinary(AbstractTaskList tasks, File file) {

    }//– считывает задачи из файла в список задач.

}
