package ru.yandex.practicum.gym;

public record CounterOfTrainings(Coach coach, int count) {

    @Override
    public String toString() {
        return coach.getSurname() + " " +
                coach.getName() + " " +
                coach.getMiddleName() +
                " — тренировок: " + count;
    }
}