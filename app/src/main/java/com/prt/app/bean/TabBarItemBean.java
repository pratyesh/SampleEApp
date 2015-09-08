package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class TabBarItemBean implements Parcelable {

	private String title = "";
	private String image = "";
	private int linkedscreen = 0;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLinkedscreen() {
		return linkedscreen;
	}

	public void setLinkedscreen(int linkedscreen) {
		this.linkedscreen = linkedscreen;
	}

	public TabBarItemBean() {

	}

	public TabBarItemBean(Parcel source) {
		title = source.readString();
		image = source.readString();
		linkedscreen = source.readInt();

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(title);
		dest.writeString(image);
		dest.writeInt(linkedscreen);

	}

	public static final Creator<TabBarItemBean> CREATOR = new Creator<TabBarItemBean>() {

		@Override
		public TabBarItemBean[] newArray(int size) {

			return new TabBarItemBean[size];
		}

		@Override
		public TabBarItemBean createFromParcel(Parcel source) {

			return new TabBarItemBean(source);
		}
	};

}
