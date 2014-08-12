package com.example.dugmici.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Reminder {

    private static final String TAG = Reminder.class.getSimpleName();
    private String task;
    private String details;

    public Reminder(String task, String details) {
        this.task = task;
        this.details = details;

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Reminder [task=" + task + ", details=" + details + "]";
    }

    public JSONObject toJSON() {
        JSONObject jsonReminder = new JSONObject();
        try {
            jsonReminder.put("task", getTask());
            jsonReminder.put("details", getDetails());
        } catch (JSONException e) {
            Log.e(TAG,"Unable to create JSON");
        }
        
        return jsonReminder;
    }

    public static Reminder parseJSONString(String jsonReminderString) throws JSONException {
        JSONObject jsonReminder = new JSONObject(jsonReminderString);
        Reminder reminder = new Reminder(jsonReminder.getString("task"), jsonReminder.getString("details"));
        return reminder;
    }

    public static List<Reminder> parseRemindersList(String jsonRemidersList) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonRemidersList);
        List<Reminder> reminders = new ArrayList<Reminder>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json = jsonArray.getJSONObject(i);
            reminders.add(parseJSONString(json.toString()));
        }
        return reminders;
    }

}
