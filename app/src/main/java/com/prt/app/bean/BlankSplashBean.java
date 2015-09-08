package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class BlankSplashBean implements Parcelable {

	private int action = 0;
	private int time = 0;
	private boolean hasUrl = false;
	private int noOfUrls = 0;
	private ArrayList<UrlBean> urlBeansList = new ArrayList<UrlBean>();

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean isHasUrl() {
		return hasUrl;
	}

	public void setHasUrl(boolean hasUrl) {
		this.hasUrl = hasUrl;
	}

	public int getNoOfUrls() {
		return noOfUrls;
	}

	public void setNoOfUrls(int noOfUrls) {
		this.noOfUrls = noOfUrls;
	}

	public ArrayList<UrlBean> getUrlBeansList() {
		return urlBeansList;
	}

	public void setUrlBeansList(UrlBean urlBeansList) {
		this.urlBeansList.add(urlBeansList);
	}

	public BlankSplashBean() {

	}

	public BlankSplashBean(Parcel source) {
		action = source.readInt();
		time = source.readInt();
		hasUrl = source.readInt() == 1;
		noOfUrls = source.readInt();
		urlBeansList = source.readArrayList(UrlBean.class.getClassLoader());

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(action);
		dest.writeInt(time);
		dest.writeInt(hasUrl ? 1 : 0);
		dest.writeInt(noOfUrls);
		dest.writeList(urlBeansList);
	}

	public static final Creator<BlankSplashBean> CREATOR = new Creator<BlankSplashBean>() {

		@Override
		public BlankSplashBean[] newArray(int size) {

			return new BlankSplashBean[size];
		}

		@Override
		public BlankSplashBean createFromParcel(Parcel source) {

			return new BlankSplashBean(source);
		}
	};

}
