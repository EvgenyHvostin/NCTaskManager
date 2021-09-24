package ua.edu.sundu.j2se.studentHvostin.tasks;

public class Task implements Cloneable {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task (final String title, final int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        } else {
            this.title = title;
            this.time = time;
            this.active = false;
        }

    }

    public Task (final String title, final int start, final int end, final int interval) {
        if (start < 0 || end < 0 ) {
            throw new IllegalArgumentException("Time cannot be negative");
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

    public int getTime() {
        if (this.interval > 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    public void setTime(final int time) {
        if (time < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        } else {
            this.time = time;
            this.interval = 0;
        }
    }

    public int getStartTime() {
        if (this.interval > 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    public int getEndTime() {
        if (this.interval > 0) {
            return this.end;
        } else {
            return this.time;
        }
    }

    public int getRepeatInterval() {
        if (this.interval >= 0) {
            return this.interval;
        } else {
            return 0;
        }
    }

    public void setTime(final int start, final int end, final  int interval) {
        if (start < 0 || end < 0 ) {
            throw new IllegalArgumentException("Time cannot be negative");
        } else if (interval <= 0) {
            throw new IllegalArgumentException("Interval cannot be negative");
        } else {
            this.start = start;
            this.end = end;
            this.interval = interval;
        }
    }

    public boolean isRepeated() {
        if (this.interval > 0) {
            return true;
        } else {
            return false;
        }
    }

    public int nextTimeAfter (final int current) {
        if ( current > this.getEndTime() ) {
            return -1;
        } else if (current > this.getStartTime() ) {
            int time = this.start;
            do {
                time += this.interval;
            }
            while (time < current);
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

        int result = this.getStartTime() * this.getEndTime() + getRepeatInterval() + active;

        return result;
    }

    @Override
    public boolean equals(Object task) {
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
        if (this.isRepeated()) {
            return "(title=" + this.getTitle() +
                    ", startTime=" + this.getStartTime() +
                    ", endTime=" + this.getEndTime() +
                    ", repeatInterval=" + this.getRepeatInterval() + ")";
        } else {
            return "(title=" + this.getTitle() +
                    ", time=" + this.getTime() + ")";
        }
    }

    @Override
    public Task clone() {
        Task newTask = new Task(null, 0);
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