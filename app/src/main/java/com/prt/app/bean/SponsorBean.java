package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SponsorBean implements Parcelable {

	private String stallNo;
	private String hallNo;
	private String chaletNo;
	private String organisation;

	public String getstallNo() {
		return stallNo;
	}

	public void setstallNo(String stallNo) {
		this.stallNo = stallNo;
	}

	public String gethallNo() {
		return hallNo;
	}

	public void sethallNo(String hallNo) {
		this.hallNo = hallNo;
	}

	public String getchaletNo() {
		return chaletNo;
	}

	public void setchaletNo(String chaletNo) {
		this.chaletNo = chaletNo;
	}

	public String getorganisation() {
		return organisation;
	}

	public void setorganisation(String organisation) {
		this.organisation = organisation;
	}

	public SponsorBean() {

	}

	public SponsorBean(Parcel source) {

	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub

	}

	public static final Creator<SponsorBean> CREATOR = new Creator<SponsorBean>() {

		@Override
		public SponsorBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new SponsorBean[size];
		}

		@Override
		public SponsorBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new SponsorBean(source);
		}
	};

}
