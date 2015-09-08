package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class GridBean implements Parcelable {

	private int noOfGridRow = 0;
	private int noOfGridCol = 0;
	private int verticalSpacing = 0;
	private int horizontalSpacing = 0;
	private String gridBackground = "";
	private String gravity = "";
	private ArrayList<GridRowBean> rowBeansList = new ArrayList<GridRowBean>();

	public int getNoOfGridRow() {
		return noOfGridRow;
	}

	public void setNoOfGridRow(int noOfGridRow) {
		this.noOfGridRow = noOfGridRow;
	}

	public int getNoOfGridCol() {
		return noOfGridCol;
	}

	public void setNoOfGridCol(int noOfGridCol) {
		this.noOfGridCol = noOfGridCol;
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

	public void setHorizontalSpacing(int horizontalSpacing) {
		this.horizontalSpacing = horizontalSpacing;
	}

	public String getGridBackground() {
		return gridBackground;
	}

	public void setGridBackground(String gridBackground) {
		this.gridBackground = gridBackground;
	}

	public String getGravity() {
		return gravity;
	}

	public void setGravity(String gravity) {
		this.gravity = gravity;
	}

	public ArrayList<GridRowBean> getRowBeans() {
		return rowBeansList;
	}

	public void setRowBeans(GridRowBean rowBeans) {
		this.rowBeansList.add(rowBeans);
	}

	public GridBean() {

	}

	public GridBean(Parcel source) {
		noOfGridRow = source.readInt();
		noOfGridCol = source.readInt();
		verticalSpacing = source.readInt();
		horizontalSpacing = source.readInt();
		gridBackground = source.readString();
		gravity = source.readString();
		rowBeansList = source.readArrayList(GridRowBean.class.getClassLoader());

	}

	@Override
	public int describeContents() {

		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(noOfGridRow);
		dest.writeInt(noOfGridCol);
		dest.writeInt(verticalSpacing);
		dest.writeInt(horizontalSpacing);
		dest.writeString(gridBackground);
		dest.writeString(gravity);
		dest.writeList(rowBeansList);
	}

	public static final Creator<GridBean> CREATOR = new Creator<GridBean>() {

		@Override
		public GridBean[] newArray(int size) {

			return new GridBean[size];
		}

		@Override
		public GridBean createFromParcel(Parcel source) {

			return new GridBean(source);
		}
	};

}
