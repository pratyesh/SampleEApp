package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UrlBean implements Parcelable {

	private String name = "";
	private String url = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UrlBean() {

	}

	public UrlBean(Parcel source) {
		name = source.readString();
		url = source.readString();
	}

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(url);
	}

	public static final Creator<UrlBean> CREATOR = new Creator<UrlBean>() {

		@Override
		public UrlBean[] newArray(int size) {

			return new UrlBean[size];
		}

		@Override
		public UrlBean createFromParcel(Parcel source) {

			return new UrlBean(source);
		}
	};

}
