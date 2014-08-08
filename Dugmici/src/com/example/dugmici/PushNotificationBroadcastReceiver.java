package com.example.dugmici;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * Receives push notifications from walkabou.com and calls handler
 * {@link PushNotificationService}
 * 
 * @author gdjonovic
 * 
 */
public class PushNotificationBroadcastReceiver extends WakefulBroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Explicitly specify that PushNotificationService will handle the
		// intent.
		ComponentName comp = new ComponentName(context.getPackageName(), PushNotificationService.class.getName());
		// Start the service, keeping the device awake while it is launching.
		startWakefulService(context, (intent.setComponent(comp)));
	}

}
