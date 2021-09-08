package ua.edu.sundu.j2se.studentHvostin.tasks;

public class Task  {

    String title;
    int time;
    int start;
    int end;
    int interval;
    boolean active;

     public void Task (final String title, final int time) {
        this.title = title;
        this.time = time;
    }

    public void Task (final String title, final int start, final int end, final int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    boolean isActive() {
        return true;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    int getTime() {
        if (interval > 0) {
            return start;
        } else {
            return time;
        }
    }

    void setTime(int time) {
        this.time = time;
    }

    int getStartTime() {
        if (interval > 0) {
            return start;
        } else {
            return time;
        }
    }

    int getEndTime() {
        if (interval > 0) {
            return end;
        } else {
            return time;
        }
    }

    int getRepeatInterval() {
        if (interval >= 0) {
            return interval;
        } else {
            return 0;
        }
    }

    void setTime(int start, int end, int interval) {
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    boolean isRepeated() {
        if (interval > 0) {
            return true;
        } else {
            return false;
        }
    }

    int nextTimeAfter (int current) {
        if ( current > getEndTime() ) {
            return -1;
        } else if (current > getStartTime() ) {
            int t = start;
            do {
                t += interval;
            }
            while (t < current);
            return t;
        } else {
            return getStartTime();
        }
    }

}