package ua.edu.sundu.j2se.studentHvostin.tasks;

public class Task  {

    String title;
    int time;
    int start;
    int end;
    int interval;
    boolean active;

     public void Task (final String TITLE, final int TIME) {
        this.title = TITLE;
        this.time = TIME;
        this.active = false;
    }

    public void Task (final String TITLE, final int START, final int END, final int INTERVAL) {
        this.title = TITLE;
        this.start = START;
        this.end = END;
        this.interval = INTERVAL;
        this.active = false;
    }

    String getTitle() {
        return this.title;
    }

    void setTitle(final String TITLE) {
        this.title = TITLE;
    }

    boolean isActive() {
        return this.active;
    }

    void setActive(final boolean ACTIVE) {
        this.active = ACTIVE;
    }

    int getTime() {
        if (this.interval > 0) {
            return this.start;
        } else {
            return this.time;
        }
    }

    void setTime(final int TIME) {
        this.time = TIME;
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

    void setTime(final int START, final int END, final  int INTERVAL) {
        this.start = START;
        this.end = END;
        this.interval = INTERVAL;
    }

    boolean isRepeated() {
        if (this.interval > 0) {
            return true;
        } else {
            return false;
        }
    }

    int nextTimeAfter (final int CURRENT) {
        if ( CURRENT > getEndTime() ) {
            return -1;
        } else if (CURRENT > getStartTime() ) {
            int time = this.start;
            do {
                time += this.interval;
            }
            while (time < CURRENT);
            return time;
        } else {
            return getStartTime();
        }
    }

}