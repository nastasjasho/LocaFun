package com.example.dugmici;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dugmici.model.Reminder;

public class RemindersListAdapter extends ArrayAdapter<Reminder>{

    public RemindersListAdapter(Context context, int resource, List<Reminder> objects) {
        super(context, resource, objects);
    }
    
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View cellView = inflater.inflate(R.layout.reminder_cell, parent, false);
        final Reminder reminder = getItem(position);
        
        TextView title = (TextView) cellView.findViewById(R.id.taskTitle);
        title.setText(reminder.getTask());
        
        TextView details = (TextView) cellView.findViewById(R.id.taskDetails);
        details.setText(reminder.getTask());
        return cellView;
    }

}
