package com.example.dugmici;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dugmici.model.Reminder;

import eu.execom.locafun.DataStorage;

public class RemindersFragment extends Fragment {

    private Button buttonAdd;
    private ListView remindersListView;
	public RemindersFragment() {

	}

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static RemindersFragment newInstance() {
		RemindersFragment fragment = new RemindersFragment();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_reminders,
				container, false);
		
		remindersListView = (ListView) rootView
                .findViewById(R.id.listView1);
        remindersListView.setDivider(new ColorDrawable(Color.WHITE));
        remindersListView.setDividerHeight(3);
        
        
		buttonAdd = new Button(getActivity());
		buttonAdd.setText("Add reminder");
		buttonAdd.setTextColor(Color.WHITE);
	      
        remindersListView.addFooterView(buttonAdd);
        
        remindersListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reminder reminder = (Reminder) parent.getAdapter().getItem(position);
                Toast.makeText(getActivity(), "Select reminder: " +  reminder.toString(), Toast.LENGTH_LONG).show();
            }
        });
		buttonAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent addIntent = new Intent(getActivity(),
						AddRemindersActivity.class);
				startActivity(addIntent);
			}
		});
		return rootView;
	}
	
	@Override
	public void onResume() {
	    // TODO Auto-generated method stub
	    super.onResume();
        remindersListView.setAdapter(new RemindersListAdapter(getActivity(), android.R.layout.simple_expandable_list_item_1, DataStorage.get(getActivity()).getReminders()));

	}
}
