package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Map2DBean implements Parcelable {

	private String image = "";
	private int height;
	private int width;
	private String gravity = "";

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Map2DBean() {

	}

	public Map2DBean(Parcel source) {
		image = source.readString();
		height = source.readInt();
		width = source.readInt();
		gravity = source.readString();

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(image);
		dest.writeInt(height);
		dest.writeInt(width);
		dest.writeString(gravity);
	}

	public static final Creator<Map2DBean> CREATOR = new Creator<Map2DBean>() {

		@Override
		public Map2DBean[] newArray(int size) {

			return new Map2DBean[size];
		}

		@Override
		public Map2DBean createFromParcel(Parcel source) {

			return new Map2DBean(source);
		}
	};
}
