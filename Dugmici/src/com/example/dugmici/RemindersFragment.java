package com.example.dugmici;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RemindersFragment extends Fragment {

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
		final Button buttonAdd = (Button) rootView
				.findViewById(R.id.add_reminder);

		buttonAdd.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent addIntent = new Intent(getActivity(),
						AddRemindersActivity.class);
				startActivity(addIntent);
			}
		});
		return rootView;
	}
}
