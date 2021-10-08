package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import org.apache.log4j.Logger;
import ua.edu.sundu.j2se.studentHvostin.tasks.Services.Tasks;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.AbstractTaskList;
import ua.edu.sundu.j2se.studentHvostin.tasks.TaskList.ArrayTaskList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

public class FormTasksManager extends JFrame {

    static Logger log = Logger.getLogger(FormTasksManager.class.getName());

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
                log.info("Add task");
                tasks.add(new Task("New task", LocalDateTime.now()));
                new FormEditTask(tasks.getTask(tasks.getSize() - 1), mainForm);
                setVisible(false);
            }
        });

        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Edit task");
                int index = listTasks.getSelectedIndex();
                new FormEditTask(tasks.getTask(index), mainForm);
            }
        });

        this.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Remove task");
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
                    log.info("Open calendar tasks");
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
