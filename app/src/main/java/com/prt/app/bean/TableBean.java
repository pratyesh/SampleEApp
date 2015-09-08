package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class TableBean implements Parcelable {

	private ArrayList<FormBean> formBeansList = new ArrayList<FormBean>();

	public ArrayList<FormBean> getFormBeansList() {
		return formBeansList;
	}

	public void setFormBeansList(FormBean formBeansList) {
		this.formBeansList.add(formBeansList);
	}

	public TableBean() {

	}

	public TableBean(Parcel source) {
		formBeansList = source.readArrayList(FormBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(formBeansList);
	}

	public static final Creator<TableBean> CREATOR = new Creator<TableBean>() {

		@Override
		public TableBean[] newArray(int size) {

			return new TableBean[size];
		}

		@Override
		public TableBean createFromParcel(Parcel source) {

			return new TableBean(source);
		}
	};

}
