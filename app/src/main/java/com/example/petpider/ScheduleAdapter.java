package com.example.petpider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleHolder> {

    private List<Schedule> scheduleList;
    private Context context;

    public ScheduleAdapter(List<Schedule> scheduleList, Context context) {
        this.scheduleList = scheduleList;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.schedule_holder, parent, false);
        return new ScheduleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.time_schedule.setText(schedule.time);
        holder.day_schedule.setText(schedule.day);
        holder.title_schedule.setText(schedule.title);
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleHolder extends RecyclerView.ViewHolder {
        public TextView day_schedule, time_schedule, title_schedule;

        public ScheduleHolder(View itemView) {
            super(itemView);
            day_schedule = itemView.findViewById(R.id.day_schedule);
            time_schedule = itemView.findViewById(R.id.time_schedule);
            title_schedule = itemView.findViewById(R.id.title_schedule);
        }
    }


}
