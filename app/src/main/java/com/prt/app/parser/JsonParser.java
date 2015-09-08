package com.prt.app.parser;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

import com.prt.app.db.ExecutionQuery;

public class JsonParser {
	public static void parseAndSaveSchedule(JSONArray res, Context activity) {
		ExecutionQuery exq = new ExecutionQuery(activity, 3);
		exq.configureScheduleTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);

				int schedule_id = 0;
				if (data.has("id"))
					schedule_id = data.getInt("id");

				String desc = "";
				if (data.has("description"))
					desc = data.getString("description");

				int event_id = 0;
				if (data.has("eventId"))
					event_id = data.getInt("eventId");

				String end_time = "";
				if (data.has("endTime"))
					end_time = data.getString("endTime");

				String start_time = "";
				if (data.has("startTime"))
					start_time = data.getString("startTime");

				String schedule_date = "";
				if (data.has("scheduleDate"))
					schedule_date = data.getString("scheduleDate");

				String name = "";
				if (data.has("scheduleName"))
					name = data.getString("scheduleName");

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				String speakers = "";
				if (data.has("speaker"))
					speakers = data.getString("speaker");

				String scheduleType = "";
				if (data.has("scheduleType"))
					scheduleType = data.getString("scheduleType");

				int alert_status = 0;
				String alert_id = "0";

				long check = exq.insertSchedule(schedule_id, name, desc, schedule_date, start_time, end_time, event_id, speakers, status, alert_status, alert_id, scheduleType);

				if (check == 0) {
					exq.updateSchedule(schedule_id, name, desc, schedule_date, start_time, end_time, event_id, speakers, status, alert_status, alert_id, scheduleType);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}

	public static void parseAndSaveGallery(JSONArray res, Context activity) {
		ExecutionQuery exq = new ExecutionQuery(activity, 4);
		exq.configureGalleryTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);
				int image_id = 0;
				if (data.has("id"))
					image_id = data.getInt("id");

				int event_id = 0;
				if (data.has("eventId"))
					event_id = data.getInt("eventId");

				String thumb_url = "";
				if (data.has("thumb_url"))
					thumb_url = data.getString("thumb_url");

				String image_url = "";
				if (data.has("image_url"))
					image_url = data.getString("image_url");

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				String category = "";
				if (data.has("category"))
					category = data.getString("category");

				long check = exq.insertGallery(event_id, image_id, category, thumb_url, image_url, status);
				if (check == 0) {
					try {
						// update here
						exq.updateGallery(event_id, image_id, category, thumb_url, image_url, status);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}

	public static void parseAndSaveBanner(JSONArray res, Context activity, int eventId) {
		ExecutionQuery exq = new ExecutionQuery(activity, 6);
		exq.configureBannerTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);
				int banner_id = 0;
				if (data.has("banner_id"))
					banner_id = data.getInt("banner_id");

				String banner_url = "";
				if (data.has("banner_url"))
					banner_url = data.getString("banner_url");

				String time = "";
				if (data.has("time"))
					time = data.getString("time");

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				// int eventId = data.getInt("");
				String link_url = "";
				if (data.has("link_url"))
					link_url = data.getString("link_url");

				long check = exq.insertBanner(banner_id, banner_url, time, status, eventId, link_url);
				if (check == 0) {
					try {
						// update here
						exq.updateBanner(banner_id, banner_url, time, status, eventId, link_url);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}

	public static void parseAndSaveFloorPlan(JSONArray res, Context activity) {
		ExecutionQuery exq = new ExecutionQuery(activity, 5);
		exq.configureFloorPlanTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);

				int image_id = 0;
				if (data.has("id"))
					image_id = data.getInt("id");

				int event_id = 0;
				if (data.has("eventId"))
					event_id = data.getInt("eventId");

				// String thumb_url = data.getString("thumb_url");
				String image_url = "";
				if (data.has("image_url"))
					image_url = data.getString("image_url");

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				String category = "";
				if (data.has("category"))
					category = data.getString("category");

				long check = exq.insertFloorplan(event_id, image_id, category, image_url, status);
				if (check == 0) {
					try {
						// update here
						exq.updateFloorplan(event_id, image_id, category, image_url, status);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}

	public static void parseAndSavePresentation(JSONArray res, Context activity) {
		ExecutionQuery exq = new ExecutionQuery(activity, 8);
		exq.configurePresentationTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);

				int presentation_id = 0;
				if (data.has("id"))
					presentation_id = data.getInt("id");

				int event_id = 0;
				if (data.has("eventId"))
					event_id = data.getInt("eventId");

				String presentation_data = "";
				if (data.has("url"))
					presentation_data = data.getString("url");

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				String presentation_name = "";
				if (data.has("category"))
					presentation_name = data.getString("category");

				String presentation_type = "";

				long check = exq.insertPresentation(event_id, presentation_id, presentation_name, presentation_type, presentation_data, status);
				if (check == 0) {
					try {
						// update here
						exq.updatePresentation(event_id, presentation_id, presentation_name, presentation_type, presentation_data, status);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}

	public static void parseAndSaveSpeaker(JSONArray res, Context activity) {
		ExecutionQuery exq = new ExecutionQuery(activity, 2);
		exq.configureSpeakerTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);

				String email_id = "";
				if (data.has("email"))
					email_id = data.getString("email");

				int event_id = 0;
				if (data.has("eventId"))
					event_id = data.getInt("eventId");

				String company_name = "";
				if (data.has("companyName"))
					company_name = data.getString("companyName");

				String speaker_name = "";
				if (data.has("speakerName"))
					speaker_name = data.getString("speakerName");

				String designation = "";
				if (data.has("designation"))
					designation = data.getString("designation");

				String twitter_id = "";
				if (data.has("twitterId"))
					twitter_id = data.getString("twitterId");

				String facebook_id = "";
				if (data.has("facebookId"))
					facebook_id = data.getString("facebookId");

				int speaker_id = 0;
				if (data.has("id"))
					speaker_id = data.getInt("id");

				String image = "";
				if (data.has("image"))
					image = data.getString("image");

				String desc = "";
				if (data.has("description"))
					desc = data.getString("description");

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				if (data.has("imageAvail"))
					data.getString("imageAvail");

				if (data.has("linkedin"))
					data.getString("linkedin");

				int favourites = 0;
				String notes = "";
				String contact_number = "";
				String country = "";
				String url = "";
				String schedule = "";
				if (data.has("session"))
					schedule = data.getString("session");

				long check = exq.insertSpeaker(event_id, company_name, speaker_name, country, image, desc, email_id, url, facebook_id, twitter_id, favourites, notes, contact_number, speaker_id, designation,
						status, schedule);
				if (check == 0) {
					try {
						// update here
						exq.updateSpeaker(event_id, company_name, speaker_name, country, image, desc, email_id, url, facebook_id, twitter_id, favourites, notes, contact_number, speaker_id, designation, status,
								schedule);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}

	public static void parseAndSaveExhibitors(JSONArray res, Context activity) {

		ExecutionQuery exq = new ExecutionQuery(activity, 1);
		exq.configureExhibitorTable();
		try {
			for (int i = 0; i < res.length(); i++) {
				JSONObject data = res.getJSONObject(i);

				int status = 0;
				if (data.has("status"))
					status = data.getInt("status");

				String product_info = "";
				if (data.has("productInfo"))
					product_info = data.getString("productInfo");

				String company_name = "";
				if (data.has("companyName"))
					company_name = data.getString("companyName");

				String twitter_id = "";
				if (data.has("twitter_url"))
					twitter_id = data.getString("twitter_url");

				String url = "";
				if (data.has("url"))
					url = data.getString("url");

				String country = "";
				if (data.has("country"))
					country = data.getString("country");

				int exhibitor_id = 0;
				if (data.has("id"))
					exhibitor_id = data.getInt("id");

				String image = "";
				if (data.has("image"))
					image = data.getString("image");

				String hall_number = "";
				if (data.has("hallNum"))
					hall_number = data.getString("hallNum");

				String booth_number = "";
				if (data.has("boothNum"))
					booth_number = data.getString("boothNum");

				int event_id = 0;
				if (data.has("eventId"))
					event_id = data.getInt("eventId");

				String email_id = "";
				if (data.has("email"))
					email_id = data.getString("email");

				String desc = "";
				if (data.has("description"))
					desc = data.getString("description");

				String facebook_id = "";
				if (data.has("facebook_url"))
					facebook_id = data.getString("facebook_url");

				String contact_person = "";
				if (data.has("contactPerson"))
					contact_person = data.getString("contactPerson");

				if (data.has("imageAvail"))
					data.getString("imageAvail");

				if (data.has("linkedin"))
					data.getString("linkedin");

				int favourites = 0;
				String notes = "";
				String floor_info = "";
				if (data.has("floorInfo"))
					floor_info = data.getString("floorInfo");
				String contact_number = "";

				long check = exq.insertExhibitor(event_id, company_name, contact_person, country, hall_number, booth_number, image, desc, product_info, email_id, url, facebook_id, twitter_id, favourites,
						notes, floor_info, contact_number, exhibitor_id, status);

				if (check == 0) {
					try {
						// update here
						exq.updateExhibitor(event_id, company_name, contact_person, country, hall_number, booth_number, image, desc, product_info, email_id, url, facebook_id, twitter_id, favourites, notes,
								floor_info, contact_number, exhibitor_id, status);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		exq.closeDatabase();
	}
}
