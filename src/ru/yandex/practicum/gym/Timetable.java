package ru.yandex.practicum.gym;

import java.util.*;

public class Timetable {

    private HashMap<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable;

    public Timetable() {
        timetable = new HashMap<>();

        for (DayOfWeek day : DayOfWeek.values()) {
            timetable.put(day, new TreeMap<>());
        }
    }

    public void addNewTrainingSession(TrainingSession trainingSession) {
        //сохраняем занятие в расписании
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(day);
        List<TrainingSession> sessions = dayMap.get(time);

        if (sessions == null) {
            sessions = new ArrayList<>();
            dayMap.put(time, sessions);
        }
        sessions.add(trainingSession);
    }

    public List<TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);

        List<TrainingSession> result = new ArrayList<>();
        for (List<TrainingSession> sessions : dayMap.values()){
            result.addAll(sessions);
        }

        return result;
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        TreeMap<TimeOfDay, List<TrainingSession>> dayMap = timetable.get(dayOfWeek);
        List<TrainingSession> sessions = dayMap.get(timeOfDay);

        if (sessions != null) {
            return new ArrayList<>(sessions);
        } else {
            return Collections.emptyList();
        }
    }

    public List<CoachTrainingCount> getCountByCoaches() {
        HashMap<Coach, Integer> coachTrainingMap = new HashMap<>();

        for(TreeMap<TimeOfDay,List<TrainingSession>> dayMap : timetable.values()) {
            for (List<TrainingSession> sessions : dayMap.values()) {
                for (TrainingSession session : sessions) {
                    Coach coach = session.getCoach();
                    int current = coachTrainingMap.getOrDefault(coach, 0);
                    coachTrainingMap.put(coach,current + 1);
                }
            }
        }

        List<CoachTrainingCount> result = new ArrayList<>();

        for(Map.Entry<Coach, Integer> entry : coachTrainingMap.entrySet()) {
            result.add(new CoachTrainingCount(entry.getKey(), entry.getValue()));
        }

        Collections.sort(result);
        return result;
    }
}
