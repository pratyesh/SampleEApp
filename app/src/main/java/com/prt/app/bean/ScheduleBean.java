package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ScheduleBean implements Parcelable {

	private int eventId;
	private String scheduleName;
	private String scheduleDate;
	private String endTime;
	private int scheduleId;
	private String startTime;
	private String description;

	private String speakers = "";
	private int status = 0;
	private int alert_status = 0;
	private String alert_id = "";

	public String getSpeakers() {
		return speakers;
	}

	public void setSpeakers(String speakers) {
		this.speakers = speakers;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAlert_status() {
		return alert_status;
	}

	public void setAlert_status(int alert_status) {
		this.alert_status = alert_status;
	}

	public String getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(String alert_id) {
		this.alert_id = alert_id;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int schedule_id) {
		this.scheduleId = schedule_id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getScheduleDate() {
		return scheduleDate;
	}

	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ScheduleBean() {

	}

	public ScheduleBean(Parcel source) {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	public static final Creator<ScheduleBean> CREATOR = new Creator<ScheduleBean>() {

		@Override
		public ScheduleBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ScheduleBean[size];
		}

		@Override
		public ScheduleBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ScheduleBean(source);
		}
	};
}
