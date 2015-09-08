package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class WebBean implements Parcelable {

	private String url = "";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public WebBean() {

	}

	public WebBean(Parcel source) {
		url = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(url);
	}

	public static final Creator<WebBean> CREATOR = new Creator<WebBean>() {

		@Override
		public WebBean[] newArray(int size) {

			return new WebBean[size];
		}

		@Override
		public WebBean createFromParcel(Parcel source) {

			return new WebBean(source);
		}
	};

}
