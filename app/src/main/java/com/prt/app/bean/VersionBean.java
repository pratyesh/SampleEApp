package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class VersionBean implements Parcelable{

	private String eventID="";
	private String eventName="";
	private String packageName="";
	private String version="";
	private String id="";
	
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getEventID() {
		return eventID;
	}
	public void setEventID(String eventID) {
		this.eventID = eventID;
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public VersionBean() {

	}

	public VersionBean(Parcel source) {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	public static final Creator<VersionBean> CREATOR = new Creator<VersionBean>() {

		@Override
		public VersionBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new VersionBean[size];
		}

		@Override
		public VersionBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new VersionBean(source);
		}
	};

}
