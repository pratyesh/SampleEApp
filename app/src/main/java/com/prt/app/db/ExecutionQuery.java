package com.prt.app.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.prt.app.bean.BannerBean;
import com.prt.app.bean.ExhibitorBean;
import com.prt.app.bean.NotificationBean;
import com.prt.app.bean.ScheduleBean;
import com.prt.app.bean.SpeakerBean;

/**
 * @author Pratyesh Singh
 */
public class ExecutionQuery {
	private SQLiteDatabase db;
	private SQLiteStatement insertStmt;
	DBConnection dBConnection = null;

	private String EXHIBITOR_TABLE_NAME = "exhibitor"; // type 1
	private String SPEAKER_TABLE_NAME = "speaker";// type 2
	private String SCHEDULE_TABLE_NAME = "schedule";// type 3
	private String GALLERY_TABLE_NAME = "gallery";// type 4
	private String FLOOR_PLAN_TABLE_NAME = "floor_plan";// type 5
	private String BANNER_TABLE_NAME = "banner";// type 6
	private String NOTIFICATION_TABLE_NAME = "notification";// type 7
	private String PRESENTATION_TABLE_NAME = "presentation";// type 8

	private final String INSERT_EXHIBITOR = "insert into "
			+ EXHIBITOR_TABLE_NAME
			+ "(event_id,company_name,contact_person,country,hall_number,booth_number,image,desc,product_info,email_id,url,facebook_id,twitter_id,favourites,notes,floor_info,contact_number,exhibitor_id,status) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17,?18,?19)";

	private final String INSERT_SPEAKER = "insert into "
			+ SPEAKER_TABLE_NAME
			+ "(event_id,company_name,speaker_name,country,image,desc,email_id,url,facebook_id,twitter_id,favourites,notes,contact_number,speaker_id,designation,status,schedule) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12,?13,?14,?15,?16,?17)";

	private final String INSERT_SCHEDULE = "insert into " + SCHEDULE_TABLE_NAME
			+ "(schedule_id,name,desc,schedule_date,start_time,end_time,event_id,speakers,status,alert_status,alert_id,group_name) VALUES (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)";

	private final String INSERT_GALLERY = "insert into " + GALLERY_TABLE_NAME + "(event_id,image_id,category,thumb_url,image_url,status) VALUES (?1,?2,?3,?4,?5,?6)";
	private final String INSERT_FLOOR_PPLAN = "insert into " + FLOOR_PLAN_TABLE_NAME + "(event_id,image_id,floor_name,image_url,status) VALUES (?1,?2,?3,?4,?5)";
	private final String INSERT_PRESENTATION = "insert into " + PRESENTATION_TABLE_NAME + "(event_id,presentation_id,presentation_name,presentation_type,presentation_data,status) VALUES (?1,?2,?3,?4,?5,?6)";
	private final String INSERT_BANNER = "insert into " + BANNER_TABLE_NAME + "(banner_id,banner_url,time,status,eventId,link_url) VALUES (?1,?2,?3,?4,?5,?6)";
	private final String INSERT_NOTIFICATION = "insert into " + NOTIFICATION_TABLE_NAME + "(event_id,notification_id,message,time,status,title) VALUES (?1,?2,?3,?4,?5,?6)";

	/***
	 * EXHIBITOR_TABLE_NAME = "exhibitor"; // type 1 <br>
	 * SPEAKER_TABLE_NAME = "speaker";// type 2 <br>
	 * SCHEDULE_TABLE_NAME = "schedule";// type 3 <br>
	 * GALLERY_TABLE_NAME = "gallery";// type 4 <br>
	 * FLOOR_PLAN_TABLE_NAME = "floor_plan";// type 5 <br>
	 * BANNER_TABLE_NAME = "banner";// type 6 <br>
	 * NOTIFICATION_TABLE_NAME = "banner";// type 7<br>
	 * PRESENTATION_TABLE_NAME = "presentation";// type 8
	 */
	public ExecutionQuery(Context context, int type) {

		switch (type) {
		case 1:
			dBConnection = new DBConnection(context, EXHIBITOR_TABLE_NAME);
			break;
		case 2:
			dBConnection = new DBConnection(context, SPEAKER_TABLE_NAME);
			break;
		case 3:
			dBConnection = new DBConnection(context, SCHEDULE_TABLE_NAME);
			break;
		case 4:
			dBConnection = new DBConnection(context, GALLERY_TABLE_NAME);
			break;
		case 5:
			dBConnection = new DBConnection(context, FLOOR_PLAN_TABLE_NAME);
			break;
		case 6:
			dBConnection = new DBConnection(context, BANNER_TABLE_NAME);
			break;
		case 7:
			dBConnection = new DBConnection(context, NOTIFICATION_TABLE_NAME);
			break;
		case 8:
			dBConnection = new DBConnection(context, PRESENTATION_TABLE_NAME);
			break;
		default:
			break;

		}
		this.db = dBConnection.getWritableDatabase();

	}

	public void closeDatabase() {
		db.close();
	}

	public void configureNotificationTable() {
		this.insertStmt = this.db.compileStatement(INSERT_NOTIFICATION);
	}

	public void configureScheduleTable() {
		this.insertStmt = this.db.compileStatement(INSERT_SCHEDULE);
	}

	public void configureGalleryTable() {
		this.insertStmt = this.db.compileStatement(INSERT_GALLERY);
	}

	public void configureBannerTable() {
		this.insertStmt = this.db.compileStatement(INSERT_BANNER);
	}

	public void configureFloorPlanTable() {
		this.insertStmt = this.db.compileStatement(INSERT_FLOOR_PPLAN);
	}

	public void configurePresentationTable() {
		this.insertStmt = this.db.compileStatement(INSERT_PRESENTATION);
	}

	public void configureSpeakerTable() {
		this.insertStmt = this.db.compileStatement(INSERT_SPEAKER);
	}

	public void configureExhibitorTable() {
		this.insertStmt = this.db.compileStatement(INSERT_EXHIBITOR);
	}

	public void updateExhibitor(int event_id, String company_name, String contact_person, String country, String hall_number, String booth_number, String image, String desc, String product_info,
			String email_id, String url, String facebook_id, String twitter_id, int favourites, String notes, String floor_info, String contact_number, int exhibitor_id, int status) {

		String sql = "update " + EXHIBITOR_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";
		if (!company_name.trim().equals(""))
			sql = sql + "company_name = \"" + company_name + "\",";
		if (!contact_person.trim().equals(""))
			sql = sql + "contact_person = \"" + contact_person + "\",";
		if (!country.trim().equals(""))
			sql = sql + "country = \"" + country + "\",";
		if (!hall_number.trim().equals(""))
			sql = sql + "hall_number = \"" + hall_number + "\",";
		if (!booth_number.trim().equals(""))
			sql = sql + "booth_number = \"" + booth_number + "\",";
		if (!image.trim().equals(""))
			sql = sql + "image = \"" + image + "\",";
		if (!desc.trim().equals(""))
			sql = sql + "desc = \"" + desc + "\",";
		if (!product_info.trim().equals(""))
			sql = sql + "product_info = \"" + product_info + "\",";
		if (!email_id.trim().equals(""))
			sql = sql + "email_id = \"" + email_id + "\",";
		if (!url.trim().equals(""))
			sql = sql + "url = \"" + url + "\",";
		if (!facebook_id.trim().equals(""))
			sql = sql + "facebook_id = \"" + facebook_id + "\",";
		if (!twitter_id.trim().equals(""))
			sql = sql + "twitter_id = \"" + twitter_id + "\",";
		if (favourites != 0)
			sql = sql + "favourites = \"" + favourites + "\",";
		if (!notes.trim().equals(""))
			sql = sql + "notes = \"" + notes + "\",";
		if (!floor_info.trim().equals(""))
			sql = sql + "floor_info = \"" + floor_info + "\",";
		if (!contact_number.trim().equals(""))
			sql = sql + "contact_number = \"" + contact_number + "\",";
		sql = sql + "status = \"" + status + "\" where exhibitor_id=\"" + exhibitor_id + "\"";
		db.execSQL(sql);
	}

	public long insertExhibitor(int event_id, String company_name, String contact_person, String country, String hall_number, String booth_number, String image, String desc, String product_info,
			String email_id, String url, String facebook_id, String twitter_id, int favourites, String notes, String floor_info, String contact_number, int exhibitor_id, int status) {
		try {
			insertStmt.bindLong(1, event_id);
			insertStmt.bindString(2, company_name);
			insertStmt.bindString(3, contact_person);
			insertStmt.bindString(4, country);
			insertStmt.bindString(5, hall_number);
			insertStmt.bindString(6, booth_number);
			insertStmt.bindString(7, image);
			insertStmt.bindString(8, desc);
			insertStmt.bindString(9, product_info);
			insertStmt.bindString(10, email_id);
			insertStmt.bindString(11, url);
			insertStmt.bindString(12, facebook_id);
			insertStmt.bindString(13, twitter_id);
			insertStmt.bindLong(14, favourites);
			insertStmt.bindString(15, notes);
			insertStmt.bindString(16, floor_info);
			insertStmt.bindString(17, contact_number);
			insertStmt.bindLong(18, exhibitor_id);
			insertStmt.bindLong(19, status);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updateSpeaker(int event_id, String company_name, String speaker_name, String country, String image, String desc, String email_id, String url, String facebook_id, String twitter_id,
			int favourites, String notes, String contact_number, int speaker_id, String designation, int status, String schedule) {
		String sql = "update " + SPEAKER_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";
		// if (!designation.trim().equals(""))
		// sql = sql + "designation = \"" + designation + "\",";
		if (!company_name.trim().equals(""))
			sql = sql + "company_name = \"" + company_name + "\",";
		if (!speaker_name.trim().equals(""))
			sql = sql + "speaker_name = \"" + speaker_name + "\",";
		if (!country.trim().equals(""))
			sql = sql + "country = \"" + country + "\",";
		if (!image.trim().equals(""))
			sql = sql + "image = \"" + image + "\",";
		if (!desc.trim().equals(""))
			sql = sql + "desc = \"" + desc + "\",";
		if (!email_id.trim().equals(""))
			sql = sql + "email_id = \"" + email_id + "\",";
		if (!url.trim().equals(""))
			sql = sql + "url = \"" + url + "\",";
		if (!facebook_id.trim().equals(""))
			sql = sql + "facebook_id = \"" + facebook_id + "\",";
		if (!twitter_id.trim().equals(""))
			sql = sql + "twitter_id = \"" + twitter_id + "\",";
		if (favourites != 0)
			sql = sql + "favourites = \"" + favourites + "\",";
		if (!notes.trim().equals(""))
			sql = sql + "notes = \"" + notes + "\",";
		if (!notes.trim().equals(""))
			sql = sql + "notes = \"" + notes + "\",";
		if (!schedule.trim().equals(""))
			sql = sql + "schedule = \"" + schedule + "\",";
		if (!designation.trim().equals(""))
			sql = sql + "designation = \"" + designation + "\",";
		sql = sql + "status = \"" + status + "\" where speaker_id=\"" + speaker_id + "\"";
		db.execSQL(sql);
	}

	public long insertSpeaker(int event_id, String company_name, String speaker_name, String country, String image, String desc, String email_id, String url, String facebook_id, String twitter_id,
			int favourites, String notes, String contact_number, int speaker_id, String designation, int status, String schedule) {
		try {
			insertStmt.bindLong(1, event_id);
			insertStmt.bindString(2, company_name);
			insertStmt.bindString(3, speaker_name);
			insertStmt.bindString(4, country);
			insertStmt.bindString(5, image);
			insertStmt.bindString(6, desc);
			insertStmt.bindString(7, email_id);
			insertStmt.bindString(8, url);
			insertStmt.bindString(9, facebook_id);
			insertStmt.bindString(10, twitter_id);
			insertStmt.bindLong(11, favourites);
			insertStmt.bindString(12, notes);
			insertStmt.bindString(13, contact_number);
			insertStmt.bindLong(14, speaker_id);
			insertStmt.bindString(15, designation);
			insertStmt.bindLong(16, status);
			insertStmt.bindString(17, schedule);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updateSchedule(int schedule_id, String name, String desc, String schedule_date, String start_time, String end_time, int event_id, String speakers, int status, int alert_status, String alert_id,
			String scheduleType) {
		String sql = "update " + SCHEDULE_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";
		// if (schedule_id != 0)
		// sql = sql + "schedule_id = \"" + schedule_id + "\",";

		if (!name.trim().equals(""))
			sql = sql + "name = \"" + name + "\",";
		if (!schedule_date.trim().equals(""))
			sql = sql + "schedule_date = \"" + schedule_date + "\",";
		if (!start_time.trim().equals(""))
			sql = sql + "start_time = \"" + start_time + "\",";
		if (!end_time.trim().equals(""))
			sql = sql + "end_time = \"" + end_time + "\",";
		if (!desc.trim().equals(""))
			sql = sql + "desc = \"" + desc + "\",";
		if (!speakers.trim().equals(""))
			sql = sql + "speakers = \"" + speakers + "\",";
		if (alert_status != 0)
			sql = sql + "alert_status = \"" + alert_status + "\",";
		if (!alert_id.trim().equals(""))
			sql = sql + "alert_id = \"" + alert_id + "\",";
		if (!scheduleType.trim().equals(""))
			sql = sql + "group_name = \"" + scheduleType + "\",";

		sql = sql + "status = \"" + status + "\" where schedule_id=\"" + schedule_id + "\"";
		db.execSQL(sql);
	}

	public long insertSchedule(int schedule_id, String name, String desc, String schedule_date, String start_time, String end_time, int event_id, String speakers, int status, int alert_status, String alert_id,
			String scheduleType) {
		try {
			insertStmt.bindLong(1, schedule_id);
			insertStmt.bindString(2, name);
			insertStmt.bindString(3, desc);
			insertStmt.bindString(4, schedule_date);
			insertStmt.bindString(5, start_time);
			insertStmt.bindString(6, end_time);
			insertStmt.bindLong(7, event_id);
			insertStmt.bindString(8, speakers);
			insertStmt.bindLong(9, status);
			insertStmt.bindLong(10, alert_status);
			insertStmt.bindString(11, alert_id);
			insertStmt.bindString(12, scheduleType);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updateBanner(int banner_id, String banner_url, String time, int status, int eventId, String link_url) {
		String sql = "update " + BANNER_TABLE_NAME + " set ";

		if (!banner_url.trim().equals(""))
			sql = sql + "banner_url = \"" + banner_url + "\",";
		if (!link_url.trim().equals(""))
			sql = sql + "link_url = \"" + link_url + "\",";
		if (!time.trim().equals(""))
			sql = sql + "time = \"" + time + "\",";
		sql = sql + "status = \"" + status + "\" where banner_id=\"" + banner_id + "\"";
		db.execSQL(sql);
	}

	public long insertBanner(int banner_id, String banner_url, String time, int status, int eventId, String link_url) {
		try {
			insertStmt.bindLong(1, banner_id);
			insertStmt.bindString(2, banner_url);
			insertStmt.bindString(3, time);
			insertStmt.bindLong(4, status);
			insertStmt.bindLong(5, eventId);
			insertStmt.bindString(6, link_url);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updateGallery(int event_id, int image_id, String category, String thumb_url, String image_url, int status) {
		String sql = "update " + GALLERY_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";
		// if (image_id != 0)
		// sql = sql + "image_id = \"" + image_id + "\",";

		if (!category.trim().equals(""))
			sql = sql + "category = \"" + category + "\",";
		if (!thumb_url.trim().equals(""))
			sql = sql + "thumb_url = \"" + thumb_url + "\",";
		if (!image_url.trim().equals(""))
			sql = sql + "image_url = \"" + image_url + "\",";
		sql = sql + "status = \"" + status + "\" where image_id=\"" + image_id + "\"";
		db.execSQL(sql);
	}

	public long insertGallery(int event_id, int image_id, String category, String thumb_url, String image_url, int status) {
		try {
			insertStmt.bindLong(1, event_id);
			insertStmt.bindLong(2, image_id);
			insertStmt.bindString(3, category);
			insertStmt.bindString(4, thumb_url);
			insertStmt.bindString(5, image_url);
			insertStmt.bindLong(6, status);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updateFloorplan(int event_id, int image_id, String floor_name, String image_url, int status) {
		String sql = "update " + FLOOR_PLAN_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";

		if (!floor_name.trim().equals(""))
			sql = sql + "floor_name = \"" + floor_name + "\",";
		if (!image_url.trim().equals(""))
			sql = sql + "image_url = \"" + image_url + "\",";
		sql = sql + "status = \"" + status + "\" where image_id=\"" + image_id + "\"";
		db.execSQL(sql);
	}

	public long insertFloorplan(int event_id, int image_id, String floor_name, String image_url, int status) {
		try {
			insertStmt.bindLong(1, event_id);
			insertStmt.bindLong(2, image_id);
			insertStmt.bindString(3, floor_name);
			insertStmt.bindString(4, image_url);
			insertStmt.bindLong(5, status);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updatePresentation(int event_id, int presentation_id, String presentation_name, String presentation_type, String presentation_data, int status) {
		String sql = "update " + PRESENTATION_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";

		if (!presentation_name.trim().equals(""))
			sql = sql + "presentation_name = \"" + presentation_name + "\",";

		if (!presentation_type.trim().equals(""))
			sql = sql + "presentation_type = \"" + presentation_type + "\",";

		if (!presentation_data.trim().equals(""))
			sql = sql + "presentation_data = \"" + presentation_data + "\",";

		sql = sql + "status = \"" + status + "\" where presentation_id=\"" + presentation_id + "\"";
		db.execSQL(sql);
	}

	public long insertPresentation(int event_id, int presentation_id, String presentation_name, String presentation_type, String presentation_data, int status) {
		try {
			insertStmt.bindLong(1, event_id);
			insertStmt.bindLong(2, presentation_id);
			insertStmt.bindString(3, presentation_name);
			insertStmt.bindString(4, presentation_type);
			insertStmt.bindString(5, presentation_data);
			insertStmt.bindLong(6, status);

			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void updateNotification(int event_id, int notification_id, String message, String time, int status, String title) {
		String sql = "update " + NOTIFICATION_TABLE_NAME + " set ";
		// if (event_id != 0)
		// sql = sql + "event_id = \"" + event_id + "\",";
		// if (notification_id != 0)
		// sql = sql + "notification_id = \"" + notification_id + "\",";

		if (!message.trim().equals(""))
			sql = sql + "message = \"" + message + "\",";
		if (!time.trim().equals(""))
			sql = sql + "time = \"" + time + "\",";
		if (!title.trim().equals(""))
			sql = sql + "title = \"" + title + "\",";
		sql = sql + "status = \"" + status + "\" where notification_id=\"" + notification_id + "\"";
		db.execSQL(sql);
	}

	public long insertNotification(int event_id, int notification_id, String message, String time, int status, String title) {
		try {
			insertStmt.bindLong(1, event_id);
			insertStmt.bindLong(2, notification_id);
			insertStmt.bindString(3, message);
			insertStmt.bindString(4, time);
			insertStmt.bindLong(5, status);
			insertStmt.bindString(6, title);
			return this.insertStmt.executeInsert();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @param statusId
	 *            : status id should be 0 or 1
	 **/
	public void updateFavoriteStatusExhibitor(int exhibitor_id, int statusId) {
		String sql = "update " + EXHIBITOR_TABLE_NAME + " set favourites = '" + statusId + "' where exhibitor_id like '" + exhibitor_id + "'";
		db.execSQL(sql);
	}

	public void updateNotesOfExhibitor(int exhibitor_id, String notes) {
		String sql = "update " + EXHIBITOR_TABLE_NAME + " set notes = '" + notes + "' where exhibitor_id like '" + exhibitor_id + "'";
		db.execSQL(sql);
	}

	/**
	 * @param statusId
	 *            : status id should be 0 or 1
	 **/
	public void updateFavoriteStatusSpeaker(int speaker_id, int statusId) {
		String sql = "update " + SPEAKER_TABLE_NAME + " set favourites = '" + statusId + "' where speaker_id like '" + speaker_id + "'";
		db.execSQL(sql);
	}

	public void updateNotesOfSpeaker(int speaker_id, String notes) {
		String sql = "update " + SPEAKER_TABLE_NAME + " set notes = '" + notes + "' where speaker_id like '" + speaker_id + "'";
		db.execSQL(sql);
	}

	public ArrayList<NotificationBean> getNotificationList() {
		ArrayList<NotificationBean> notificationBeanList = new ArrayList<NotificationBean>();
		String query = "SELECT * FROM " + NOTIFICATION_TABLE_NAME + " where status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				NotificationBean notificationBean = new NotificationBean();
				notificationBean.setEvent_id(cursor.getInt(cursor.getColumnIndex("event_id")));
				notificationBean.setMessage(cursor.getString(cursor.getColumnIndex("message")));
				notificationBean.setNotification_id(cursor.getInt(cursor.getColumnIndex("notification_id")));
				notificationBean.setTime(cursor.getString(cursor.getColumnIndex("time")));
				notificationBean.setTitle(cursor.getString(cursor.getColumnIndex("title")));

				notificationBeanList.add(notificationBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return notificationBeanList;
	}

	public ArrayList<ExhibitorBean> getExhibitorsList() {
		ArrayList<ExhibitorBean> exhibitorBeanList = new ArrayList<ExhibitorBean>();
		String query = "SELECT * FROM " + EXHIBITOR_TABLE_NAME + " where status='1' order by company_name asc";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				ExhibitorBean exhibitorBean = new ExhibitorBean();
				exhibitorBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				exhibitorBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				exhibitorBean.setContactPerson(cursor.getString(cursor.getColumnIndex("contact_person")));
				exhibitorBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				exhibitorBean.setHallNo(cursor.getString(cursor.getColumnIndex("hall_number")));
				exhibitorBean.setBoothNum(cursor.getString(cursor.getColumnIndex("booth_number")));
				exhibitorBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				exhibitorBean.setDescription(cursor.getString(cursor.getColumnIndex("desc")));
				exhibitorBean.setProductInfo(cursor.getString(cursor.getColumnIndex("product_info")));
				exhibitorBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				exhibitorBean.setFacebook_id(cursor.getString(cursor.getColumnIndex("facebook_id")));
				exhibitorBean.setTwitter_id(cursor.getString(cursor.getColumnIndex("twitter_id")));
				exhibitorBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				exhibitorBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				exhibitorBean.setFloor_info(cursor.getString(cursor.getColumnIndex("floor_info")));
				exhibitorBean.setContact_number(cursor.getString(cursor.getColumnIndex("contact_number")));
				exhibitorBean.setExhibitor_id(cursor.getInt(cursor.getColumnIndex("exhibitor_id")));
				exhibitorBean.setEmail(cursor.getString(cursor.getColumnIndex("email_id")));

				exhibitorBeanList.add(exhibitorBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return exhibitorBeanList;
	}

	public ArrayList<ExhibitorBean> getExhibitorsListForNotes() {
		ArrayList<ExhibitorBean> exhibitorBeanList = new ArrayList<ExhibitorBean>();
		String query = "SELECT * FROM " + EXHIBITOR_TABLE_NAME + " WHERE  notes !='' and status='1' and notes IS not NULL order by company_name asc";// order by id desc

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				ExhibitorBean exhibitorBean = new ExhibitorBean();
				exhibitorBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				exhibitorBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				exhibitorBean.setContactPerson(cursor.getString(cursor.getColumnIndex("contact_person")));
				exhibitorBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				exhibitorBean.setHallNo(cursor.getString(cursor.getColumnIndex("hall_number")));
				exhibitorBean.setBoothNum(cursor.getString(cursor.getColumnIndex("booth_number")));
				exhibitorBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				exhibitorBean.setDescription(cursor.getString(cursor.getColumnIndex("desc")));
				exhibitorBean.setProductInfo(cursor.getString(cursor.getColumnIndex("product_info")));
				exhibitorBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				exhibitorBean.setFacebook_id(cursor.getString(cursor.getColumnIndex("facebook_id")));
				exhibitorBean.setTwitter_id(cursor.getString(cursor.getColumnIndex("twitter_id")));
				exhibitorBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				exhibitorBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				exhibitorBean.setFloor_info(cursor.getString(cursor.getColumnIndex("floor_info")));
				exhibitorBean.setContact_number(cursor.getString(cursor.getColumnIndex("contact_number")));
				exhibitorBean.setExhibitor_id(cursor.getInt(cursor.getColumnIndex("exhibitor_id")));
				exhibitorBean.setEmail(cursor.getString(cursor.getColumnIndex("email_id")));

				exhibitorBeanList.add(exhibitorBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return exhibitorBeanList;
	}

	public ArrayList<ExhibitorBean> getExhibitorsListForFavorites() {
		ArrayList<ExhibitorBean> exhibitorBeanList = new ArrayList<ExhibitorBean>();
		String query = "SELECT * FROM " + EXHIBITOR_TABLE_NAME + " WHERE  favourites ='1' and status='1' order by company_name asc";// order by id desc

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				ExhibitorBean exhibitorBean = new ExhibitorBean();
				exhibitorBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				exhibitorBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				exhibitorBean.setContactPerson(cursor.getString(cursor.getColumnIndex("contact_person")));
				exhibitorBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				exhibitorBean.setHallNo(cursor.getString(cursor.getColumnIndex("hall_number")));
				exhibitorBean.setBoothNum(cursor.getString(cursor.getColumnIndex("booth_number")));
				exhibitorBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				exhibitorBean.setDescription(cursor.getString(cursor.getColumnIndex("desc")));
				exhibitorBean.setProductInfo(cursor.getString(cursor.getColumnIndex("product_info")));
				exhibitorBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				exhibitorBean.setFacebook_id(cursor.getString(cursor.getColumnIndex("facebook_id")));
				exhibitorBean.setTwitter_id(cursor.getString(cursor.getColumnIndex("twitter_id")));
				exhibitorBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				exhibitorBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				exhibitorBean.setFloor_info(cursor.getString(cursor.getColumnIndex("floor_info")));
				exhibitorBean.setContact_number(cursor.getString(cursor.getColumnIndex("contact_number")));
				exhibitorBean.setExhibitor_id(cursor.getInt(cursor.getColumnIndex("exhibitor_id")));
				exhibitorBean.setEmail(cursor.getString(cursor.getColumnIndex("email_id")));

				exhibitorBeanList.add(exhibitorBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return exhibitorBeanList;
	}

	public ArrayList<SpeakerBean> getSpeakersList() {
		ArrayList<SpeakerBean> speakerBeanList = new ArrayList<SpeakerBean>();
		String query = "SELECT * FROM " + SPEAKER_TABLE_NAME + " where status='1' order by speaker_name asc";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				SpeakerBean speakerBean = new SpeakerBean();
				speakerBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				speakerBean.setContactNumber(cursor.getString(cursor.getColumnIndex("contact_number")));
				speakerBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				speakerBean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
				speakerBean.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
				speakerBean.setEmailId(cursor.getString(cursor.getColumnIndex("email_id")));
				speakerBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				speakerBean.setFacebookId(cursor.getString(cursor.getColumnIndex("facebook_id")));
				speakerBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				speakerBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				speakerBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				speakerBean.setSpeakerId(cursor.getInt(cursor.getColumnIndex("speaker_id")));
				speakerBean.setSpeakerName(cursor.getString(cursor.getColumnIndex("speaker_name")));
				speakerBean.setTwitterId(cursor.getString(cursor.getColumnIndex("twitter_id")));
				speakerBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				speakerBean.setSchedule(cursor.getString(cursor.getColumnIndex("schedule")));

				speakerBeanList.add(speakerBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return speakerBeanList;
	}

	public ArrayList<SpeakerBean> getSpeakersListForSchedule(String... speaker_id) {
		ArrayList<SpeakerBean> speakerBeanList = new ArrayList<SpeakerBean>();

		String speaker_query = "";
		for (int i = 0; i < speaker_id.length; i++) {
			speaker_query = speaker_query + " speaker_id ='" + speaker_id[i] + "' OR ";
		}
		speaker_query = speaker_query.substring(0, speaker_query.lastIndexOf("' OR "));

		// String query = "SELECT * FROM " + SPEAKER_TABLE_NAME + " WHERE  speaker_id ='" + speaker_id[0] + "' and status='1'";// order by id desc
		String query = "SELECT * FROM " + SPEAKER_TABLE_NAME + " WHERE " + speaker_query + "' and status='1'";// order by speaker_name asc

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				SpeakerBean speakerBean = new SpeakerBean();
				speakerBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				speakerBean.setContactNumber(cursor.getString(cursor.getColumnIndex("contact_number")));
				speakerBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				speakerBean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
				speakerBean.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
				speakerBean.setEmailId(cursor.getString(cursor.getColumnIndex("email_id")));
				speakerBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				speakerBean.setFacebookId(cursor.getString(cursor.getColumnIndex("facebook_id")));
				speakerBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				speakerBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				speakerBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				speakerBean.setSpeakerId(cursor.getInt(cursor.getColumnIndex("speaker_id")));
				speakerBean.setSpeakerName(cursor.getString(cursor.getColumnIndex("speaker_name")));
				speakerBean.setTwitterId(cursor.getString(cursor.getColumnIndex("twitter_id")));
				speakerBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				speakerBean.setSchedule(cursor.getString(cursor.getColumnIndex("schedule")));

				speakerBeanList.add(speakerBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return speakerBeanList;
	}

	public ArrayList<SpeakerBean> getSpeakersListForFavorites() {
		ArrayList<SpeakerBean> speakerBeanList = new ArrayList<SpeakerBean>();
		String query = "SELECT * FROM " + SPEAKER_TABLE_NAME + " WHERE  favourites ='1' and status='1' order by speaker_name asc";// order by id desc

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				SpeakerBean speakerBean = new SpeakerBean();
				speakerBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				speakerBean.setContactNumber(cursor.getString(cursor.getColumnIndex("contact_number")));
				speakerBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				speakerBean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
				speakerBean.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
				speakerBean.setEmailId(cursor.getString(cursor.getColumnIndex("email_id")));
				speakerBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				speakerBean.setFacebookId(cursor.getString(cursor.getColumnIndex("facebook_id")));
				speakerBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				speakerBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				speakerBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				speakerBean.setSpeakerId(cursor.getInt(cursor.getColumnIndex("speaker_id")));
				speakerBean.setSpeakerName(cursor.getString(cursor.getColumnIndex("speaker_name")));
				speakerBean.setTwitterId(cursor.getString(cursor.getColumnIndex("twitter_id")));
				speakerBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				speakerBean.setSchedule(cursor.getString(cursor.getColumnIndex("schedule")));

				speakerBeanList.add(speakerBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return speakerBeanList;
	}

	public ArrayList<SpeakerBean> getSpeakersListForNotes() {
		ArrayList<SpeakerBean> speakerBeanList = new ArrayList<SpeakerBean>();
		String query = "SELECT * FROM " + SPEAKER_TABLE_NAME + " WHERE  notes !='' and status='1' and notes IS not NULL";// order by id desc

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {

				SpeakerBean speakerBean = new SpeakerBean();
				speakerBean.setCompanyName(cursor.getString(cursor.getColumnIndex("company_name")));
				speakerBean.setContactNumber(cursor.getString(cursor.getColumnIndex("contact_number")));
				speakerBean.setCountry(cursor.getString(cursor.getColumnIndex("country")));
				speakerBean.setDesc(cursor.getString(cursor.getColumnIndex("desc")));
				speakerBean.setDesignation(cursor.getString(cursor.getColumnIndex("designation")));
				speakerBean.setEmailId(cursor.getString(cursor.getColumnIndex("email_id")));
				speakerBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				speakerBean.setFacebookId(cursor.getString(cursor.getColumnIndex("facebook_id")));
				speakerBean.setFavourites(cursor.getInt(cursor.getColumnIndex("favourites")));
				speakerBean.setImage(cursor.getString(cursor.getColumnIndex("image")));
				speakerBean.setNotes(cursor.getString(cursor.getColumnIndex("notes")));
				speakerBean.setSpeakerId(cursor.getInt(cursor.getColumnIndex("speaker_id")));
				speakerBean.setSpeakerName(cursor.getString(cursor.getColumnIndex("speaker_name")));
				speakerBean.setTwitterId(cursor.getString(cursor.getColumnIndex("twitter_id")));
				speakerBean.setUrl(cursor.getString(cursor.getColumnIndex("url")));
				speakerBean.setSchedule(cursor.getString(cursor.getColumnIndex("schedule")));

				speakerBeanList.add(speakerBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return speakerBeanList;
	}

	public ArrayList<BannerBean> getBanners() {
		ArrayList<BannerBean> bannerList = new ArrayList<BannerBean>();
		String query = "SELECT * FROM " + BANNER_TABLE_NAME + " where status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				BannerBean bannerBean = new BannerBean();
				bannerBean.setBanner_id(cursor.getInt(cursor.getColumnIndex("banner_id")));
				bannerBean.setBanner_url(cursor.getString(cursor.getColumnIndex("banner_url")));
				bannerBean.setTime(cursor.getString(cursor.getColumnIndex("time")));
				bannerBean.setEventId(cursor.getInt(cursor.getColumnIndex("eventId")));
				bannerBean.setLink_url(cursor.getString(cursor.getColumnIndex("link_url")));

				bannerList.add(bannerBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return bannerList;
	}

	public ArrayList<String> getGalleryFolders() {
		ArrayList<String> galleryBeanList = new ArrayList<String>();
		String query = "SELECT DISTINCT (category) FROM " + GALLERY_TABLE_NAME + " where status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				galleryBeanList.add(cursor.getString(cursor.getColumnIndex("category")));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return galleryBeanList;
	}

	public ArrayList<String> getPresentationFolders() {
		ArrayList<String> presentationList = new ArrayList<String>();
		String query = "SELECT DISTINCT (presentation_name) FROM " + PRESENTATION_TABLE_NAME + " where status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				presentationList.add(cursor.getString(cursor.getColumnIndex("presentation_name")));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return presentationList;
	}

	public ArrayList<String> getFloorFolders() {
		ArrayList<String> floorNameList = new ArrayList<String>();
		String query = "SELECT DISTINCT (floor_name) FROM " + FLOOR_PLAN_TABLE_NAME + " where status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				floorNameList.add(cursor.getString(cursor.getColumnIndex("floor_name")));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return floorNameList;
	}

	public ArrayList<String> getScheduleDays(String group_name) {
		ArrayList<String> scheduleDaysList = new ArrayList<String>();
		String query = "SELECT distinct (schedule_date) FROM schedule where status='1' and group_name='" + group_name + "' order by schedule_date";
		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				scheduleDaysList.add(cursor.getString(cursor.getColumnIndex("schedule_date")));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return scheduleDaysList;
	}

	public ArrayList<ScheduleBean> getScheduleList(String group_name, String scheduleDate) {
		ArrayList<ScheduleBean> scheduleBeanList = new ArrayList<ScheduleBean>();
		String query = "SELECT * FROM " + SCHEDULE_TABLE_NAME + " where status='1' and group_name='" + group_name + "' and schedule_date='" + scheduleDate + "' ORDER BY start_time";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setDescription(cursor.getString(cursor.getColumnIndex("desc")));
				scheduleBean.setEndTime(cursor.getString(cursor.getColumnIndex("end_time")));
				scheduleBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				scheduleBean.setScheduleId(cursor.getInt(cursor.getColumnIndex("schedule_id")));
				scheduleBean.setScheduleDate(cursor.getString(cursor.getColumnIndex("schedule_date")));
				scheduleBean.setScheduleName(cursor.getString(cursor.getColumnIndex("name")));
				scheduleBean.setStartTime(cursor.getString(cursor.getColumnIndex("start_time")));
				scheduleBean.setSpeakers(cursor.getString(cursor.getColumnIndex("speakers")));
				scheduleBean.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
				scheduleBean.setAlert_status(cursor.getInt(cursor.getColumnIndex("alert_status")));
				scheduleBean.setAlert_id(cursor.getString(cursor.getColumnIndex("alert_id")));

				scheduleBeanList.add(scheduleBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return scheduleBeanList;
	}

	public ArrayList<ScheduleBean> getScheduleListForSpeaker(String... schedule_id) {
		ArrayList<ScheduleBean> scheduleBeanList = new ArrayList<ScheduleBean>();
		String schedule_query = "";
		for (int i = 0; i < schedule_id.length; i++) {
			schedule_query = schedule_query + " schedule_id ='" + schedule_id[i] + "' OR ";
		}

		schedule_query = schedule_query.substring(0, schedule_query.lastIndexOf("' OR "));
		// String query = "SELECT * FROM " + SCHEDULE_TABLE_NAME + " WHERE  schedule_id ='" + schedule_id[0] + "' and status='1'";
		String query = "SELECT * FROM " + SCHEDULE_TABLE_NAME + " WHERE  " + schedule_query + "' and status='1' ORDER BY start_time";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				ScheduleBean scheduleBean = new ScheduleBean();
				scheduleBean.setDescription(cursor.getString(cursor.getColumnIndex("desc")));
				scheduleBean.setEndTime(cursor.getString(cursor.getColumnIndex("end_time")));
				scheduleBean.setEventId(cursor.getInt(cursor.getColumnIndex("event_id")));
				scheduleBean.setScheduleId(cursor.getInt(cursor.getColumnIndex("schedule_id")));
				scheduleBean.setScheduleDate(cursor.getString(cursor.getColumnIndex("schedule_date")));
				scheduleBean.setScheduleName(cursor.getString(cursor.getColumnIndex("name")));
				scheduleBean.setStartTime(cursor.getString(cursor.getColumnIndex("start_time")));
				scheduleBean.setSpeakers(cursor.getString(cursor.getColumnIndex("speakers")));
				scheduleBean.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
				scheduleBean.setAlert_status(cursor.getInt(cursor.getColumnIndex("alert_status")));
				scheduleBean.setAlert_id(cursor.getString(cursor.getColumnIndex("alert_id")));

				scheduleBeanList.add(scheduleBean);
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return scheduleBeanList;
	}

	public ArrayList<String> getImageGalleryList(String fldr_name) {
		ArrayList<String> galleryBeanList = new ArrayList<String>();
		String query = "SELECT (image_url) FROM " + GALLERY_TABLE_NAME + " where category like '" + fldr_name + "' and status='1'";// + "order by id desc";
		// String query = "SELECT (thumb_url) FROM " + GALLERY_TABLE_NAME + " where category like '" + fldr_name + "'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				galleryBeanList.add(cursor.getString(cursor.getColumnIndex("image_url")));
				// galleryBeanList.add(cursor.getString(cursor.getColumnIndex("thumb_url")));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return galleryBeanList;
	}

	public String getFloorPlanImageUrl(String floor_name) {
		String image_url = "";
		String query = "SELECT (image_url) FROM " + FLOOR_PLAN_TABLE_NAME + " where floor_name like '" + floor_name + "' and status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				image_url = cursor.getString(cursor.getColumnIndex("image_url"));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return image_url;
	}

	public String getPresentationPdfUrl(String presentation_name) {
		String presentation_data = "";
		String query = "SELECT (presentation_data) FROM " + PRESENTATION_TABLE_NAME + " where presentation_name like '" + presentation_name + "' and status='1'";// + "order by id desc";

		Cursor cursor = db.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			do {
				presentation_data = cursor.getString(cursor.getColumnIndex("presentation_data"));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return presentation_data;
	}
}
