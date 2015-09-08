package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ButtonBean implements Parcelable {

	private int width;
	private int height;
	private int action;
	private String textColor;
	private String image = "";
	private String text = "";
	private String bgcolor;
	private String paddingTop;
	private String gravity;

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getBackgroundColor() {
		return bgcolor;
	}

	public void setBackgroundColor(String bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(String paddingTop) {
		this.paddingTop = paddingTop;
	}

	public ButtonBean() {

	}

	public ButtonBean(Parcel source) {

		width = source.readInt();
		height = source.readInt();
		action = source.readInt();
		textColor = source.readString();
		image = source.readString();
		text = source.readString();
		bgcolor = source.readString();
		paddingTop = source.readString();
		gravity = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(width);
		dest.writeInt(height);
		dest.writeInt(action);
		dest.writeString(textColor);
		dest.writeString(image);
		dest.writeString(text);
		dest.writeString(bgcolor);
		dest.writeString(paddingTop);
		dest.writeString(gravity);

	}

	public static final Creator<ButtonBean> CREATOR = new Creator<ButtonBean>() {

		@Override
		public ButtonBean[] newArray(int size) {

			return new ButtonBean[size];
		}

		@Override
		public ButtonBean createFromParcel(Parcel source) {

			return new ButtonBean(source);
		}
	};
}
