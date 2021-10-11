package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class FormEditTask extends JFrame {

    private JTextArea textTitle;
    private JCheckBox activeCheckBox;
    private JComboBox comboBoxDay1;
    private JComboBox comboBoxMonth1;
    private JComboBox comboBoxYear1;
    private JComboBox comboBoxHour1;
    private JTextArea textIntervalDays;
    private JButton buttonCancel;
    private JButton buttonOK;
    private JPanel panalEdit;
    private JComboBox comboBoxDay2;
    private JComboBox comboBoxMonth2;
    private JComboBox comboBoxYear2;
    private JComboBox comboBoxHour2;

    public FormEditTask (int index, FormTasksManager mainForm) {

        this.add(this.panalEdit);
        this.setVisible(true);
        //this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(380,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Task task = mainForm.tasks.getTask(index);

        textTitle.setText(task.getTitle());
        activeCheckBox.setSelected(task.isActive());
        comboBoxDay1.setSelectedIndex(task.getTime().getDayOfMonth() - 1);
        comboBoxMonth1.setSelectedIndex(task.getTime().getMonthValue() - 1);
        comboBoxYear1.setSelectedIndex(task.getTime().getYear() - 2021);
        comboBoxHour1.setSelectedIndex(task.getTime().getHour());

        if (task.isRepeated()) {
            textIntervalDays.setText(String.valueOf(task.getRepeatInterval()));
            comboBoxDay2.setSelectedIndex(task.getEndTime().getDayOfMonth() - 1);
            comboBoxMonth2.setSelectedIndex(task.getEndTime().getMonthValue() - 1);
            comboBoxYear2.setSelectedIndex(task.getEndTime().getYear() - 2021);
            comboBoxHour2.setSelectedIndex(task.getEndTime().getHour());
        }

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int interval = Integer.parseInt(textIntervalDays.getText());
                int year = Integer.parseInt((String) comboBoxYear1.getSelectedItem());
                int month = Integer.parseInt((String) comboBoxMonth1.getSelectedItem());
                int day = Integer.parseInt((String) comboBoxDay1.getSelectedItem());
                int hour = Integer.parseInt((String) comboBoxHour1.getSelectedItem());

                LocalDateTime time = LocalDateTime.of(year, month, day, hour, 0);
                year = Integer.parseInt((String) comboBoxYear2.getSelectedItem());
                month = Integer.parseInt((String) comboBoxMonth2.getSelectedItem());
                day = Integer.parseInt((String) comboBoxDay2.getSelectedItem());
                hour = Integer.parseInt((String) comboBoxHour2.getSelectedItem());
                LocalDateTime end = LocalDateTime.of(year, month, day, hour, 0);

                mainForm.tasks.editTask(index, activeCheckBox.isSelected(), textTitle.getText(), time, end, interval);
                mainForm.setVisible(true);
                mainForm.updateList();
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.setVisible(true);
                dispose();
            }
        });
    }
}
