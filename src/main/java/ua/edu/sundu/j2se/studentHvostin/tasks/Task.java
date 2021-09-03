package ua.edu.sundu.j2se.studentHvostin.tasks;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task  {
    String _title;
    int _time, _start, _end, _interval;
    boolean _active, _repeated;

     public void Task (String title, int time) {
        setTitle(title);
        setTime(time);
        setActive(false);
        _repeated = false;
    }
    public void Task (String title, int start, int end, int interval) {
        setTitle(title);
        setTime(start, end, interval);
        setActive(false);
        _repeated = true;
    }
    String getTitle() {
        return _title;
    }
    void setTitle(String title){
        _title = title;
    }
    boolean isActive() {
      return _active;
    }
    void setActive(boolean active){
        _active = active;
    }
    int getTime(){
        if (_repeated){
            return _start; //если задача является повторяющейся, метод должен возвращать время начала повторения
        }else{
            return _time;
        }
    }
    void setTime(int time) {
        _time = time;
        if (_repeated) _repeated = false; //если задача была повторяющейся, она должна стать неповторяющейся
    }
    int getStartTime(){
        if (_repeated){
            return _start;//если задача неповторяющаяся, метод должен возвращать время начала выполнения
        }else{
            return _time;
        }
    }
    int getEndTime(){
        if (_repeated){
            return _end; //если задача неповторяющаяся, метод должен возвращать время окончания выполнения
        }else{
            return _time;
        }
    }
    int getRepeatInterval(){
        if (_repeated){
            return _interval;
        }else{
            return 0;//если задача неповторяющаяся, метод должен возвращать 0
        }
    }
    void setTime(int start, int end, int interval){
        _start = start;
        _end = end;
        _interval = interval;
        if (!_repeated) _repeated = true;//если задача не повторяется, она должна стать повторяющейся
    }
    boolean isRepeated(){
        return _repeated;
    }
    int nextTimeAfter (int current){
        if (current>getEndTime()) {
            return -1;
        }else if(current>getStartTime()){
            int t = _start;
            do{
                t += _interval;
            }
            while (t < current);
            return t;
        }else{
            return getStartTime();
        }
    }
}