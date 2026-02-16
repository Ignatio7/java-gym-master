package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    public Timetable() {
        timetable = new HashMap<>();
    }

    //Добавляем тренировки
    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> daySchedule =
                timetable.computeIfAbsent(day, d -> new TreeMap<>());

        List<TrainingSession> sessionsAtTime =
                daySchedule.computeIfAbsent(time, t -> new ArrayList<>());

        sessionsAtTime.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        TreeMap<TimeOfDay, List<TrainingSession>> daySchedule = timetable.get(dayOfWeek);

        if (daySchedule == null) {
            return Collections.emptyList();
        }

        List<TrainingSession> result = new ArrayList<>();

        for (TimeOfDay time : daySchedule.navigableKeySet()) {
            result.addAll(daySchedule.get(time));
        }

        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(
            DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {

        TreeMap<TimeOfDay, List<TrainingSession>> daySchedule = timetable.get(dayOfWeek);

        if (daySchedule == null) {
            return Collections.emptyList();
        }

        return daySchedule.getOrDefault(timeOfDay, Collections.emptyList());
    }


    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> counters = new HashMap<>();

        for (TreeMap<TimeOfDay, List<TrainingSession>> daySchedule : timetable.values()) {
            for (List<TrainingSession> sessions : daySchedule.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    counters.put(coach, counters.getOrDefault(coach, 0) + 1);
                }
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : counters.entrySet()) {
            result.add(new CounterOfTrainings(entry.getKey(), entry.getValue()));
        }

        result.sort((a, b) -> Integer.compare(b.count(), a.count()));
        return result;
    }
}