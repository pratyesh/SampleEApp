package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class EventDescriptionBean implements Parcelable {

	private String backgroundColor = "";
	private String backgroundImg = "";
	private String textColor = "";
	private int textSize;
	private String imageRight = "";
	private int imageRightHeight;
	private int imageRightWidth;
	private String desc = "";
	private String event = "";
	private String eventDescription = "";

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public String getImageRight() {
		return imageRight;
	}

	public void setImageRight(String imageRight) {
		this.imageRight = imageRight;
	}

	public int getImageRightHeight() {
		return imageRightHeight;
	}

	public void setImageRightHeight(int imageRightHeight) {
		this.imageRightHeight = imageRightHeight;
	}

	public int getImageRightWidth() {
		return imageRightWidth;
	}

	public void setImageRightWidth(int imageRightWidth) {
		this.imageRightWidth = imageRightWidth;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public EventDescriptionBean() {

	}

	public EventDescriptionBean(Parcel source) {

		backgroundColor = source.readString();
		backgroundImg = source.readString();
		textColor = source.readString();
		textSize = source.readInt();
		imageRight = source.readString();
		imageRightHeight = source.readInt();
		imageRightWidth = source.readInt();
		desc = source.readString();
		event = source.readString();
		eventDescription = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

	}

	public static final Creator<EventDescriptionBean> CREATOR = new Creator<EventDescriptionBean>() {

		@Override
		public EventDescriptionBean[] newArray(int size) {

			return new EventDescriptionBean[size];
		}

		@Override
		public EventDescriptionBean createFromParcel(Parcel source) {

			return new EventDescriptionBean(source);
		}
	};

}
