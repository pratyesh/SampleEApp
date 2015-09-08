package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class MenuBean implements Parcelable {

	private ArrayList<MenuItemBean> menuItemBeansList = new ArrayList<MenuItemBean>();

	public ArrayList<MenuItemBean> getMenuItemBeansList() {
		return menuItemBeansList;
	}

	public void setMenuItemBeansList(MenuItemBean menuItemBeansList) {
		this.menuItemBeansList.add(menuItemBeansList);
	}

	public MenuBean() {

	}

	public MenuBean(Parcel source) {
		menuItemBeansList = source.readArrayList(MenuItemBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(menuItemBeansList);
	}

	public static final Creator<MenuBean> CREATOR = new Creator<MenuBean>() {

		@Override
		public MenuBean[] newArray(int size) {

			return new MenuBean[size];
		}

		@Override
		public MenuBean createFromParcel(Parcel source) {

			return new MenuBean(source);
		}
	};
}
