package com.example.dugmici;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dugmici.model.Reminder;

import eu.execom.locafun.DataStorage;

public class AddRemindersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminders);
	
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                final EditText detailsText = (EditText) findViewById(R.id.taskDetail);
                final EditText taskText = (EditText) findViewById(R.id.taskName);
                Reminder reminder = new Reminder(taskText.getText().toString(), detailsText.getText().toString());
                DataStorage.get(AddRemindersActivity.this).saveReminder(reminder);
                Toast.makeText(AddRemindersActivity.this ,  reminder.toString() ,  Toast.LENGTH_LONG).show();
                finish();
            	
            }
        });
        
        

	
	}
}
