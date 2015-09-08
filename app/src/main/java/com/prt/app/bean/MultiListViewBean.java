package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class MultiListViewBean implements Parcelable {

	private int noOfListRow = 0;
	private int verticalSpacing = 0;
	private int horizontalSpacing = 0;
	private String listBackgroundColor = "";
	private String listBackgroundImg = "";
	private ArrayList<MultiListRowBean> multiListRowBeansList = new ArrayList<MultiListRowBean>();
	private String headerTextColor = "";
	private String headerTextSize = "";
	private String headerTextStyle = "";
	private String subTitleTextColor = "";
	private String subTitleTextSize = "";
	private String subTitleTextStyle = "";
	private String smallTextColor = "";
	private String smallTextSize = "";
	private String smallTextStyle = "";
	private int action = 0;
	private boolean hasSearch = false;

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

	public ArrayList<MultiListRowBean> getMultiListRowBeansList() {
		return multiListRowBeansList;
	}

	public void setMultiListRowBeansList(MultiListRowBean multiListRowBeansList) {
		this.multiListRowBeansList.add(multiListRowBeansList);
	}

	public String getheaderTextColor() {
		return headerTextColor;
	}

	public void setheaderTextColor(String headerTextColor) {
		this.headerTextColor = headerTextColor;
	}

	public String getheaderTextSize() {
		return headerTextSize;
	}

	public void setheaderTextSize(String headerTextSize) {
		this.headerTextSize = headerTextSize;
	}

	public String getheaderTextStyle() {
		return headerTextStyle;
	}

	public void setheaderTextStyle(String headerTextStyle) {
		this.headerTextStyle = headerTextStyle;
	}

	public String getsubTitleTextColor() {
		return subTitleTextColor;
	}

	public void setsubTitleTextColor(String subTitleTextColor) {
		this.subTitleTextColor = subTitleTextColor;
	}

	public String getsubTitleTextSize() {
		return subTitleTextSize;
	}

	public void setsubTitleTextSize(String subTitleTextSize) {
		this.subTitleTextSize = subTitleTextSize;
	}

	public String getsubTitleTextStyle() {
		return subTitleTextStyle;
	}

	public void setsubTitleTextStyle(String subTitleTextStyle) {
		this.subTitleTextStyle = subTitleTextStyle;
	}

	public String getsmallTextColor() {
		return smallTextColor;
	}

	public void setsmallTextColor(String smallTextColor) {
		this.smallTextColor = smallTextColor;
	}

	public String getsmallTextSize() {
		return smallTextSize;
	}

	public void setsmallTextSize(String smallTextSize) {
		this.smallTextSize = smallTextSize;
	}

	public String getsmallTextStyle() {
		return smallTextStyle;
	}

	public void setsmallTextStyle(String smallTextStyle) {
		this.smallTextStyle = smallTextStyle;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public boolean getSearch() {
		return hasSearch;
	}

	public void setSearch(boolean hasSearch) {
		this.hasSearch = hasSearch;
	}

	public MultiListViewBean() {

	}

	public MultiListViewBean(Parcel source) {

		noOfListRow = source.readInt();
		verticalSpacing = source.readInt();
		horizontalSpacing = source.readInt();
		listBackgroundColor = source.readString();
		listBackgroundImg = source.readString();
		headerTextColor = source.readString();
		headerTextSize = source.readString();
		headerTextStyle = source.readString();
		subTitleTextColor = source.readString();
		subTitleTextSize = source.readString();
		subTitleTextStyle = source.readString();
		smallTextColor = source.readString();
		smallTextSize = source.readString();
		smallTextStyle = source.readString();
		action = source.readInt();
		hasSearch = source.readInt() == 1;
		multiListRowBeansList = source.readArrayList(MultiListRowBean.class.getClassLoader());

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
		dest.writeString(headerTextColor);
		dest.writeString(headerTextSize);
		dest.writeString(headerTextStyle);
		dest.writeString(subTitleTextColor);
		dest.writeString(subTitleTextSize);
		dest.writeString(subTitleTextStyle);
		dest.writeString(smallTextColor);
		dest.writeString(smallTextSize);
		dest.writeString(smallTextStyle);
		dest.writeInt(action);
		dest.writeInt(hasSearch ? 1 : 0);
		dest.writeList(multiListRowBeansList);
	}

	public static final Creator<MultiListViewBean> CREATOR = new Creator<MultiListViewBean>() {

		@Override
		public MultiListViewBean[] newArray(int size) {

			return new MultiListViewBean[size];
		}

		@Override
		public MultiListViewBean createFromParcel(Parcel source) {

			return new MultiListViewBean(source);
		}
	};
}
