package com.example.locafun;


import com.example.dugmici.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MapActivity extends Activity implements
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener{

	LocationClient locationClient;

	
	@Override
	protected void onStart() {
		super.onStart();
		locationClient.connect();
	};
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		locationClient.disconnect();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle arg0) {
		MapFragment mapFragment = ((MapFragment) this.getFragmentManager().findFragmentById(R.id.map));
		
		MarkerOptions mo = new MarkerOptions();
		mo.position(new LatLng(locationClient.getLastLocation().getLatitude(),locationClient.getLastLocation().getLongitude()));
		mo.title("Its meeeee");
		
		
		mapFragment.getMap().addMarker(mo);
		mapFragment.getMap().setMyLocationEnabled(true);
		mapFragment.getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationClient.getLastLocation().getLatitude(), locationClient.getLastLocation().getLongitude()), 15.0f));
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		locationClient = new LocationClient(this, this, this);
		//String actionType = getIntent().getExtras().getString("ACTION_TYPE");
		String actionType = "";

		Log.d("SecondActivity",
				"Ovo je debug poruka, bas je lepa, a action type je "
						+ actionType);

		TextView title = (TextView) findViewById(R.id.title);

		title.setText("You are in " + actionType + " mode");
		
	  
	}
}
