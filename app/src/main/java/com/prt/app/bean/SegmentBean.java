package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SegmentBean implements Parcelable {
	private String text = "";
	private String textColor = "";

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

	public SegmentBean() {
	}

	public SegmentBean(Parcel source) {

		text = source.readString();
		textColor = source.readString();
	}

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(text);
		dest.writeString(textColor);
	}

	public static final Creator<SegmentBean> CREATOR = new Creator<SegmentBean>() {

		@Override
		public SegmentBean[] newArray(int size) {

			return new SegmentBean[size];
		}

		@Override
		public SegmentBean createFromParcel(Parcel source) {

			return new SegmentBean(source);
		}
	};

}
