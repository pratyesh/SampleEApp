package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class FormBean implements Parcelable {

	private int noOfRows;
	private ArrayList<TextBoxBean> textBoxBeansList = new ArrayList<TextBoxBean>();
	private ArrayList<ButtonBean> buttonBeansList = new ArrayList<ButtonBean>();

	public int getNoOfRows() {
		return noOfRows;
	}

	public void setNoOfRows(int noOfRows) {
		this.noOfRows = noOfRows;
	}

	public ArrayList<TextBoxBean> getTextBoxBeansList() {
		return textBoxBeansList;
	}

	public void setTextBoxBeansList(TextBoxBean textBoxBeansList) {
		this.textBoxBeansList.add(textBoxBeansList);
	}

	public ArrayList<ButtonBean> getButtonBeansList() {
		return buttonBeansList;
	}

	public void setButtonBeansList(ButtonBean buttonBeansList) {
		this.buttonBeansList.add(buttonBeansList);
	}

	public FormBean() {

	}

	public FormBean(Parcel source) {
		noOfRows = source.readInt();
		textBoxBeansList = source.readArrayList(TextBoxBean.class.getClassLoader());
		buttonBeansList = source.readArrayList(ButtonBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(noOfRows);
		dest.writeList(textBoxBeansList);
		dest.writeList(buttonBeansList);
	}

	public static final Creator<FormBean> CREATOR = new Creator<FormBean>() {

		@Override
		public FormBean[] newArray(int size) {

			return new FormBean[size];
		}

		@Override
		public FormBean createFromParcel(Parcel source) {

			return new FormBean(source);
		}
	};

}
