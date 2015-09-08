package com.prt.app.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageGalleryBean implements Parcelable {

	private String thumbNailURL = null;
	private String imageURL = null;

	public String getThumbNailURL() {
		return thumbNailURL;
	}

	public void setThumbNailURL(String thumbNailURL) {
		this.thumbNailURL = thumbNailURL;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public ImageGalleryBean() {

	}

	public ImageGalleryBean(Parcel source) {
		thumbNailURL = source.readString();
		imageURL = source.readString();
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(thumbNailURL);
		dest.writeString(imageURL);

	}

	public static final Creator<ImageGalleryBean> CREATOR = new Creator<ImageGalleryBean>() {

		@Override
		public ImageGalleryBean[] newArray(int size) {

			return new ImageGalleryBean[size];
		}

		@Override
		public ImageGalleryBean createFromParcel(Parcel source) {

			return new ImageGalleryBean(source);
		}
	};

}
