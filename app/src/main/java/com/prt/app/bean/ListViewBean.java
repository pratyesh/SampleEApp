package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ListViewBean implements Parcelable {

	private int noOfListRow = 0;
	private int verticalSpacing = 0;
	private int horizontalSpacing = 0;
	private String listBackgroundColor = "";
	private String listBackgroundImg = "";
	private ArrayList<ListRowBean> listRowBeansList = new ArrayList<ListRowBean>();

	public int getNoOfListRow() {
		return noOfListRow;
	}

	public void setNoOfListRow(int noOfListRow) {
		this.noOfListRow = noOfListRow;
	}

	public int getVerticalSpacing() {
		return verticalSpacing;
	}

	public void setVerticalSpacing(int verticalSpacing) {
		this.verticalSpacing = verticalSpacing;
	}

	public int getHorizontalSpacing() {
		return horizontalSpacing;
	}

	public String getListBackgroundColor() {
		return listBackgroundColor;
	}

	public void setListBackgroundColor(String listBackgroundColor) {
		this.listBackgroundColor = listBackgroundColor;
	}

	public String getListBackgroundImg() {
		return listBackgroundImg;
	}

	public void setListBackgroundImg(String listBackgroundImg) {
		this.listBackgroundImg = listBackgroundImg;
	}

	public void setHorizontalSpacing(int horizontalSpacing) {
		this.horizontalSpacing = horizontalSpacing;
	}

	public ArrayList<ListRowBean> getListRowBeansList() {
		return listRowBeansList;
	}

	public void setListRowBeansList(ListRowBean listRowBeansList) {
		this.listRowBeansList.add(listRowBeansList);
	}

	public ListViewBean() {

	}

	public ListViewBean(Parcel source) {
		noOfListRow = source.readInt();
		verticalSpacing = source.readInt();
		horizontalSpacing = source.readInt();
		listBackgroundColor = source.readString();
		listBackgroundImg = source.readString();
		listRowBeansList = source.readArrayList(ListRowBean.class.getClassLoader());

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(noOfListRow);
		dest.writeInt(verticalSpacing);
		dest.writeInt(horizontalSpacing);
		dest.writeString(listBackgroundColor);
		dest.writeString(listBackgroundImg);
		dest.writeList(listRowBeansList);

	}

	public static final Creator<ListViewBean> CREATOR = new Creator<ListViewBean>() {

		@Override
		public ListViewBean[] newArray(int size) {

			return new ListViewBean[size];
		}

		@Override
		public ListViewBean createFromParcel(Parcel source) {

			return new ListViewBean(source);
		}
	};

}
