package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class GridRowBean implements Parcelable {

	ArrayList<GridColBean> gridColBeansList = new ArrayList<GridColBean>();

	public ArrayList<GridColBean> getGridColBeans() {
		return gridColBeansList;
	}

	public void setGridColBeans(GridColBean gridColBeans) {
		this.gridColBeansList.add(gridColBeans);
	}

	public GridRowBean() {

	}

	public GridRowBean(Parcel source) {
		gridColBeansList = source.readArrayList(GridRowBean.class.getClassLoader());
	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeList(gridColBeansList);
	}

	public static final Creator<GridRowBean> CREATOR = new Creator<GridRowBean>() {

		@Override
		public GridRowBean[] newArray(int size) {

			return new GridRowBean[size];
		}

		@Override
		public GridRowBean createFromParcel(Parcel source) {

			return new GridRowBean(source);
		}
	};

}
