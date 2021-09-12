package ua.edu.sundu.j2se.studentHvostin.tasks;

public class Task  {

    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;

    public Task (final String title, final int time) {
        this.title = title;
        this.time = time;
        this.active = false;
    }

    public Task (final String title, final int start, final int end, final int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
    }

    String getTitle() {
        return this.title;
    }

    void setTitle(final String title) {
        this.title = title;
    }

    boolean isActive() {
        return this.active;
    }

    void setActive(final boolean active) {
        this.active = active;
    }

    int getTime() {
        if (this.interval > 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    void setTime(final int time) {
        this.time = time;
        this.interval = 0;
    }

    int getStartTime() {
        if (this.interval > 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    int getEndTime() {
        if (this.interval > 0) {
            return this.end;
        } else {
            return this.time;
        }
    }

    int getRepeatInterval() {
        if (this.interval >= 0) {
            return this.interval;
        } else {
            return 0;
        }
    }

    void setTime(final int start, final int end, final  int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    boolean isRepeated() {
        if (this.interval > 0) {
            return true;
        } else {
            return false;
        }
    }

    int nextTimeAfter (final int current) {
        if ( current > getEndTime() ) {
            return -1;
        } else if (current > getStartTime() ) {
            int time = this.start;
            do {
                time += this.interval;
            }
            while (time < current);
            return time;
        } else {
            return getStartTime();
        }
    }

}