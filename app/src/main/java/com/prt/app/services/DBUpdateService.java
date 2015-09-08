package com.prt.app.services;

import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.prt.app.api.HTTP_ApiRequest;
import com.prt.app.parser.JsonParser;
import com.prt.app.util.Constants;
import com.prt.app.util.Utility;

public class DBUpdateService extends IntentService {
	public static final int UPDATE_PROGRESS = 8344;

	public DBUpdateService(String name) {
		super(name);

	}

	public DBUpdateService() {
		super("DBUpdateService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		// final ResultReceiver receiver = (ResultReceiver) intent.getParcelableExtra("receiver");

		System.out.println("download zip of update service");

		try {
			SharedPreferences sf = getSharedPreferences("saturn_mobi_app", Context.MODE_PRIVATE);
			int exhibitor_version = 0;
			int speaker_version = 0;
			int scheduler_version = 0;
			int gallery_version = 0;
			int floor_version = 0;
			int presentation_version = 0;
			try {
				exhibitor_version = sf.getInt("exhibitor_version", 0);
				speaker_version = sf.getInt("speaker_version", 0);
				scheduler_version = sf.getInt("scheduler_version", 0);
				gallery_version = sf.getInt("gallery_version", 0);
				floor_version = sf.getInt("floor_version", 0);
				presentation_version = sf.getInt("presentation_version", 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/**** ----------Exhibitor--------------------- **/
			me: if (true) {
				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("exhibitor_version", exhibitor_version);
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.exhibitor_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					if (!da.has("exhibitors"))
						break me;
					JsonParser.parseAndSaveExhibitors(da.getJSONArray("exhibitors"), DBUpdateService.this);
					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("exhibitor_version", version);
					ed.commit();
				}
			}
			/**** ----------Speaker--------------------- **/
			me: if (true) {
				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("speaker_version", speaker_version);
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.speaker_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					if (!da.has("speakers"))
						break me;
					JsonParser.parseAndSaveSpeaker(da.getJSONArray("speakers"), DBUpdateService.this);
					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("speaker_version", version);
					ed.commit();
				}
			}
			/**** ----------Schedule--------------------- **/
			me: if (true) {

				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("scheduler_version", scheduler_version);
				request_body.put("group", "");
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.scheduler_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					if (!da.has("schedule"))
						break me;
					JsonParser.parseAndSaveSchedule(da.getJSONArray("schedule"), DBUpdateService.this);
					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("scheduler_version", version);
					ed.commit();
				}
			}
			/**** ----------Gallery--------------------- **/
			me: if (true) {

				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("gallery_version", gallery_version);
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.gallery_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					if (!da.has("gallery"))
						break me;
					JsonParser.parseAndSaveGallery(da.getJSONArray("gallery"), DBUpdateService.this);
					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("gallery_version", version);
					ed.commit();
				}
			}

			/**** ----------Floor Plan--------------------- **/
			me: if (true) {

				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("floor_version", floor_version);
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.floors_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					if (!da.has("floor"))
						break me;
					JsonParser.parseAndSaveFloorPlan(da.getJSONArray("floor"), DBUpdateService.this);
					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("floor_version", version);
					ed.commit();
				}
			}
			/**** ----------Presentation--------------------- **/
			me: if (true) {

				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("presentation_version", presentation_version);
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.presentations_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					if (!da.has("presentation"))
						break me;
					JsonParser.parseAndSavePresentation(da.getJSONArray("presentation"), DBUpdateService.this);
					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("presentation_version", version);
					ed.commit();
				}
			}
			// }
			/**** ----------Event Banner and Html--------------------- **/
			if (true) {
				int event_version = 0;
				int html_version = 0;
				int banner_version = 0;
				try {
					event_version = sf.getInt("event_version", 0);
					html_version = sf.getInt("html_version", 0);
					banner_version = sf.getInt("banner_version", 0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Hashtable<String, String> header = new Hashtable<String, String>();
				JSONObject request_body = new JSONObject();
				request_body.put("event_id", Constants.EVENT_ID);
				request_body.put("event_version", event_version);
				request_body.put("html_version", html_version);
				request_body.put("banner_version", banner_version);
				Object[] obj = HTTP_ApiRequest.getApiResult(Constants.events_Url, request_body.toString(), "POST", header);
				int i = (Integer) obj[0];
				if (i == 200) {
					JSONObject da = (JSONObject) obj[1];
					int eventId = 1;
					if (da.has("events")) {
						JSONObject events = da.getJSONObject("events");
						eventId = events.getInt("eventId");

						Editor ed = sf.edit();
						ed.putInt("eventId", eventId);
						ed.putString("event_name", events.getString("event_name"));
						ed.putString("ticker", events.getString("ticker"));
						ed.putString("description", events.getString("description"));
						ed.putInt("status", events.getInt("status"));
						ed.commit();

					}
					if (da.has("banner")) {
						JSONArray banner = da.getJSONArray("banner");
						banner_version = Integer.parseInt(da.getString("banner_version"));
						JsonParser.parseAndSaveBanner(banner, DBUpdateService.this, eventId);
						Editor ed = sf.edit();
						ed.putInt("banner_version", banner_version);
						ed.commit();
					}
					if (da.has("html")) {
						JSONObject html = da.getJSONObject("html");
						if (Integer.parseInt(da.getString("html_version")) > html_version) {

							Utility.downloadZip(html.getString("html_url"), "html.zip", DBUpdateService.this);
							html_version = Integer.parseInt(da.getString("html_version"));
							Editor ed = sf.edit();
							ed.putInt("html_version", html_version);
							ed.commit();
						}

					}

					event_version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("event_version", event_version);
					ed.commit();

				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
