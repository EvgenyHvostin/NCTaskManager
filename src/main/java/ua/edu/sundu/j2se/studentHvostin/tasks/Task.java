package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Task implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;

    public Task (final String title, final LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null");
        } else {
            this.title = title;
            this.time = time;
            this.active = false;
        }
    }

    public Task (final String title, final LocalDateTime start, final LocalDateTime end, final int interval) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Time cannot be null");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("Interval cannot be negative");
        } else {
            this.title = title;
            this.start = start;
            this.end = end;
            this.interval = interval;
            this.active = false;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public LocalDateTime getTime() {
        if (this.interval == 0) {
            return this.time;
        } else {
            return this.start;
        }
    }

    public void setTime(final LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
        this.time = time;
        this.interval = 0;
    }

    public void setTime(final LocalDateTime start, final LocalDateTime end, final int interval) {
        if (start == null || end == null ) {
            throw new IllegalArgumentException("Time cannot be null");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("Interval cannot be negative");
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    public LocalDateTime getStartTime() {
        if (this.interval == 0) {
            return this.time;
        } else {
            return this.start;
        }
    }

    public LocalDateTime getEndTime() {
        if (this.interval == 0) {
            return this.time;
        } else {
            return this.end;
        }
    }

    public int getRepeatInterval() {
        return this.interval;
    }

    public boolean isRepeated() {
        return (this.interval > 0);
    }

    public LocalDateTime nextTimeAfter (final LocalDateTime current) {

        if (current.isAfter(this.getEndTime())) {
            return null;
        } else if (current.isAfter(this.getStartTime())) {
            LocalDateTime time = this.start;
            do {
                time = time.plus(this.interval, ChronoUnit.DAYS);
            }
            while (!time.isAfter(current));
            return time;
        } else {
            return this.getStartTime();
        }
    }

    @Override
    public int hashCode() {
        int active = 0;

        if (this.active) {
            active = 1;
        }

        int result = this.getStartTime().hashCode()
                * this.getEndTime().hashCode()
                + getRepeatInterval()
                + active;

        return result;
    }

    @Override
    public boolean equals(final Object task) {
        if (task == null)
            return false;
        if (this == task)
            return true;
        if (getClass() != task.getClass())
            return false;

        Task other = (Task) task;

        if (!this.getTitle().equals(other.getTitle()))
            return false;
        if (this.getStartTime() != other.getStartTime())
            return false;
        if (this.getEndTime() != other.getEndTime())
            return false;
        return this.getRepeatInterval() == other.getRepeatInterval();
    }

    @Override
    public String toString(){
        String result = "(";
        if (this.isRepeated()) {
            result += "title=" + this.getTitle() +
                    ", startTime=" + this.getStartTime() +
                    ", endTime=" + this.getEndTime() +
                    ", repeatInterval=" + this.getRepeatInterval();
        } else {
            result += "title=" + this.getTitle() +
                    ", time=" + this.getTime();
        }
        return result + ")";
    }

    private Task () {}

    @Override
    public Task clone() {
        Task newTask = new Task();
        if (this.isRepeated()) {
            newTask.setTitle(this.getTitle());
            newTask.setTime(
                    this.getStartTime(),
                    this.getEndTime(),
                    this.getRepeatInterval());
        } else {
            newTask.setTitle(this.getTitle());
            newTask.setTime(this.getTime());
        }
        return newTask;
    }

}