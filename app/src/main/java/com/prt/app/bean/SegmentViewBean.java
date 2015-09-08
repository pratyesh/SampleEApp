package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class SegmentViewBean implements Parcelable {

	private String backgroundColor = "";
	private String backgroundImg = "";
	private ArrayList<SegmentBean> segmentBean = new ArrayList<SegmentBean>();

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}

	public ArrayList<SegmentBean> getSegmentBean() {
		return segmentBean;
	}

	public void setSegmentBean(SegmentBean segmentBean) {
		this.segmentBean.add(segmentBean);
	}

	public SegmentViewBean() {

	}

	public SegmentViewBean(Parcel source) {

		backgroundColor = source.readString();
		backgroundImg = source.readString();
		segmentBean = source.readArrayList(SegmentBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(backgroundColor);
		dest.writeString(backgroundImg);
		dest.writeList(segmentBean);
	}

	public static final Creator<SegmentViewBean> CREATOR = new Creator<SegmentViewBean>() {

		@Override
		public SegmentViewBean[] newArray(int size) {

			return new SegmentViewBean[size];
		}

		@Override
		public SegmentViewBean createFromParcel(Parcel source) {

			return new SegmentViewBean(source);
		}
	};

}
