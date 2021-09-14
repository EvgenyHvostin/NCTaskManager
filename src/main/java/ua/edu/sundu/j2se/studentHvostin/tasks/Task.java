package ua.edu.sundu.j2se.studentHvostin.tasks;

public class Task  {

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

}