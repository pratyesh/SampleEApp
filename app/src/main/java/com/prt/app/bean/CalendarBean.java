package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class CalendarBean implements Parcelable {

	private String eventHeaderBackgroundColor;
	private String eventHeaderBackgroundImg;
	private String eventHeaderTextColor;
	private int eventHeaderTextSize;
	private String eventHeaderStartDate;
	private String eventDescriptionBackgroundColor;
	private String eventDescriptionbackgroundImg;
	private String eventDescriptionTextColor;
	private int eventDescriptionTextSize;
	private String eventDescriptionImageRight;
	private int eventDescriptionImageRightHeight;
	private int eventDescriptionImageRightWidth;
	private ArrayList<EventHeaderBean> eventHeaderBeansList = new ArrayList<EventHeaderBean>();
	private ArrayList<EventDescriptionBean> eventDescriptionBeansList = new ArrayList<EventDescriptionBean>();
	private int noOfEvents;
	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getEventHeaderBackgroundColor() {
		return eventHeaderBackgroundColor;
	}

	public void setEventHeaderBackgroundColor(String eventHeaderBackgroundColor) {
		this.eventHeaderBackgroundColor = eventHeaderBackgroundColor;
	}

	public String getEventHeaderBackgroundImg() {
		return eventHeaderBackgroundImg;
	}

	public void setEventHeaderBackgroundImg(String eventHeaderBackgroundImg) {
		this.eventHeaderBackgroundImg = eventHeaderBackgroundImg;
	}

	public String getEventHeaderTextColor() {
		return eventHeaderTextColor;
	}

	public void setEventHeaderTextColor(String eventHeaderTextColor) {
		this.eventHeaderTextColor = eventHeaderTextColor;
	}

	public int getEventHeaderTextSize() {
		return eventHeaderTextSize;
	}

	public void setEventHeaderTextSize(int eventHeaderTextSize) {
		this.eventHeaderTextSize = eventHeaderTextSize;
	}

	public String getEventHeaderStartDate() {
		return eventHeaderStartDate;
	}

	public void setEventHeaderStartDate(String eventHeaderStartDate) {
		this.eventHeaderStartDate = eventHeaderStartDate;
	}

	public String getEventDescriptionBackgroundColor() {
		return eventDescriptionBackgroundColor;
	}

	public void setEventDescriptionBackgroundColor(String eventDescriptionBackgroundColor) {
		this.eventDescriptionBackgroundColor = eventDescriptionBackgroundColor;
	}

	public String getEventDescriptionbackgroundImg() {
		return eventDescriptionbackgroundImg;
	}

	public void setEventDescriptionbackgroundImg(String eventDescriptionbackgroundImg) {
		this.eventDescriptionbackgroundImg = eventDescriptionbackgroundImg;
	}

	public String getEventDescriptionTextColor() {
		return eventDescriptionTextColor;
	}

	public void setEventDescriptionTextColor(String eventDescriptionTextColor) {
		this.eventDescriptionTextColor = eventDescriptionTextColor;
	}

	public int getEventDescriptionTextSize() {
		return eventDescriptionTextSize;
	}

	public void setEventDescriptionTextSize(int eventDescriptionTextSize) {
		this.eventDescriptionTextSize = eventDescriptionTextSize;
	}

	public String getEventDescriptionImageRight() {
		return eventDescriptionImageRight;
	}

	public void setEventDescriptionImageRight(String eventDescriptionImageRight) {
		this.eventDescriptionImageRight = eventDescriptionImageRight;
	}

	public int getEventDescriptionImageRightHeight() {
		return eventDescriptionImageRightHeight;
	}

	public void setEventDescriptionImageRightHeight(int eventDescriptionImageRightHeight) {
		this.eventDescriptionImageRightHeight = eventDescriptionImageRightHeight;
	}

	public int getEventDescriptionImageRightWidth() {
		return eventDescriptionImageRightWidth;
	}

	public void setEventDescriptionImageRightWidth(int eventDescriptionImageRightWidth) {
		this.eventDescriptionImageRightWidth = eventDescriptionImageRightWidth;
	}

	public int getNoOfEvents() {
		return noOfEvents;
	}

	public void setNoOfEvents(int noOfEvents) {
		this.noOfEvents = noOfEvents;
	}

	public ArrayList<EventHeaderBean> getEventHeaderBeansList() {
		return eventHeaderBeansList;
	}

	public void setEventHeaderBeansList(EventHeaderBean eventHeaderBeansList) {
		this.eventHeaderBeansList.add(eventHeaderBeansList);
	}

	public ArrayList<EventDescriptionBean> getEventDescriptionBeansList() {
		return eventDescriptionBeansList;
	}

	public void setEventDescriptionBeansList(EventDescriptionBean eventDescriptionBeansList) {
		this.eventDescriptionBeansList.add(eventDescriptionBeansList);
	}

	public CalendarBean() {

	}

	public CalendarBean(Parcel source) {

		eventHeaderBackgroundColor = source.readString();
		eventHeaderBackgroundImg = source.readString();
		eventHeaderTextColor = source.readString();
		eventHeaderTextSize = source.readInt();
		eventHeaderStartDate = source.readString();
		eventDescriptionBackgroundColor = source.readString();
		eventDescriptionbackgroundImg = source.readString();
		eventDescriptionTextColor = source.readString();
		eventDescriptionTextSize = source.readInt();
		eventDescriptionImageRight = source.readString();
		eventDescriptionImageRightHeight = source.readInt();
		eventDescriptionImageRightWidth = source.readInt();
		noOfEvents = source.readInt();
		groupName = source.readString();
		eventHeaderBeansList = source.readArrayList(EventHeaderBean.class.getClassLoader());
		eventDescriptionBeansList = source.readArrayList(EventDescriptionBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(eventHeaderBackgroundColor);
		dest.writeString(eventHeaderBackgroundImg);
		dest.writeString(eventHeaderTextColor);
		dest.writeInt(eventHeaderTextSize);
		dest.writeString(eventHeaderStartDate);
		dest.writeString(eventDescriptionBackgroundColor);
		dest.writeString(eventDescriptionbackgroundImg);
		dest.writeString(eventDescriptionTextColor);
		dest.writeInt(eventDescriptionTextSize);
		dest.writeString(eventDescriptionImageRight);
		dest.writeInt(eventDescriptionImageRightHeight);
		dest.writeInt(eventDescriptionImageRightWidth);
		dest.writeInt(noOfEvents);
		dest.writeString(groupName);
		dest.writeList(eventHeaderBeansList);
		dest.writeList(eventDescriptionBeansList);

	}

	public static final Creator<CalendarBean> CREATOR = new Creator<CalendarBean>() {

		@Override
		public CalendarBean[] newArray(int size) {

			return new CalendarBean[size];
		}

		@Override
		public CalendarBean createFromParcel(Parcel source) {

			return new CalendarBean(source);
		}
	};
}
