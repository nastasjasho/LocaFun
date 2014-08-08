package com.example.dugmici;



import com.example.dugmici.model.Reminder;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddRemindersActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_reminders);
	
		final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                final EditText detailsText = (EditText) findViewById(R.id.editText2);
Reminder reminder1 = new Reminder(detailsText.getText().toString(), "");
                Toast.makeText(AddRemindersActivity.this ,  reminder1.toString() ,  Toast.LENGTH_LONG).show();

            	
            }
        });
        
        

	
	}
}
