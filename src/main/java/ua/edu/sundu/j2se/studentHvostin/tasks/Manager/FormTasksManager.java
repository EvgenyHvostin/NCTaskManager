package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import org.apache.log4j.Logger;
import ua.edu.sundu.j2se.studentHvostin.tasks.Task;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;

public class FormTasksManager extends JFrame {

    static Logger log = Logger.getLogger(FormTasksManager.class.getName());

    private JPanel panelTasks;
    private JButton buttonAdd;
    private JButton buttonRemove;
    private JButton buttonEdit;
    private JButton buttonTasks;
    private JList listTasks;
    private JButton buttonSave;
    private JButton buttonOpen;

    ModelTaskManager tasks = new ModelTaskManager();

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
                tasks.addTask(new Task("New task", LocalDateTime.now()));
                new FormEditTask(listTasks.getLastVisibleIndex() + 1, mainForm);
                setVisible(false);
            }
        });

        this.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Edit task");
                if (listTasks.getSelectedIndex() == -1) listTasks.setSelectedIndex(0);
                new FormEditTask(listTasks.getSelectedIndex(), mainForm);
            }
        });

        this.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                log.info("Remove task");
                if (listTasks.getSelectedIndex() == -1) listTasks.setSelectedIndex(0);
                tasks.removeTask(listTasks.getSelectedIndex());
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

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showOpenDialog(panelTasks);
                jFileChooser.setCurrentDirectory(new File("."));
                tasks.saveFile(jFileChooser.getSelectedFile());
            }
        });

        buttonOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.showSaveDialog(panelTasks);
                jFileChooser.setCurrentDirectory(new File("."));
                tasks.openFile(jFileChooser.getSelectedFile());
            }
        });
    }

    public void updateList() {
        DefaultListModel listModel = new DefaultListModel();
        for (Task task : tasks.getList()) {
            listModel.addElement(task.nextTimeAfter(LocalDateTime.now()) + "  " + task.getTitle());
        }
        listTasks.setModel(listModel);
    }

    public void calendar(final LocalDateTime start, final LocalDateTime end) {
        DefaultListModel listModel = new DefaultListModel();
        for (String element : tasks.calendar(start, end)) {
            listModel.addElement(element);
        }
        listTasks.setModel(listModel);
        buttonTasks.setText("Back");
    }

}
