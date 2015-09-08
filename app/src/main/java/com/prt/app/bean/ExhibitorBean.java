package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ExhibitorBean implements Parcelable {

	private String url;
	private String email;
	private int eventId;
	private String companyName;
	private String contactPerson;
	private String productInfo;
	private String hallNo;
	private String boothNum;
	private int exhibitor_id;
	private String country;
	private String image;
	private String description;
	private String facebook_id;
	private String twitter_id;
	private int favourites;
	private String notes;
	private String floor_info;
	private String contact_number;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getHallNo() {
		return hallNo;
	}

	public void setHallNo(String hallNo) {
		this.hallNo = hallNo;
	}

	public String getBoothNum() {
		return boothNum;
	}

	public void setBoothNum(String boothNum) {
		this.boothNum = boothNum;
	}

	public int getExhibitor_id() {
		return exhibitor_id;
	}

	public void setExhibitor_id(int exhibitor_id) {
		this.exhibitor_id = exhibitor_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFacebook_id() {
		return facebook_id;
	}

	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}

	public String getTwitter_id() {
		return twitter_id;
	}

	public void setTwitter_id(String twitter_id) {
		this.twitter_id = twitter_id;
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

	public String getFloor_info() {
		return floor_info;
	}

	public void setFloor_info(String floor_info) {
		this.floor_info = floor_info;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public ExhibitorBean() {

	}

	public ExhibitorBean(Parcel source) {

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

	public static final Creator<ExhibitorBean> CREATOR = new Creator<ExhibitorBean>() {

		@Override
		public ExhibitorBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ExhibitorBean[size];
		}

		@Override
		public ExhibitorBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new ExhibitorBean(source);
		}
	};

}
