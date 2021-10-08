package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import ua.edu.sundu.j2se.studentHvostin.tasks.Services.Tasks;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.ArrayTaskList;

import javax.swing.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.*;
import javax.swing.JFrame;

public class FormTasksManager extends JFrame {

    private JPanel panelTasks;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buttonEdit;
    private JButton buttonTasks;
    private JList listTasks;

    private AbstractTaskList tasks = new ArrayTaskList();

    public FormTasksManager() {
        FormTasksManager mainForm = this;

        this.add(this.panelTasks);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(300,200);

        this.buttonAdd.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e)  {
                tasks.add(new Task("New task", LocalDateTime.now()));
                new FormEditTask(tasks.getTask(tasks.getSize() - 1), mainForm);
                setVisible(false);
            }
        });

        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listTasks.getSelectedIndex();
                new FormEditTask(tasks.getTask(index), mainForm);
            }
        });

        this.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listTasks.getSelectedIndex() == -1) listTasks.setSelectedIndex(0);
                tasks.remove(tasks.getTask(listTasks.getSelectedIndex()));
                DefaultListModel listModel = (DefaultListModel) listTasks.getModel();
                listModel.remove(listTasks.getSelectedIndex());
            }
        });

        buttonTasks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonTasks.getText().equals("Back")) {
                    buttonTasks.setText("Calendar");
                    updateList();
                } else {
                    new FormCalendar(mainForm) ;
                    setVisible(false);
                }
            }
        });
    }

    public void updateList() {
        DefaultListModel listModel = new DefaultListModel();
        for (Task task : tasks) {
            listModel.addElement(task.nextTimeAfter(LocalDateTime.now()) + "  " + task.getTitle());
        }
        listTasks.setModel(listModel);
    }

    public void calendarList(final LocalDateTime start, final LocalDateTime end) {
        Collection<Set<String>> calendar = Tasks.calendar(tasks, start, end).values();
        DefaultListModel listModel = new DefaultListModel();
        for (Set element : calendar) {
            listModel.addElement(element);
        }
        listTasks.setModel(listModel);
        buttonTasks.setText("Back");
    }

}
