package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class WebButtonBean implements Parcelable {

	private ArrayList<WebBean> webBeansList = new ArrayList<WebBean>();
	private ArrayList<ButtonBean> buttonBeansList = new ArrayList<ButtonBean>();

	public ArrayList<WebBean> getWebBeansList() {
		return webBeansList;
	}

	public void setWebBeansList(WebBean webBeans) {
		this.webBeansList.add(webBeans);
	}

	public ArrayList<ButtonBean> getButtonBeansList() {
		return buttonBeansList;
	}

	public void setButtonBeansList(ButtonBean buttonBeans) {
		this.buttonBeansList.add(buttonBeans);
	}

	public WebButtonBean() {

	}

	public WebButtonBean(Parcel source) {
		webBeansList = source.readArrayList(WebBean.class.getClassLoader());
		buttonBeansList = source.readArrayList(ButtonBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(webBeansList);
		dest.writeList(buttonBeansList);
	}

	public static final Creator<WebButtonBean> CREATOR = new Creator<WebButtonBean>() {

		@Override
		public WebButtonBean[] newArray(int size) {

			return new WebButtonBean[size];
		}

		@Override
		public WebButtonBean createFromParcel(Parcel source) {

			return new WebButtonBean(source);
		}
	};
}
