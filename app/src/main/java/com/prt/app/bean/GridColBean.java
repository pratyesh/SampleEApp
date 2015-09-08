package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class GridColBean implements Parcelable {

	private String text = "";
	private int action = 0;
	private String textColor = "";
	private String image = "";

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	public GridColBean() {

	}

	public GridColBean(Parcel source) {
		text = source.readString();
		action = source.readInt();
		textColor = source.readString();
		image = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		dest.writeInt(action);
		dest.writeString(textColor);
		dest.writeString(image);
	}

	public static final Creator<GridColBean> CREATOR = new Creator<GridColBean>() {

		@Override
		public GridColBean[] newArray(int size) {

			return new GridColBean[size];
		}

		@Override
		public GridColBean createFromParcel(Parcel source) {

			return new GridColBean(source);
		}
	};
}
