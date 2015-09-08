package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TwitterBean implements Parcelable {

	private String consumerKey = "";
	private String consumerSecret = "";

	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	public TwitterBean() {

	}

	public TwitterBean(Parcel source) {
		consumerKey = source.readString();
		consumerSecret = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(consumerKey);
		dest.writeString(consumerSecret);
	}

	public static final Creator<TwitterBean> CREATOR = new Creator<TwitterBean>() {

		@Override
		public TwitterBean[] newArray(int size) {

			return new TwitterBean[size];
		}

		@Override
		public TwitterBean createFromParcel(Parcel source) {

			return new TwitterBean(source);
		}
	};

}
