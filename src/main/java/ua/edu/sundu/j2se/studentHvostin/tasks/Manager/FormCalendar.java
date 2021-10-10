package ua.edu.sundu.j2se.studentHvostin.tasks.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class FormCalendar extends JFrame  {

    private JComboBox comboBoxMonth1;
    private JComboBox comboBoxYear1;
    private JComboBox comboBoxHour1;
    private JComboBox comboBoxDay2;
    private JComboBox comboBoxDay1;
    private JComboBox comboBoxYear2;
    private JComboBox comboBoxHour2;
    private JButton buttonCancel;
    private JButton buttonOK;
    private JComboBox comboBoxMonth2;
    private JPanel panelCalendar;

    public FormCalendar(FormTasksManager mainForm) {
        this.add(this.panelCalendar);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(500,200);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int year = Integer.parseInt((String) comboBoxYear1.getSelectedItem());
                int month = Integer.parseInt((String) comboBoxMonth1.getSelectedItem());
                int day = Integer.parseInt((String) comboBoxDay1.getSelectedItem());
                int hour = Integer.parseInt((String) comboBoxHour1.getSelectedItem());
                LocalDateTime start = LocalDateTime.of(year, month, day, hour, 0);
                year = Integer.parseInt((String) comboBoxYear2.getSelectedItem());
                month = Integer.parseInt((String) comboBoxMonth2.getSelectedItem());
                day = Integer.parseInt((String) comboBoxDay2.getSelectedItem());
                hour = Integer.parseInt((String) comboBoxHour2.getSelectedItem());
                LocalDateTime end = LocalDateTime.of(year, month, day, hour, 0);

                mainForm.calendar(start, end);
                mainForm.setVisible(true);
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
