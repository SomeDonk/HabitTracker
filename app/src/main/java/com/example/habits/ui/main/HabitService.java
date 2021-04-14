package com.example.habits.ui.main;

public class HabitService {

    private static HabitService instance;

    private HabitObserver observer;

    public interface HabitObserver {
        void newHabitCompleted(Habit newHabit);
    }

    public static HabitService getInstance() {
        if (instance == null) {
            instance = new HabitService(null);
        }
        return instance;
    }

    public void addHabit(Habit habit) {
        observer.newHabitCompleted(habit);
    }

    private HabitService(HabitObserver observer) {
        this.observer = observer;
    }

    public static void setInstance(HabitService instance) {
        HabitService.instance = instance;
    }

    public HabitObserver getObserver() {
        return observer;
    }

    void setObserver(HabitObserver observer) {
        this.observer = observer;
    }
}