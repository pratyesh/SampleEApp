package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class HeaderSocialNetworkingBean implements Parcelable {

	private boolean isAvailable = false;
	private String socialNetworkingUrl = "";
	private String image = "";
	private int action;

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean getSocialNetworkingIsAvailable() {
		return isAvailable;
	}

	public void setSocialNetworkingIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getSocialNetworkingUrl() {
		return socialNetworkingUrl;
	}

	public void setSocialNetworkingUrl(String socialNetworkingUrl) {
		this.socialNetworkingUrl = socialNetworkingUrl;
	}

	public HeaderSocialNetworkingBean() {

	}

	public HeaderSocialNetworkingBean(Parcel source) {

		isAvailable = source.readInt() == 1;
		socialNetworkingUrl = source.readString();
		image = source.readString();
		action = source.readInt();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(isAvailable ? 1 : 0);
		dest.writeString(socialNetworkingUrl);
		dest.writeString(image);
		dest.writeInt(action);

	}

	public static final Creator<HeaderSocialNetworkingBean> CREATOR = new Creator<HeaderSocialNetworkingBean>() {

		@Override
		public HeaderSocialNetworkingBean[] newArray(int size) {

			return new HeaderSocialNetworkingBean[size];
		}

		@Override
		public HeaderSocialNetworkingBean createFromParcel(Parcel source) {

			return new HeaderSocialNetworkingBean(source);
		}
	};

}
