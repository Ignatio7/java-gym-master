package ru.yandex.practicum.gym;

import java.util.Objects;

public class TimeOfDay implements Comparable<TimeOfDay> {

    private int hours;
    private int minutes;

    public TimeOfDay(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(TimeOfDay o) {
        if (hours != o.hours) {
            return hours - o.hours;
        }
        return minutes - o.minutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeOfDay)) return false;
        TimeOfDay that = (TimeOfDay) o;
        return hours == that.hours && minutes == that.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hours, minutes);
    }
}