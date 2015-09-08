package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class EventHeaderBean implements Parcelable {

	private String backgroundColor = "";
	private String backgroundImg = "";
	private String textColor = "";
	private String startDate = "";
	private String endDate = "";
	private int textSize;
	private String eventHeader = "";
	private String imageRight = "";
	private int imageRightHeight;
	private int imageRightWidth;

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String date) {
		this.startDate = date;
	}

	public int getTextSize() {
		return textSize;
	}

	public void setTextSize(int textSize) {
		this.textSize = textSize;
	}

	public String getEventHeader() {
		return eventHeader;
	}

	public void setEventHeader(String eventHeader) {
		this.eventHeader = eventHeader;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public EventHeaderBean() {

	}

	public EventHeaderBean(Parcel source) {

		backgroundColor = source.readString();
		backgroundImg = source.readString();
		textColor = source.readString();
		startDate = source.readString();
		endDate = source.readString();
		textSize = source.readInt();
		eventHeader = source.readString();
		imageRight = source.readString();
		imageRightHeight = source.readInt();
		imageRightWidth = source.readInt();

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(backgroundColor);
		dest.writeString(backgroundImg);
		dest.writeString(textColor);
		dest.writeString(startDate);
		dest.writeString(endDate);
		dest.writeInt(textSize);
		dest.writeString(eventHeader);
		dest.writeString(imageRight);
		dest.writeInt(imageRightHeight);
		dest.writeInt(imageRightWidth);

	}

	public static final Creator<EventHeaderBean> CREATOR = new Creator<EventHeaderBean>() {

		@Override
		public EventHeaderBean[] newArray(int size) {

			return new EventHeaderBean[size];
		}

		@Override
		public EventHeaderBean createFromParcel(Parcel source) {

			return new EventHeaderBean(source);
		}
	};
}
