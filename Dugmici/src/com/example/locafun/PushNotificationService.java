package com.example.locafun;

import com.example.dugmici.R;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

/**
 * Handles push notifications from walkabout.com received by
 * {@link PushNotificationBroadcastReceiver}
 * 
 * @author gdjonovic
 * 
 */
public class PushNotificationService extends IntentService {

	private static String TAG = "PushNotificationService";
	public static final int NOTIFICATION_ID = 1;
	public static final String LATITUDE = "Latitude";
	public static final String LONGITUDE = "Longitude";
	public static final int BLUE_COLOR = 0xFF0000FF;
	public static final int LIGHT_ON_TIME = 500; // ms
	public static final int LIGHT_OFF_TIME = 1500; // ms
	private NotificationManager notificationManager;
	NotificationCompat.Builder builder;
	private static final long[] DEFAULT_VIBRATION_PATTERN = new long[] { 1000, 1000 };

	public PushNotificationService() {
		super(PushNotificationService.class.getSimpleName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();
		if (!extras.isEmpty()) {

				Log.i(TAG, "LATITUDE " + extras.getDouble(LATITUDE));
				postNotification(extras.getString("details"));
				Log.i(TAG, "Received: " + extras.toString());
			
		}
		// Release the wake lock provided by the WakefulBroadcastReceiver.
		PushNotificationBroadcastReceiver.completeWakefulIntent(intent);
	}

	/**
	 * Creates notification with message and post it
	 * 
	 * @param message
	 */
	private void postNotification(String message) {
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	      Intent masterActivityIntent = null;
	      masterActivityIntent = new Intent(this, MapActivity.class);
	        masterActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	        masterActivityIntent.setAction(Intent.ACTION_VIEW);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_launcher).setContentTitle("location changed")
				.setStyle(new NotificationCompat.BigTextStyle().bigText(message))
				.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
				.setVibrate(DEFAULT_VIBRATION_PATTERN).setContentText(message);

		// The stack builder object will contain an artificial back stack for
		// the started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		  stackBuilder.addParentStack(MapActivity.class);
	        // Adds the Intent that starts the Activity to the top of the stack
	        stackBuilder.addNextIntent(masterActivityIntent);
		// Adds the back stack for the Intent (but not the Intent itself)
		// Adds the Intent that starts the Activity to the top of the stack
		PendingIntent pendingIntent = stackBuilder.getPendingIntent( message.hashCode(),
				PendingIntent.FLAG_ONE_SHOT);
		// sets LED indicator on the device
		builder.setLights(BLUE_COLOR, LIGHT_ON_TIME, LIGHT_OFF_TIME);
		builder.setContentIntent(pendingIntent);
		builder.setAutoCancel(true);
		builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
		
		notificationManager.notify(message.hashCode(), builder.build());
	}

}
