package com.prt.app.bean;

import java.util.HashMap;

import android.os.Parcel;
import android.os.Parcelable;

public class EventBean implements Parcelable {

	private HashMap<Integer, ScreenBean> eventMap = new HashMap<Integer, ScreenBean>();

	public HashMap<Integer, ScreenBean> getEventMap() {
		return eventMap;
	}

	public void setEventMap(int key, ScreenBean screenBean) {
		this.eventMap.put(key, screenBean);
	}

	public EventBean() {

	}

	public EventBean(Parcel source) {
		int size = source.readInt();
		for (int i = 0; i < size; i++) {
			Integer key = source.readInt();
			ScreenBean value = source.readParcelable(ScreenBean.class.getClassLoader());
			eventMap.put(key, value);
		}
	}

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(eventMap.size());
		for (Integer key : eventMap.keySet()) {
			dest.writeInt(key);
			dest.writeParcelable(eventMap.get(key), flags);
		}
	}

	public static final Creator<EventBean> CREATOR = new Creator<EventBean>() {

		@Override
		public EventBean[] newArray(int size) {
			return new EventBean[size];
		}

		@Override
		public EventBean createFromParcel(Parcel source) {
			return new EventBean(source);
		}
	};
}
