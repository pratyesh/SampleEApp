package com.ledexpo.android;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.prt.app.api.HTTP_ApiRequest;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.parser.JsonParser;
import com.prt.app.parser.TemplateParser;
import com.prt.app.services.DBUpdateService;
import com.prt.app.util.Constants;
import com.prt.app.util.Utility;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Pratyesh Singh
 */
public class Splash extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    static int notification_id;
    public boolean isMessage = false;

    protected void onMessage(Context context, Intent data) {
        String message = data.getStringExtra("payload");
        if (message == null)
            return;
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


    private void init() {
        try {
            SharedPreferences sf = getSharedPreferences("saturn_mobi_app", Context.MODE_PRIVATE);
            boolean dbflag = sf.getBoolean("copy_db", false);
            if (!dbflag) {
                Utility.copyAssets(Splash.this, "saturnmobi.sqlite", "copy_db");
            }

            dbflag = sf.getBoolean("html_db", false);
            if (!dbflag) {
                Utility.copyAssets(Splash.this, "html.zip", "html_db");
                String outFileName = "/data/data/" + getPackageName() + "/databases/" + "html.zip";
                Utility.extractZip(outFileName);
                /**** ----------Here setting default values for banner and html contents--------------------- **/
                if (true) {
                    Editor ed = sf.edit();
                    ed.putInt("event_version", 1);
                    ed.putInt("html_version", 1);
                    ed.putInt("banner_version", 1);
                    ed.commit();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash);

        init();


        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    onMessage(context, intent);
                    System.out.println(getString(R.string.gcm_send_message));
                } else {
                    System.out.println(getString(R.string.token_error_message));
                }
            }
        };

        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(Splash.this, RegistrationIntentService.class);
            startService(intent);
        }

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            String fileName = "event.xml";
            InputStream inputStream;

            saxParser = saxParserFactory.newSAXParser();
            XMLReader xmlReader = saxParser.getXMLReader();

            TemplateParser templateParser = new TemplateParser();
            xmlReader.setContentHandler(templateParser);

            inputStream = getAssets().open(fileName);

            xmlReader.parse(new InputSource(inputStream));
            final Intent intent = new Intent("android.intent.action.ledexpo.Home");
            intent.putExtra("eventmap", templateParser.getEventBean());
            intent.putExtra("action", templateParser.getEventBean().getEventMap().get(0).getBlankSplashBeansList().get(0).getAction());
            // intent.putExtra("templateId", templateParser.getEventBean().getEventMap().get(0).getTemplateId());

            final ProgressDialog dialog = new ProgressDialog(this);
            dialog.setMessage("Loading...");
            // dialog.show();

            if (android.os.Build.VERSION.SDK_INT > 9) {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
            }

            Handler handler = new Handler(new Handler.Callback() {

                @Override
                public boolean handleMessage(Message msg) {
                    return false;
                }
            });
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {


                    if (Utility.checkInternetConnection(Splash.this)) {
//                        startService(new Intent("com.prt.app.services.ledexpo.DBUpdateService"));
                        startService(new Intent(Splash.this, DBUpdateService.class));
                    }

                    if (dialog.isShowing())
                        dialog.dismiss();
                    startActivity(intent);
                    finish();
                }
            }, templateParser.getEventBean().getEventMap().get(0).getBlankSplashBeansList().get(0).getTime());

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver, new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    /**
     * Check the device to make sure it has the Google Play Services APK. If
     * it doesn't, display a dialog that allows users to download the APK from
     * the Google Play Store or enable it in the device's system settings.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FlurryAgent.setCaptureUncaughtExceptions(true);
        FlurryAgent.onStartSession(this, Constants.flurryKey);
        FlurryAgent.logEvent(Constants.FLURRY_SPLASH);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FlurryAgent.onEndSession(this);
    }
}
