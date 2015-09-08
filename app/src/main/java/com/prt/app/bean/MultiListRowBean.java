package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MultiListRowBean implements Parcelable {

	private String backgroundColor = "";
	private int action = 0;
	private String imageLeft = "";
	private String headerText = "";
	private String textColor = "";
	private String subTitle = "";
	private String smallText = "";

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

	public String getHeaderText() {
		return headerText;
	}

	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getSmallText() {
		return smallText;
	}

	public void setSmallText(String smallText) {
		this.smallText = smallText;
	}

	public MultiListRowBean() {

	}

	public MultiListRowBean(Parcel source) {

		backgroundColor = source.readString();
		action = source.readInt();
		imageLeft = source.readString();
		headerText = source.readString();
		textColor = source.readString();
		subTitle = source.readString();
		smallText = source.readString();
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
		dest.writeString(headerText);
		dest.writeString(textColor);
		dest.writeString(subTitle);
		dest.writeString(smallText);
	}

	public static final Creator<MultiListRowBean> CREATOR = new Creator<MultiListRowBean>() {

		@Override
		public MultiListRowBean[] newArray(int size) {

			return new MultiListRowBean[size];
		}

		@Override
		public MultiListRowBean createFromParcel(Parcel source) {

			return new MultiListRowBean(source);
		}
	};

}
