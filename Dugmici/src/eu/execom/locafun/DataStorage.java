package eu.execom.locafun;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.example.dugmici.model.Reminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;

public class DataStorage {
    
    private static final String PREFERENCES = "locafun-data";
    private static final String REMINDERS = "reminders";
    private static final String TAG = DataStorage.class.getSimpleName();
    
    private Editor editor;
    private SharedPreferences pref;
    
    
    public DataStorage(Context context) {
        super();
        pref = context.getSharedPreferences(PREFERENCES, 0);
        editor = pref.edit();
    }
    
    
    public static DataStorage get(Context context) {
        return new DataStorage(context);
    }
    
    public void saveReminder(Reminder reminder){
        String remindersJSONList = pref.getString(REMINDERS, "");
        if(TextUtils.isEmpty(remindersJSONList)){
            remindersJSONList = new JSONArray().put(reminder.toJSON()).toString();
            editor.putString(REMINDERS, remindersJSONList);
            editor.commit();
        }else{
            try {
                JSONArray jsonArray = new JSONArray(remindersJSONList);
                jsonArray.put(reminder.toJSON());
                editor.putString(REMINDERS, jsonArray.toString());
                editor.commit();
            } catch (JSONException e) {
                Log.e(TAG, "Uable to create Reminders JSON list");
            }
        }
    }
    
    public List<Reminder> getReminders(){
        try {
            return Reminder.parseRemindersList(pref.getString(REMINDERS, ""));
        } catch (JSONException e) {
            Log.e(TAG, "Uable to retrive JSON list");
            return new ArrayList<Reminder>();
        }
    }
    
    
}
