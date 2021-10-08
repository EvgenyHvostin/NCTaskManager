package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import ua.edu.sundu.j2se.studentHvostin.tasks.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
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

    public FormEditTask (Task task, FormTasksManager f) {

        this.add(this.panalEdit);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(530,300);

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

                task.setTitle(textTitle.getText());
                if (interval == 0) {
                    task.setTime(time);
                } else {
                    year = Integer.parseInt((String) comboBoxYear2.getSelectedItem());
                    month = Integer.parseInt((String) comboBoxMonth2.getSelectedItem());
                    day = Integer.parseInt((String) comboBoxDay2.getSelectedItem());
                    hour = Integer.parseInt((String) comboBoxHour2.getSelectedItem());
                    LocalDateTime end = LocalDateTime.of(year, month, day, hour, 0);
                    task.setTime(time,end,interval);
                }
                task.setActive(activeCheckBox.isSelected());
                f.setVisible(true);
                f.updateList();
                dispose();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
                dispose();
            }
        });
    }
}
