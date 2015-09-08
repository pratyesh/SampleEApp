package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SpeakerBean implements Parcelable {

	private int eventId;
	private String companyName;
	private String speakerName;
	private String country;
	private String image;
	private String desc;
	private String emailId;
	private String url;
	private String facebookId;
	private String twitterId;
	private int favourites;
	private String notes;
	private String contactNumber;
	private int speakerId;
	private String designation;
	private String schedule;

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getTwitterId() {
		return twitterId;
	}

	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}

	public int getFavourites() {
		return favourites;
	}

	public void setFavourites(int favourites) {
		this.favourites = favourites;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(int speakerId) {
		this.speakerId = speakerId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public SpeakerBean() {

	}

	public SpeakerBean(Parcel source) {

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

	public static final Creator<SpeakerBean> CREATOR = new Creator<SpeakerBean>() {

		@Override
		public SpeakerBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new SpeakerBean[size];
		}

		@Override
		public SpeakerBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new SpeakerBean(source);
		}
	};

}
