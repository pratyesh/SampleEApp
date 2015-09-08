package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MapViewBean implements Parcelable {

	private String address;
	private String mapKey;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMapKey() {
		return mapKey;
	}

	public void setMapKey(String mapKey) {
		this.mapKey = mapKey;
	}

	public MapViewBean() {

	}

	public MapViewBean(Parcel source) {
		address = source.readString();
		mapKey = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeString(address);
		dest.writeString(mapKey);
	}

	public static final Creator<MapViewBean> CREATOR = new Creator<MapViewBean>() {

		@Override
		public MapViewBean[] newArray(int size) {

			return new MapViewBean[size];
		}

		@Override
		public MapViewBean createFromParcel(Parcel source) {

			return new MapViewBean(source);
		}
	};

}
