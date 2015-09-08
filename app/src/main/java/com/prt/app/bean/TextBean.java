package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TextBean implements Parcelable {

	private String textColor;
	private int textSize;
	private String backgroundColor = "";
	private String textStyle;
	private String text = "";
	private String tag = "";

	public String gettextColor() {
		return textColor;
	}

	public void settextColor(String textColor) {
		this.textColor = textColor;
	}

	public int gettextSize() {
		return textSize;
	}

	public void settextSize(int textSize) {
		this.textSize = textSize;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String gettextStyle() {
		return textStyle;
	}

	public void settextStyle(String textStyle) {
		this.textStyle = textStyle;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public TextBean() {

	}

	public TextBean(Parcel source) {
		textColor = source.readString();
		textSize = source.readInt();
		backgroundColor = source.readString();
		textStyle = source.readString();
		text = source.readString();
		tag = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(textColor);
		dest.writeInt(textSize);
		dest.writeString(backgroundColor);
		dest.writeString(textStyle);
		dest.writeString(text);
		dest.writeString(tag);

	}

	public static final Creator<TextBean> CREATOR = new Creator<TextBean>() {

		@Override
		public TextBean[] newArray(int size) {

			return new TextBean[size];
		}

		@Override
		public TextBean createFromParcel(Parcel source) {

			return new TextBean(source);
		}
	};

}
