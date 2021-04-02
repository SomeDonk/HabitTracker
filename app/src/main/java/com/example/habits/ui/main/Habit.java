package com.example.habits.ui.main;

public class Habit {
    private String description;
    private Integer completions;
    private Integer goal;

    public Habit(String description, Integer completions, Integer goal) {
        this.description = description;
        this.completions = completions;
        this.goal = goal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCompletions() {
        return completions;
    }

    public void setCompletions(Integer completions) {
        this.completions = completions;
    }

    public Integer getGoal() {
        return goal;
    }

    public void setGoal(Integer goal) {
        this.goal = goal;
    }
}
