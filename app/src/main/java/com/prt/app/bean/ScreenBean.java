package com.prt.app.bean;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

public class ScreenBean implements Parcelable {

	private int id = 0;
	private String backgroundColor = "";
	private String backgroundImg = "";
	private int templateId = 0;
	private String title = "";
	private String titlebackgroundImage = "";
	private String titleBackgroundColor = "";
	private String titleColor = "";
	private int titleTextSize = 0;
	private boolean hasTitle = false;
	private boolean hasMenu = false;
	private ArrayList<TabBarBean> tabbarBeansList = new ArrayList<TabBarBean>();
	private ArrayList<GridBean> gridBeansList = new ArrayList<GridBean>();
	private ArrayList<ButtonBean> buttonBeansList = new ArrayList<ButtonBean>();
	private ArrayList<TableBean> tableBeansList = new ArrayList<TableBean>();
	private ArrayList<WebBean> webBeansList = new ArrayList<WebBean>();
	private ArrayList<ImageGalleryBean> imageGalleryBeansList = new ArrayList<ImageGalleryBean>();
	private ArrayList<BlankSplashBean> blankSplashBeansList = new ArrayList<BlankSplashBean>();
	private ArrayList<ListViewBean> listViewBeansList = new ArrayList<ListViewBean>();
	private ArrayList<TextBean> textViewBeansList = new ArrayList<TextBean>();
	private ArrayList<MapViewBean> mapviewBean = new ArrayList<MapViewBean>();
	private ArrayList<FacebookBean> facebookBeanList = new ArrayList<FacebookBean>();
	private ArrayList<TwitterBean> twitterBeanList = new ArrayList<TwitterBean>();
	private ArrayList<MenuBean> menuBeanList = new ArrayList<MenuBean>();
	private ArrayList<Map2DBean> map2DBeanList = new ArrayList<Map2DBean>();
	private ArrayList<CalendarBean> calendarBeansList = new ArrayList<CalendarBean>();
	private ArrayList<WebButtonBean> webButtonBeanList = new ArrayList<WebButtonBean>();
	private ArrayList<MultiListViewBean> multiListViewBeansList = new ArrayList<MultiListViewBean>();
	private ArrayList<HeaderSocialNetworkingBean> headerSocialNetworkingList = new ArrayList<HeaderSocialNetworkingBean>();
	private ArrayList<ImageBean> imageBean = new ArrayList<ImageBean>();
	private ArrayList<SegmentViewBean> segmentViewBeanList = new ArrayList<SegmentViewBean>();

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

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitlebackgroundImage() {
		return titlebackgroundImage;
	}

	public void setTitlebackgroundImage(String titlebackgroundImage) {
		this.titlebackgroundImage = titlebackgroundImage;
	}

	public String getTitleBackgroundColor() {
		return titleBackgroundColor;
	}

	public void setTitleBackgroundColor(String titleBackgroundColor) {
		this.titleBackgroundColor = titleBackgroundColor;
	}

	public String getTitleColor() {
		return titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public int getTitleTextSize() {
		return titleTextSize;
	}

	public void setTitleTextSize(int titleTextSize) {
		this.titleTextSize = titleTextSize;
	}

	public boolean isHasTitle() {
		return hasTitle;
	}

	public void setHasTitle(boolean hasTitle) {
		this.hasTitle = hasTitle;
	}

	public boolean isHasMenu() {
		return hasMenu;
	}

	public void setHasMenu(boolean hasMenu) {
		this.hasMenu = hasMenu;
	}

	public ArrayList<TabBarBean> getTabbarBeansList() {
		return tabbarBeansList;
	}

	public void setTabbarBeansList(TabBarBean tabbarBeansList) {
		this.tabbarBeansList.add(tabbarBeansList);
	}

	public ArrayList<GridBean> getGridBeansList() {
		return gridBeansList;
	}

	public void setGridBeansList(GridBean gridBeansList) {
		this.gridBeansList.add(gridBeansList);
	}

	public ArrayList<ButtonBean> getButtonBeansList() {
		return buttonBeansList;
	}

	public void setButtonBeansList(ButtonBean buttonBeansList) {
		this.buttonBeansList.add(buttonBeansList);
	}

	public ArrayList<TableBean> getTableBeansList() {
		return tableBeansList;
	}

	public void setTableBeansList(TableBean textBoxBeansList) {
		this.tableBeansList.add(textBoxBeansList);
	}

	public ArrayList<WebBean> getWebBeansList() {
		return webBeansList;
	}

	public void setWebBeansList(WebBean webBeansList) {
		this.webBeansList.add(webBeansList);
	}

	public ArrayList<ImageGalleryBean> getImageGalleryBeansList() {
		return imageGalleryBeansList;
	}

	public void setImageGalleryBeansList(ImageGalleryBean imageGalleryBeansList) {
		this.imageGalleryBeansList.add(imageGalleryBeansList);
	}

	public ArrayList<BlankSplashBean> getBlankSplashBeansList() {
		return blankSplashBeansList;
	}

	public void setBlankSplashBeansList(BlankSplashBean blankSplashBeansList) {
		this.blankSplashBeansList.add(blankSplashBeansList);
	}

	public ArrayList<ListViewBean> getListViewBeansList() {
		return listViewBeansList;
	}

	public void setListViewBeansList(ListViewBean listViewBeansList) {
		this.listViewBeansList.add(listViewBeansList);
	}

	public ArrayList<TextBean> getTextViewBeansList() {
		return textViewBeansList;
	}

	public void setTextViewBeansList(TextBean textViewBeansList) {
		this.textViewBeansList.add(textViewBeansList);
	}

	public ArrayList<MapViewBean> getMapviewBean() {
		return mapviewBean;
	}

	public void setMapviewBean(MapViewBean mapviewBean) {
		this.mapviewBean.add(mapviewBean);
	}

	public ArrayList<FacebookBean> getFacebookBeanList() {
		return facebookBeanList;
	}

	public void setFacebookBeanList(FacebookBean facebookBeanList) {
		this.facebookBeanList.add(facebookBeanList);
	}

	public ArrayList<TwitterBean> getTwitterBeanList() {
		return twitterBeanList;
	}

	public void setTwitterBeanList(TwitterBean twitterBeanList) {
		this.twitterBeanList.add(twitterBeanList);
	}

	public ArrayList<MenuBean> getMenuBeanList() {
		return menuBeanList;
	}

	public void setMenuBeanList(MenuBean menuBeanList) {
		this.menuBeanList.add(menuBeanList);
	}

	public ArrayList<Map2DBean> getMap2DBeanList() {
		return map2DBeanList;
	}

	public void setMap2DBeanList(Map2DBean map2dBeanList) {
		map2DBeanList.add(map2dBeanList);
	}

	public ArrayList<CalendarBean> getCalendarBeansList() {
		return calendarBeansList;
	}

	public void setCalendarBeansList(CalendarBean calendarBeansList) {
		this.calendarBeansList.add(calendarBeansList);
	}

	public ArrayList<WebButtonBean> getWebButtonBeanList() {
		return webButtonBeanList;
	}

	public void setWebButtonBeanList(WebButtonBean webButtonBeanList) {
		this.webButtonBeanList.add(webButtonBeanList);
	}

	public ArrayList<MultiListViewBean> getMultiListViewBeansList() {
		return multiListViewBeansList;
	}

	public void setMultiListViewBeansList(MultiListViewBean multiListViewBeansList) {
		this.multiListViewBeansList.add(multiListViewBeansList);
	}

	public ArrayList<HeaderSocialNetworkingBean> getHeaderSocialNetworkingList() {
		return headerSocialNetworkingList;
	}

	public void setHeaderSocialNetworkingList(HeaderSocialNetworkingBean HeaderFacebookBean) {
		this.headerSocialNetworkingList.add(HeaderFacebookBean);
	}

	public ArrayList<ImageBean> getImageBean() {
		return imageBean;
	}

	public void setImageBean(ImageBean imageBean) {
		this.imageBean.add(imageBean);
	}

	public ArrayList<SegmentViewBean> getSegmentViewBeanList() {
		return segmentViewBeanList;
	}

	public void setSegmentViewBeanList(SegmentViewBean segmentViewBean) {
		this.segmentViewBeanList.add(segmentViewBean);
	}

	public ScreenBean() {

	}

	public ScreenBean(Parcel source) {

		id = source.readInt();
		backgroundColor = source.readString();
		backgroundImg = source.readString();
		templateId = source.readInt();
		title = source.readString();
		titlebackgroundImage = source.readString();
		titleBackgroundColor = source.readString();
		titleColor = source.readString();
		titleTextSize = source.readInt();
		hasTitle = source.readInt() == 1;
		hasMenu = source.readInt() == 1;

		tabbarBeansList = source.readArrayList(TabBarBean.class.getClassLoader());
		gridBeansList = source.readArrayList(GridBean.class.getClassLoader());
		buttonBeansList = source.readArrayList(ButtonBean.class.getClassLoader());
		tableBeansList = source.readArrayList(TableBean.class.getClassLoader());
		webBeansList = source.readArrayList(WebBean.class.getClassLoader());
		imageGalleryBeansList = source.readArrayList(ImageGalleryBean.class.getClassLoader());
		blankSplashBeansList = source.readArrayList(BlankSplashBean.class.getClassLoader());
		listViewBeansList = source.readArrayList(ListViewBean.class.getClassLoader());
		textViewBeansList = source.readArrayList(TextBean.class.getClassLoader());
		mapviewBean = source.readArrayList(MapViewBean.class.getClassLoader());
		facebookBeanList = source.readArrayList(FacebookBean.class.getClassLoader());
		twitterBeanList = source.readArrayList(TwitterBean.class.getClassLoader());
		menuBeanList = source.readArrayList(MenuBean.class.getClassLoader());
		map2DBeanList = source.readArrayList(Map2DBean.class.getClassLoader());
		calendarBeansList = source.readArrayList(CalendarBean.class.getClassLoader());
		webButtonBeanList = source.readArrayList(WebButtonBean.class.getClassLoader());
		multiListViewBeansList = source.readArrayList(MultiListViewBean.class.getClassLoader());
		headerSocialNetworkingList = source.readArrayList(HeaderSocialNetworkingBean.class.getClassLoader());
		imageBean = source.readArrayList(ImageBean.class.getClassLoader());
		segmentViewBeanList = source.readArrayList(SegmentViewBean.class.getClassLoader());

	}

	@Override
	public int describeContents() {
		return hashCode();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {

		dest.writeInt(id);
		dest.writeString(backgroundColor);
		dest.writeString(backgroundImg);
		dest.writeInt(templateId);
		dest.writeString(title);
		dest.writeString(titlebackgroundImage);
		dest.writeString(titleBackgroundColor);
		dest.writeString(titleColor);
		dest.writeInt(titleTextSize);
		dest.writeInt(hasTitle ? 1 : 0);
		dest.writeInt(hasMenu ? 1 : 0);
		dest.writeList(tabbarBeansList);
		dest.writeList(gridBeansList);
		dest.writeList(buttonBeansList);
		dest.writeList(tableBeansList);
		dest.writeList(webBeansList);
		dest.writeList(imageGalleryBeansList);
		dest.writeList(blankSplashBeansList);
		dest.writeList(listViewBeansList);
		dest.writeList(textViewBeansList);
		dest.writeList(mapviewBean);
		dest.writeList(facebookBeanList);
		dest.writeList(twitterBeanList);
		dest.writeList(menuBeanList);
		dest.writeList(map2DBeanList);
		dest.writeList(calendarBeansList);
		dest.writeList(webButtonBeanList);
		dest.writeList(multiListViewBeansList);
		dest.writeList(headerSocialNetworkingList);
		dest.writeList(imageBean);
		dest.writeList(segmentViewBeanList);
	}

	public static final Creator<ScreenBean> CREATOR = new Creator<ScreenBean>() {

		@Override
		public ScreenBean[] newArray(int size) {
			return new ScreenBean[size];
		}

		@Override
		public ScreenBean createFromParcel(Parcel source) {
			return new ScreenBean(source);
		}
	};

}
