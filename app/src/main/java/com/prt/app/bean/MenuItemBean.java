package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuItemBean implements Parcelable {

	private String name = "";
	private int action;
	private String icon = "";

	public String getItem() {
		return name;
	}

	public void setItem(String name) {
		this.name = name;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MenuItemBean() {

	}

	public MenuItemBean(Parcel source) {
		name = source.readString();
		action = source.readInt();
		icon = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeInt(action);
		dest.writeString(icon);
	}

	public static final Creator<MenuItemBean> CREATOR = new Creator<MenuItemBean>() {

		@Override
		public MenuItemBean[] newArray(int size) {

			return new MenuItemBean[size];
		}

		@Override
		public MenuItemBean createFromParcel(Parcel source) {

			return new MenuItemBean(source);
		}
	};

}
