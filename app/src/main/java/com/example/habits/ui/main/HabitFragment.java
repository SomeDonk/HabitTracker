package com.example.habits.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.habits.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A placeholder fragment containing a simple view.
 */
public class HabitFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private HabitViewModel pageViewModel;

    public static HabitFragment newInstance(int index) {
        HabitFragment fragment = new HabitFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(HabitViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }



    private class HabitHolder extends RecyclerView.ViewHolder {

//        private User user;
//        private final ImageView userImage;
//        private final TextView userAlias;
//        private final TextView userName;
//        private final TextView message;
//        private final TextView date;

        HabitHolder(@NonNull View itemView) {
            super(itemView);

//            userImage = itemView.findViewById(R.id.habit_userImage);
//            userAlias = itemView.findViewById(R.id.habit_userAlias);
//            userName = itemView.findViewById(R.id.habit_userName);
//            message = itemView.findViewById(R.id.habit_message);
//            date = itemView.findViewById(R.id.habit_date);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(getContext(), "You selected '" + userName.getText() + "'.", Toast.LENGTH_SHORT).show();
//                    presenter.setCurrentUser(user);
//                    Intent intent = new Intent(getActivity(), ChildProfileActivity.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                }
//            });
        }

        void bindHabit(Habit habit) {
//            userImage.setImageDrawable(ImageCache.getInstance().getImageDrawable(user));
//            userAlias.setText(user.getAlias());
//            userName.setText(user.getName());
//
//            // linkify the message
//            message.setText(habit.getMessage());
        }
    }

    private class StoryRecyclerViewAdapter extends RecyclerView.Adapter<HabitHolder> {

        private final List<Habit> habits = new ArrayList<>();

        private Habit lastHabit;

        StoryRecyclerViewAdapter() {
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

            view = layoutInflater.inflate(R.layout.habit, parent, false);

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

            addItem(new Habit());
        }
    }
}