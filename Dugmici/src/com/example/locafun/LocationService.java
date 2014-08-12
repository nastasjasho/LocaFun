package com.example.locafun;

import java.util.ArrayList;
import java.util.List;

import com.example.dugmici.model.FunLocation;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service {
	public static final String BROADCAST_ACTION = "LocationChangedAction";
	private static final int TWO_MINUTES = 1000 * 60 * 2;
	public LocationManager locationManager;
	public MyLocationListener listener;
	public Location previousBestLocation = null;
	private List<FunLocation> locationList = new ArrayList<FunLocation>();

	Intent intent;
	int counter = 0;

	@Override
	public void onCreate() {
		super.onCreate();
		intent = new Intent(BROADCAST_ACTION);
		Location targetLocation = new Location("");// provider name is
													// unecessary
		targetLocation.setLatitude(45.255124);// your coords of course
		targetLocation.setLongitude(19.842208);
		locationList.add(new FunLocation(targetLocation,
				"Jeeej stigla si kuci skoro", 5));

	}

	@Override
	public void onStart(Intent intent, int startId) {
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		listener = new MyLocationListener();
		locationManager.requestLocationUpdates(
				LocationManager.NETWORK_PROVIDER, 4000, 0, listener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				4000, 0, listener);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	protected FunLocation isBetterLocation(Location location,
			Location currentBestLocation) {
		Location targetLocation = new Location("");// provider name is
													// unecessary
		targetLocation.setLatitude(45.255124);// your coords of course
		targetLocation.setLongitude(19.842208);
		if (locationList.size() == 0)
			locationList.add(new FunLocation(targetLocation,
					"Jeeej stigla si kuci skoro", 5));
		if (location.distanceTo(locationList.get(0).getLocation()) < 50)
			return locationList.get(0);
		return null;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
		if (provider1 == null) {
			return provider2 == null;
		}
		return provider1.equals(provider2);
	}

	@Override
	public void onDestroy() {
		// handler.removeCallbacks(sendUpdatesToUI);
		super.onDestroy();
		Log.v("STOP_SERVICE", "DONE");
		locationManager.removeUpdates(listener);
	}

	public static Thread performOnBackgroundThread(final Runnable runnable) {
		final Thread t = new Thread() {
			@Override
			public void run() {
				try {
					runnable.run();
				} finally {

				}
			}
		};
		t.start();
		return t;
	}

	public class MyLocationListener implements LocationListener {

		public void onLocationChanged(final Location loc) {
			Log.i("**************************************", "Location changed");
			FunLocation funLocation = isBetterLocation(loc,
					previousBestLocation);
			if (funLocation != null) {
				loc.getLatitude();
				loc.getLongitude();
				intent.putExtra("Latitude", loc.getLatitude());
				intent.putExtra("Longitude", loc.getLongitude());
				intent.putExtra("Provider", loc.getProvider());
				intent.putExtra("details", funLocation.getDetails());
				sendBroadcast(intent);

			}
		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Disabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Enabled",
					Toast.LENGTH_SHORT).show();
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

	}
}
