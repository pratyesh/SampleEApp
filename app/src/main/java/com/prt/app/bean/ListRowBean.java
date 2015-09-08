package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ListRowBean implements Parcelable {

	private String backgroundColor = "";
	private int action = 0;
	private String imageLeft = "";
	private int imageLeftHeight = 0;
	private int imageLeftWidth = 0;
	private String text = "";
	private String textColor = "";
	private int textSize = 0;
	private String imageRight = "";
	private int imageRightHeight = 0;
	private int imageRightWidth = 0;

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getImageLeft() {
		return imageLeft;
	}

	public void setImageLeft(String imageLeft) {
		this.imageLeft = imageLeft;
	}

	public int getImageLeftHeight() {
		return imageLeftHeight;
	}

	public void setImageLeftHeight(int imageLeftHeight) {
		this.imageLeftHeight = imageLeftHeight;
	}

	public int getImageLeftWidth() {
		return imageLeftWidth;
	}

	public void setImageLeftWidth(int imageLeftWidth) {
		this.imageLeftWidth = imageLeftWidth;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public ListRowBean() {

	}

	public ListRowBean(Parcel source) {
		backgroundColor = source.readString();
		action = source.readInt();
		imageLeft = source.readString();
		imageLeftHeight = source.readInt();
		imageLeftWidth = source.readInt();
		text = source.readString();
		textColor = source.readString();
		textSize = source.readInt();
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
		dest.writeInt(action);
		dest.writeString(imageLeft);
		dest.writeInt(imageLeftHeight);
		dest.writeInt(imageLeftWidth);
		dest.writeString(text);
		dest.writeString(textColor);
		dest.writeInt(textSize);
		dest.writeString(imageRight);
		dest.writeInt(imageRightHeight);
		dest.writeInt(imageRightWidth);

	}

	public static final Creator<ListRowBean> CREATOR = new Creator<ListRowBean>() {

		@Override
		public ListRowBean[] newArray(int size) {

			return new ListRowBean[size];
		}

		@Override
		public ListRowBean createFromParcel(Parcel source) {

			return new ListRowBean(source);
		}
	};

}
