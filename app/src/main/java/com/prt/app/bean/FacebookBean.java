package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class FacebookBean implements Parcelable {

	private String appId = "";
	private String appKey = "";
	private String appSecret = "";

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public FacebookBean() {

	}

	public FacebookBean(Parcel source) {
		appId = source.readString();
		appKey = source.readString();
		appSecret = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(appId);
		dest.writeString(appKey);
		dest.writeString(appSecret);
	}

	public static final Creator<FacebookBean> CREATOR = new Creator<FacebookBean>() {

		@Override
		public FacebookBean[] newArray(int size) {

			return new FacebookBean[size];
		}

		@Override
		public FacebookBean createFromParcel(Parcel source) {

			return new FacebookBean(source);
		}
	};

}
