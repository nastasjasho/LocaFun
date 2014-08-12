package com.example.locafun;

import com.example.dugmici.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class HomeFragment extends Fragment {


	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();

		return fragment;
	}

	public HomeFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);
		
final Button btnWalk = (Button) rootView.findViewById(R.id.walk);

		//demonstracija
        btnWalk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent walkIntent = new Intent(getActivity(),
        				MapActivity.class);
            	walkIntent.putExtra("ACTION_TYPE","Walk");
        		startActivity(walkIntent);
        }});
        
        final Button buttonByke = (Button) rootView.findViewById(R.id.byke);
       
      buttonByke.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        Intent bykeIntent = new Intent(getActivity(),
				MapActivity.class);
    	bykeIntent.putExtra("ACTION_TYPE","Byke");
		startActivity(bykeIntent);
                
        }});
        
        final Button buttonBus = (Button) rootView.findViewById(R.id.bus);
        
        buttonBus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Intent busIntent = new Intent(getActivity(),
		MapActivity.class);
    	busIntent.putExtra("ACTION_TYPE","Bus");
		startActivity(busIntent);
        }});
        
        final Button buttonCar = (Button) rootView.findViewById(R.id.autocar);
        
        buttonCar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent carIntent = new Intent(getActivity(),
		MapActivity.class);
        carIntent.putExtra("ACTION_TYPE","Car");
		startActivity(carIntent);
       }});
        
		return rootView;
	}
}
