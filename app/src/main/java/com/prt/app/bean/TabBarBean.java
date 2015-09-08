package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class TabBarBean implements Parcelable {

	private int noOfTabs = 0;
	private ArrayList<TabBarItemBean> tabBarItemBeansList = new ArrayList<TabBarItemBean>();

	public int getNoOfTabs() {
		return noOfTabs;
	}

	public void setNoOfTabs(int noOfTabs) {
		this.noOfTabs = noOfTabs;
	}

	public ArrayList<TabBarItemBean> getTabBarItemBeansList() {
		return tabBarItemBeansList;
	}

	public void setTabBarItemBeansList(TabBarItemBean tabBarItemBeansList) {
		this.tabBarItemBeansList.add(tabBarItemBeansList);
	}

	public TabBarBean() {

	}

	public TabBarBean(Parcel source) {
		noOfTabs = source.readInt();
		tabBarItemBeansList = source.readArrayList(TabBarItemBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(noOfTabs);
		dest.writeList(tabBarItemBeansList);
	}

	public static final Creator<TabBarBean> CREATOR = new Creator<TabBarBean>() {

		@Override
		public TabBarBean[] newArray(int size) {

			return new TabBarBean[size];
		}

		@Override
		public TabBarBean createFromParcel(Parcel source) {

			return new TabBarBean(source);
		}
	};

}
