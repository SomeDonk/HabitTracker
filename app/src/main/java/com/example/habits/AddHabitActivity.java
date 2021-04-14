package com.example.habits;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.habits.ui.main.Habit;
import com.example.habits.ui.main.HabitService;
import com.example.habits.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class AddHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);
        final EditText newDescription = findViewById(R.id.new_habit_description);
        final EditText newGoal = findViewById(R.id.new_habit_goal);
        final Button saveButton = findViewById(R.id.new_habit_save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Habit newHabit = new Habit(newDescription.getText().toString(), 0, Integer.parseInt(newGoal.getText().toString()));
                HabitService.getInstance().addHabit(newHabit);
                onBackPressed();
            }
        });
    }
}