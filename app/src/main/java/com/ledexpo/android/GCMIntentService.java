package com.ledexpo.android;

import java.util.Hashtable;
import java.util.Random;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.util.Log;
import android.widget.RemoteViews;/*
import com.google.android.gcm.GCMBaseIntentService;*/
import com.ledexpo.android.R;
import com.prt.app.api.HTTP_ApiRequest;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.util.Constants;

/**
 * @author Pratyesh Singh
 */

public class GCMIntentService/* extends GCMBaseIntentService*/ {/*
	static int notification_id;
	public String registration_ID = null;
	public boolean isMessage = false;
	// Global Hse SENDER_ID is Project ID: potent-catwalk-769 Project Number: 1010061006675
	public static final String SENDER_ID = "1010061006675";
	private Intent notificationIntent;

	public GCMIntentService() {
		super(SENDER_ID);
	}

	@Override
	protected void onRegistered(Context context, String registration_ID) {

		Log.i(TAG, "Regsited: registrationId=" + registration_ID);
		this.registration_ID = registration_ID;
		if (registration_ID.trim().equals(""))
			Log.i(TAG, "Already Regsited: registrationId=" + registration_ID);
		else
			sendTokenToMyServer();
	}

	private void sendTokenToMyServer() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				String android_id = Secure.getString(GCMIntentService.this.getContentResolver(), Secure.ANDROID_ID);
				String searchUrl = Constants.push_Url + registration_ID + "&device_id=" + android_id + "&event_id=" + Constants.EVENT_ID;
				String requestType = "GET";
				String request_body = "";
				Hashtable<String, String> header = new Hashtable<String, String>();

				final Object[] response = HTTP_ApiRequest.getApiResult(searchUrl, request_body, requestType, header);
				// {"status":"success","msg":"data saved"}
			}
		}).start();

	}

	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "onUnregistered: registrationId=" + registrationId);
	}

	@Override
	protected void onMessage(Context context, Intent data) {
		String message = data.getStringExtra("payload");

		// int event_id = data.getIntExtra("event_id", 6);
		// int notification_id = data.getIntExtra("notification_id", 0);
		// String time = data.getStringExtra("time");
		// int status = data.getIntExtra("status", 0);
		// String title = data.getStringExtra("title");

		int event_id = 6;
		notification_id++;
		String time = "" + System.currentTimeMillis();
		int status = 1;
		String title = "LED Expo 2015";

		ExecutionQuery exq = new ExecutionQuery(context, 7);
		exq.configureNotificationTable();
		long check = exq.insertNotification(event_id, notification_id, message, time, status, title);
		if (check == 0) {
			exq.updateNotification(event_id, notification_id, message, time, status, title);
		}
		exq.closeDatabase();

		generateNotification(context, title, message);
		isMessage = true;
	}

	private void generateNotification(Context context, String title, String message) {

		int icon = R.drawable.app_icon;
		long when = System.currentTimeMillis();

		RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification_layout);
		contentView.setImageViewResource(R.id.image, R.drawable.app_icon);
		contentView.setTextViewText(R.id.text, message);
		contentView.setTextViewText(R.id.app_name, context.getResources().getString(R.string.app_name));
		// CharSequence tickerText = message;

		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		notification.contentView = contentView;

		Intent notificationIntent = new Intent(context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()));
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		notificationIntent.putExtra("push_message", message);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		// notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.contentIntent = contentIntent;

		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);
	}

	@Override
	protected void onError(Context arg0, String errorId) {

		Log.e(TAG, "onError: errorId=" + errorId);
	}
*/
}