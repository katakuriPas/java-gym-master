package ru.yandex.practicum.gym;

import ru.yandex.practicum.gym.Coach;

public class CoachTrainingCount implements Comparable<CoachTrainingCount> {
    private final Coach coach;
    private final int trainingCount;

    public CoachTrainingCount(Coach coach, int trainingCount) {
        this.coach = coach;
        this.trainingCount = trainingCount;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getTrainingCount() {
        return trainingCount;
    }

    @Override
    public int compareTo(CoachTrainingCount other) {
        // Сортировка по убыванию: кто больше тренировок — выше в списке
        return Integer.compare(other.trainingCount, this.trainingCount);
    }

    @Override
    public String toString() {
        return coach + ": " + trainingCount + " тренировок";
    }
}