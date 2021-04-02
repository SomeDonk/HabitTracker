package com.example.habits.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habits.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class HabitFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private HabitRecyclerViewAdapter habitRecyclerViewAdapter;

    public static HabitFragment newInstance(int index) {
        HabitFragment fragment = new HabitFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
//        final TextView textView = root.findViewById(R.id.section_label);
//        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        RecyclerView followingRecyclerView = root.findViewById(R.id.habitRecyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        followingRecyclerView.setLayoutManager(layoutManager);

        habitRecyclerViewAdapter = new HabitRecyclerViewAdapter();
        followingRecyclerView.setAdapter(habitRecyclerViewAdapter);

        return root;
    }



    private class HabitHolder extends RecyclerView.ViewHolder {

        private final TextView description;
        private final TextView completions;
        private final TextView goal;
        private final Button up_completions;
        private final Button down_completions;

        HabitHolder(@NonNull View itemView) {
            super(itemView);

            description = itemView.findViewById(R.id.habit_description);
            completions = itemView.findViewById(R.id.habit_completions);
            goal = itemView.findViewById(R.id.habit_goal);
            up_completions = itemView.findViewById(R.id.habit_up_completions);
            down_completions = itemView.findViewById(R.id.habit_down_completions);

            up_completions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), "You selected '" + userName.getText() + "'.", Toast.LENGTH_SHORT).show();
                    if (!completions.getText().equals(goal.getText())) {
                        Integer newValue = Integer.parseInt(completions.getText().toString()) + 1;
                        completions.setText(newValue.toString());
                    }
                }
            });

            down_completions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(getContext(), "You selected '" + userName.getText() + "'.", Toast.LENGTH_SHORT).show();
                    if (Integer.parseInt(completions.getText().toString()) > 0) {
                        Integer newValue = Integer.parseInt(completions.getText().toString()) - 1;
                        completions.setText(newValue.toString());
                    }
                }
            });
        }

        void bindHabit(Habit habit) {
            description.setText(habit.getDescription());
            completions.setText(habit.getCompletions().toString());
            goal.setText(habit.getGoal().toString());
        }
    }

    private class HabitRecyclerViewAdapter extends RecyclerView.Adapter<HabitHolder> {

        private final List<Habit> habits = new ArrayList<>();

        HabitRecyclerViewAdapter() {
            loadDummyHabits();
        }

        void addItems(List<Habit> habits) {
            int startInsertPosition = this.habits.size();
            this.habits.addAll(habits);
            this.notifyItemRangeInserted(startInsertPosition, habits.size());
        }

        void addItem(Habit habit) {
            habits.add(habit);
            this.notifyItemInserted(habits.size() - 1);
        }

        void removeItem(Habit habit) {
            int position = habits.indexOf(habit);
            habits.remove(position);
            this.notifyItemRemoved(position);
        }

        @NonNull
        @Override
        public HabitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(HabitFragment.this.getContext());
            View view;

            view = layoutInflater.inflate(R.layout.habit_box, parent, false);

            return new HabitHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HabitHolder habitHolder, int position) {
            habitHolder.bindHabit(habits.get(position));
        }

        @Override
        public int getItemCount() {
            return habits.size();
        }

//        @Override
//        public int getItemViewType(int position) {
//            return ITEM_VIEW;
//        }


        void loadDummyHabits() {
            addItem(new Habit("Drink a glass of water", 2, 4));
            addItem(new Habit("Slap a fish", 0, 50));
            addItem(new Habit("Wear a mask", 1, 1));
        }
    }
}