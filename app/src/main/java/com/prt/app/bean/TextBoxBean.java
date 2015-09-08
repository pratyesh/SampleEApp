package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TextBoxBean implements Parcelable {

	private boolean isMultiline;
	private String isInputType = "";
	private String text = "";
	private int height;
	private int paddingTop;
	private String gravity;

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public boolean getIsMultiline() {
		return isMultiline;
	}

	public void setIsMultiline(boolean isMultiline) {
		this.isMultiline = isMultiline;
	}

	public String getIsInputType() {
		return isInputType;
	}

	public void setIsInputType(String isInputType) {
		this.isInputType = isInputType;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getPaddingTop() {
		return paddingTop;
	}

	public void setPaddingTop(int paddingTop) {
		this.paddingTop = paddingTop;
	}

	public TextBoxBean() {

	}

	public TextBoxBean(Parcel source) {

		isMultiline = (source.readInt() == 1);
		isInputType = source.readString();
		text = source.readString();
		height = source.readInt();
		paddingTop = source.readInt();
		gravity = source.readString();

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(isMultiline ? 1 : 0);
		dest.writeString(isInputType);
		dest.writeString(text);
		dest.writeInt(height);
		dest.writeInt(paddingTop);
		dest.writeString(gravity);

	}

	public static final Creator<TextBoxBean> CREATOR = new Creator<TextBoxBean>() {

		@Override
		public TextBoxBean[] newArray(int size) {

			return new TextBoxBean[size];
		}

		@Override
		public TextBoxBean createFromParcel(Parcel source) {

			return new TextBoxBean(source);
		}
	};
}
