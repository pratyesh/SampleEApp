package com.prt.app.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.prt.app.bean.BlankSplashBean;
import com.prt.app.bean.ButtonBean;
import com.prt.app.bean.CalendarBean;
import com.prt.app.bean.EventBean;
import com.prt.app.bean.EventDescriptionBean;
import com.prt.app.bean.EventHeaderBean;
import com.prt.app.bean.FacebookBean;
import com.prt.app.bean.FormBean;
import com.prt.app.bean.GridBean;
import com.prt.app.bean.GridColBean;
import com.prt.app.bean.GridRowBean;
import com.prt.app.bean.HeaderSocialNetworkingBean;
import com.prt.app.bean.ImageBean;
import com.prt.app.bean.ImageGalleryBean;
import com.prt.app.bean.ListRowBean;
import com.prt.app.bean.ListViewBean;
import com.prt.app.bean.Map2DBean;
import com.prt.app.bean.MapViewBean;
import com.prt.app.bean.MenuBean;
import com.prt.app.bean.MenuItemBean;
import com.prt.app.bean.MultiListRowBean;
import com.prt.app.bean.MultiListViewBean;
import com.prt.app.bean.ScreenBean;
import com.prt.app.bean.SegmentBean;
import com.prt.app.bean.SegmentViewBean;
import com.prt.app.bean.TabBarBean;
import com.prt.app.bean.TabBarItemBean;
import com.prt.app.bean.TableBean;
import com.prt.app.bean.TextBean;
import com.prt.app.bean.TextBoxBean;
import com.prt.app.bean.TwitterBean;
import com.prt.app.bean.UrlBean;
import com.prt.app.bean.WebBean;
import com.prt.app.bean.WebButtonBean;

/**
 * @author Pratyesh Singh
 */
public class TemplateParser extends DefaultHandler {
	private boolean currentElement;
	private String currentValue;
	private String tag;
	private EventBean eventBean = null;
	private ScreenBean screenBean = null;
	private TabBarBean tabBarBean = null;
	private TabBarItemBean tabBarItemBean = null;
	private GridBean gridBean = null;
	private GridRowBean gridRowBean = null;
	private GridColBean gridColBean = null;
	private ButtonBean buttonBean = null;
	private TextBoxBean textBoxBean = null;
	private TableBean tableBean = null;
	private FormBean formBean = null;
	private WebBean webBean = null;
	private ImageGalleryBean imageGalleryBean = null;
	private BlankSplashBean blankSplashBean = null;
	private ListRowBean listRowBean = null;
	private ListViewBean listViewBean = null;
	private TextBean textBean = null;
	private MapViewBean mapViewBean = null;
	private FacebookBean facebookBean = null;
	private TwitterBean twitterBean = null;
	private MenuBean menuBean = null;
	private MenuItemBean menuItemBean = null;
	private Map2DBean map2dBean = null;
	private CalendarBean calendarBean = null;
	private EventHeaderBean eventHeaderBean = null;
	private EventDescriptionBean eventDescriptionBean = null;
	private UrlBean urlBean = null;
	private MultiListViewBean multiListViewBean = null;
	private MultiListRowBean multiListRowBean = null;
	private boolean isTableRow = false;
	private int id;
	private WebButtonBean webButtonBean = null;
	private HeaderSocialNetworkingBean headerFacebookBean = null;
	private ImageBean imageInstance = null;
	private SegmentViewBean segmentViewBean = null;
	private SegmentBean segmentBean = null;

	public EventBean getEventBean() {
		return eventBean;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		currentElement = true;

		if (localName.equalsIgnoreCase("event")) {

			eventBean = new EventBean();

		} else if (localName.equalsIgnoreCase("screen")) {

			screenBean = new ScreenBean();

			tag = attributes.getValue("id");
			id = Integer.parseInt(tag);

			screenBean.setId(Integer.parseInt(tag));

			tag = attributes.getValue("hasTitle");
			screenBean.setHasTitle(Boolean.parseBoolean(tag));

			tag = attributes.getValue("title");
			screenBean.setTitle(tag);

			tag = attributes.getValue("titlebackgroundImage");
			screenBean.setTitlebackgroundImage(tag);

			tag = attributes.getValue("titleTextSize");
			screenBean.setTitleTextSize(Integer.parseInt(tag));

			tag = attributes.getValue("titleColor");
			screenBean.setTitleColor(tag);

			tag = attributes.getValue("titleBackgroundColor");
			screenBean.setTitleBackgroundColor(tag);

			tag = attributes.getValue("backgroundColor");
			screenBean.setBackgroundColor(tag);

			tag = attributes.getValue("backgroundImg");
			screenBean.setBackgroundImg(tag);

			tag = attributes.getValue("templateId");
			screenBean.setTemplateId(Integer.parseInt(tag));

			tag = attributes.getValue("hasMenu");
			screenBean.setHasMenu(Boolean.parseBoolean(tag));

			eventBean.setEventMap(id, screenBean);

		} else if (localName.equalsIgnoreCase("splash")) {
			blankSplashBean = new BlankSplashBean();

			tag = attributes.getValue("time");
			blankSplashBean.setTime(Integer.parseInt(tag));

			tag = attributes.getValue("action");
			blankSplashBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("hasUrl");
			blankSplashBean.setHasUrl(Boolean.parseBoolean(tag));

			tag = attributes.getValue("noOfUrls");
			blankSplashBean.setNoOfUrls(Integer.parseInt(tag));

			screenBean.setBlankSplashBeansList(blankSplashBean);

		} else if (localName.equalsIgnoreCase("downLoadUrl")) {

			urlBean = new UrlBean();

			tag = attributes.getValue("name");
			urlBean.setName(tag);

			tag = attributes.getValue("url");
			urlBean.setUrl(tag);

			blankSplashBean.setUrlBeansList(urlBean);

		} else if (localName.equalsIgnoreCase("tabBar")) {

			tabBarBean = new TabBarBean();

			tag = attributes.getValue("numberOfTabs");
			tabBarBean.setNoOfTabs(Integer.parseInt(tag));

			screenBean.setTabbarBeansList(tabBarBean);
		} else if (localName.equalsIgnoreCase("tabBarItem")) {

			tabBarItemBean = new TabBarItemBean();

			tag = attributes.getValue("title");
			tabBarItemBean.setTitle(tag);

			tag = attributes.getValue("image");
			tabBarItemBean.setImage(tag);

			tag = attributes.getValue("linkedScreen");
			tabBarItemBean.setLinkedscreen(Integer.parseInt(tag));

			tabBarBean.setTabBarItemBeansList(tabBarItemBean);

		} else if (localName.equalsIgnoreCase("grid-view")) {

			gridBean = new GridBean();

			tag = attributes.getValue("noOfGridRow");
			gridBean.setNoOfGridRow(Integer.parseInt(tag));

			tag = attributes.getValue("noOfGridCol");
			gridBean.setNoOfGridCol(Integer.parseInt(tag));

			tag = attributes.getValue("verticalSpacing");
			gridBean.setVerticalSpacing(Integer.parseInt(tag));

			tag = attributes.getValue("horizontalSpacing");
			gridBean.setHorizontalSpacing(Integer.parseInt(tag));

			tag = attributes.getValue("gridBackgroundImg");
			gridBean.setGridBackground(tag);

			tag = attributes.getValue("gravity");
			gridBean.setGravity(tag);

			screenBean.setGridBeansList(gridBean);

		} else if (localName.equalsIgnoreCase("grid-row")) {

			gridRowBean = new GridRowBean();
			gridBean.setRowBeans(gridRowBean);

		} else if (localName.equalsIgnoreCase("grid-col")) {

			gridColBean = new GridColBean();

			tag = attributes.getValue("text");
			gridColBean.setText(tag);

			tag = attributes.getValue("action");
			gridColBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("textColor");
			gridColBean.setTextColor(tag);

			tag = attributes.getValue("image");
			gridColBean.setImage(tag);

			gridRowBean.setGridColBeans(gridColBean);

		} else if (localName.equalsIgnoreCase("segment-view")) {

			segmentViewBean = new SegmentViewBean();

			tag = attributes.getValue("backgroundImage");
			segmentViewBean.setBackgroundColor(tag);

			tag = attributes.getValue("backgroundColor");
			segmentViewBean.setBackgroundImg(tag);

			screenBean.setSegmentViewBeanList(segmentViewBean);

		} else if (localName.equalsIgnoreCase("segment")) {

			segmentBean = new SegmentBean();

			tag = attributes.getValue("text");
			segmentBean.setText(tag);

			tag = attributes.getValue("textColor");
			segmentBean.setText(tag);

			segmentViewBean.setSegmentBean(segmentBean);

		} else if (localName.equalsIgnoreCase("table-view")) {

			tableBean = new TableBean();
			screenBean.setTableBeansList(tableBean);

		} else if (localName.equalsIgnoreCase("table-row")) {

			isTableRow = true;
			formBean = new FormBean();

			tag = attributes.getValue("noOfRows");
			formBean.setNoOfRows(Integer.parseInt(tag));

			tableBean.setFormBeansList(formBean);

		} else if (localName.equalsIgnoreCase("text-box")) {

			textBoxBean = new TextBoxBean();

			tag = attributes.getValue("isMultiline");
			textBoxBean.setIsMultiline(Boolean.parseBoolean(tag));

			tag = attributes.getValue("isInputType");
			textBoxBean.setIsInputType(tag);

			tag = attributes.getValue("height");
			textBoxBean.setHeight(Integer.parseInt(tag));

			tag = attributes.getValue("paddingTop");
			textBoxBean.setPaddingTop(Integer.parseInt(tag));

			tag = attributes.getValue("gravity");
			textBoxBean.setGravity(tag);

			formBean.setTextBoxBeansList(textBoxBean);

		} else if (localName.equalsIgnoreCase("button")) {

			buttonBean = new ButtonBean();

			tag = attributes.getValue("width");
			buttonBean.setWidth(Integer.parseInt(tag));

			tag = attributes.getValue("height");
			buttonBean.setHeight(Integer.parseInt(tag));

			tag = attributes.getValue("image");
			buttonBean.setImage(tag);

			tag = attributes.getValue("action");
			buttonBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("paddingTop");
			buttonBean.setPaddingTop(tag);

			tag = attributes.getValue("gravity");
			buttonBean.setGravity(tag);

			tag = attributes.getValue("textColor");
			buttonBean.setTextColor(tag);

			if (isTableRow) {
				formBean.setButtonBeansList(buttonBean);
			} else {
				screenBean.setButtonBeansList(buttonBean);
			}

		} else if (localName.equalsIgnoreCase("web-view")) {
			webBean = new WebBean();
			screenBean.setWebBeansList(webBean);

		} else if (localName.equalsIgnoreCase("image-item")) {
			imageGalleryBean = new ImageGalleryBean();

			tag = attributes.getValue("thumbNailURL");
			imageGalleryBean.setThumbNailURL(tag);

			tag = attributes.getValue("imageURL");
			imageGalleryBean.setImageURL(tag);

			tag = attributes.getValue("height");

			screenBean.setImageGalleryBeansList(imageGalleryBean);

		} else if (localName.equalsIgnoreCase("list-view")) {

			listViewBean = new ListViewBean();

			tag = attributes.getValue("noOfListRow");
			listViewBean.setNoOfListRow(Integer.parseInt(tag));

			tag = attributes.getValue("verticalSpacing");
			listViewBean.setVerticalSpacing(Integer.parseInt(tag));

			tag = attributes.getValue("horizontalSpacing");
			listViewBean.setHorizontalSpacing(Integer.parseInt(tag));

			tag = attributes.getValue("listBackgroundColor");
			listViewBean.setListBackgroundColor(tag);

			tag = attributes.getValue("listBackgroundImg");
			listViewBean.setListBackgroundImg(tag);

			screenBean.setListViewBeansList(listViewBean);
		} else if (localName.equalsIgnoreCase("list-row")) {
			listRowBean = new ListRowBean();

			tag = attributes.getValue("backgroundColor");
			listRowBean.setBackgroundColor(tag);

			tag = attributes.getValue("action");
			listRowBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("imageLeft");
			listRowBean.setImageLeft(tag);

			tag = attributes.getValue("imageLeftHeight");
			listRowBean.setImageLeftHeight(Integer.parseInt(tag));

			tag = attributes.getValue("imageLeftWidth");
			listRowBean.setImageLeftWidth(Integer.parseInt(tag));

			tag = attributes.getValue("text");
			listRowBean.setText(tag);

			tag = attributes.getValue("textColor");
			listRowBean.setTextColor(tag);

			tag = attributes.getValue("textSize");
			listRowBean.setTextSize(Integer.parseInt(tag));

			tag = attributes.getValue("imageRight");
			listRowBean.setImageRight(tag);

			tag = attributes.getValue("imageRightHeight");
			listRowBean.setImageRightHeight(Integer.parseInt(tag));

			tag = attributes.getValue("imageRightWidth");
			listRowBean.setImageRightWidth(Integer.parseInt(tag));

			listViewBean.setListRowBeansList(listRowBean);
		} else if (localName.equalsIgnoreCase("text-view")) {

			textBean = new TextBean();

			tag = attributes.getValue("textsize");
			textBean.settextSize(Integer.parseInt(tag));

			tag = attributes.getValue("textcolor");
			textBean.settextColor(tag);

			tag = attributes.getValue("textstyle");
			textBean.settextStyle(tag);

			tag = attributes.getValue("text");
			textBean.setText(tag);

			tag = attributes.getValue("backgroundColor");
			textBean.setBackgroundColor(tag);

			tag = attributes.getValue("tag");
			textBean.setTag(tag);

			screenBean.setTextViewBeansList(textBean);
		} else if (localName.equalsIgnoreCase("map-view")) {
			mapViewBean = new MapViewBean();

			screenBean.setMapviewBean(mapViewBean);
		} else if (localName.equalsIgnoreCase("face-book")) {
			facebookBean = new FacebookBean();

			screenBean.setFacebookBeanList(facebookBean);
		} else if (localName.equalsIgnoreCase("twitter")) {
			twitterBean = new TwitterBean();

			screenBean.setTwitterBeanList(twitterBean);
		} else if (localName.equalsIgnoreCase("menu")) {

			menuBean = new MenuBean();

			screenBean.setMenuBeanList(menuBean);

		} else if (localName.equalsIgnoreCase("item")) {

			menuItemBean = new MenuItemBean();

			tag = attributes.getValue("name");
			menuItemBean.setItem(tag);

			tag = attributes.getValue("icon");
			menuItemBean.setIcon(tag);

			tag = attributes.getValue("action");
			menuItemBean.setAction(Integer.parseInt(tag));

			menuBean.setMenuItemBeansList(menuItemBean);

		} else if (localName.equalsIgnoreCase("venue-map")) {
			imageInstance = new ImageBean();

			tag = attributes.getValue("image");
			imageInstance.setImage(tag);

			tag = attributes.getValue("height");
			imageInstance.setHeight(Integer.parseInt(tag));

			tag = attributes.getValue("width");
			imageInstance.setWidth(Integer.parseInt(tag));

			tag = attributes.getValue("gravity");
			imageInstance.setGravity(tag);

			screenBean.setImageBean(imageInstance);

		} else if (localName.equalsIgnoreCase("image")) {
			map2dBean = new Map2DBean();

			tag = attributes.getValue("url");
			map2dBean.setImage(tag);

			tag = attributes.getValue("height");
			map2dBean.setHeight(Integer.parseInt(tag));

			tag = attributes.getValue("width");
			map2dBean.setWidth(Integer.parseInt(tag));

			tag = attributes.getValue("gravity");
			map2dBean.setGravity(tag);

			screenBean.setMap2DBeanList(map2dBean);
		} else if (localName.equalsIgnoreCase("calendar-view")) {
			calendarBean = new CalendarBean();

			tag = attributes.getValue("eventHeaderBackgroundColor");
			calendarBean.setEventHeaderBackgroundColor(tag);

			tag = attributes.getValue("eventHeaderBackgroundImg");
			calendarBean.setEventHeaderBackgroundImg(tag);

			tag = attributes.getValue("eventHeaderTextColor");
			calendarBean.setEventHeaderTextColor(tag);

			tag = attributes.getValue("eventHeaderTextSize");
			calendarBean.setEventHeaderTextSize(Integer.parseInt(tag));

			tag = attributes.getValue("eventHeaderStartDate");
			calendarBean.setEventHeaderStartDate(tag);

			tag = attributes.getValue("eventDescriptionBackgroundColor");
			calendarBean.setEventDescriptionBackgroundColor(tag);

			tag = attributes.getValue("eventDescriptionbackgroundImg");
			calendarBean.setEventDescriptionbackgroundImg(tag);

			tag = attributes.getValue("eventDescriptionTextColor");
			calendarBean.setEventDescriptionTextColor(tag);

			tag = attributes.getValue("eventDescriptionTextSize");
			calendarBean.setEventDescriptionTextSize(Integer.parseInt(tag));

			tag = attributes.getValue("eventDescriptionImageRight");
			calendarBean.setEventDescriptionImageRight(tag);

			tag = attributes.getValue("eventDescriptionImageRightHeight");
			calendarBean.setEventDescriptionImageRightHeight(Integer.parseInt(tag));

			tag = attributes.getValue("eventDescriptionImageRightWidth");
			calendarBean.setEventDescriptionImageRightWidth(Integer.parseInt(tag));

			tag = attributes.getValue("groupName");
			calendarBean.setGroupName(tag);

			screenBean.setCalendarBeansList(calendarBean);
		} else if (localName.equalsIgnoreCase("event-header")) {

			eventHeaderBean = new EventHeaderBean();

			tag = attributes.getValue("backgroundColor");
			eventHeaderBean.setBackgroundColor(tag);

			tag = attributes.getValue("backgroundImg");
			eventHeaderBean.setBackgroundImg(tag);

			tag = attributes.getValue("textColor");
			eventHeaderBean.setTextColor(tag);

			tag = attributes.getValue("textSize");
			eventHeaderBean.setTextSize(Integer.parseInt(tag));

			tag = attributes.getValue("startDate");
			eventHeaderBean.setStartDate(tag);

			tag = attributes.getValue("endDate");
			eventHeaderBean.setEndDate(tag);

			calendarBean.setEventHeaderBeansList(eventHeaderBean);

		} else if (localName.equalsIgnoreCase("event-description")) {

			eventDescriptionBean = new EventDescriptionBean();

			tag = attributes.getValue("backgroundColor");
			eventDescriptionBean.setBackgroundColor(tag);

			tag = attributes.getValue("backgroundImg");
			eventDescriptionBean.setBackgroundImg(tag);

			tag = attributes.getValue("textColor");
			eventDescriptionBean.setTextColor(tag);

			tag = attributes.getValue("textSize");
			eventDescriptionBean.setTextSize(Integer.parseInt(tag));

			tag = attributes.getValue("imageRight");
			eventDescriptionBean.setImageRight(tag);

			tag = attributes.getValue("imageRightHeight");
			eventDescriptionBean.setImageRightHeight(Integer.parseInt(tag));

			tag = attributes.getValue("imageRightWidth");
			eventDescriptionBean.setImageRightWidth(Integer.parseInt(tag));

			tag = attributes.getValue("desc");
			eventDescriptionBean.setDesc(tag);

			tag = attributes.getValue("event");
			eventDescriptionBean.setEvent(tag);

			calendarBean.setEventDescriptionBeansList(eventDescriptionBean);

		} else if (localName.equalsIgnoreCase("web-button")) {

			webButtonBean = new WebButtonBean();
			screenBean.setWebButtonBeanList(webButtonBean);

		} else if (localName.equalsIgnoreCase("custom-web-view")) {
			webBean = new WebBean();
			webButtonBean.setWebBeansList(webBean);

		} else if (localName.equalsIgnoreCase("custom-button")) {

			buttonBean = new ButtonBean();

			tag = attributes.getValue("width");
			buttonBean.setWidth(Integer.parseInt(tag));

			tag = attributes.getValue("height");
			buttonBean.setHeight(Integer.parseInt(tag));

			tag = attributes.getValue("image");
			buttonBean.setImage(tag);

			tag = attributes.getValue("action");
			buttonBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("textColor");
			buttonBean.setTextColor(tag);

			webButtonBean.setButtonBeansList(buttonBean);
		} else if (localName.equalsIgnoreCase("mutliline-list-view")) {
			multiListViewBean = new MultiListViewBean();

			tag = attributes.getValue("listBackgroundColor");
			multiListViewBean.setListBackgroundColor(tag);

			tag = attributes.getValue("listBackgroundImg");
			multiListViewBean.setListBackgroundImg(tag);

			tag = attributes.getValue("noOfListRow");
			multiListViewBean.setNoOfListRow(Integer.parseInt(tag));

			tag = attributes.getValue("verticalSpacing");
			multiListViewBean.setVerticalSpacing(Integer.parseInt(tag));

			tag = attributes.getValue("horizontalSpacing");
			multiListViewBean.setHorizontalSpacing(Integer.parseInt(tag));

			tag = attributes.getValue("headerTextColor");
			multiListViewBean.setheaderTextColor(tag);

			tag = attributes.getValue("headerTextSize");
			multiListViewBean.setheaderTextSize(tag);

			tag = attributes.getValue("headerTextStyle");
			multiListViewBean.setheaderTextStyle(tag);

			tag = attributes.getValue("subTitleTextColor");
			multiListViewBean.setsubTitleTextColor(tag);

			tag = attributes.getValue("subTitleTextSize");
			multiListViewBean.setsubTitleTextSize(tag);

			tag = attributes.getValue("subTitleTextStyle");
			multiListViewBean.setsubTitleTextStyle(tag);

			tag = attributes.getValue("smallTextColor");
			multiListViewBean.setsmallTextColor(tag);

			tag = attributes.getValue("smallTextSize");
			multiListViewBean.setsmallTextSize(tag);

			tag = attributes.getValue("smallTextStyle");
			multiListViewBean.setsmallTextStyle(tag);

			tag = attributes.getValue("action");
			multiListViewBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("hasSearch");
			multiListViewBean.setSearch(Boolean.parseBoolean(tag));

			screenBean.setMultiListViewBeansList(multiListViewBean);

		} else if (localName.equalsIgnoreCase("multi-list-row")) {

			multiListRowBean = new MultiListRowBean();

			tag = attributes.getValue("backgroundColor");
			multiListRowBean.setBackgroundColor(tag);

			tag = attributes.getValue("action");
			multiListRowBean.setAction(Integer.parseInt(tag));

			tag = attributes.getValue("headerText");
			multiListRowBean.setHeaderText(tag);

			tag = attributes.getValue("subTitle");
			multiListRowBean.setSubTitle(tag);

			tag = attributes.getValue("smallText");
			multiListRowBean.setSmallText(tag);

			tag = attributes.getValue("textColor");
			multiListRowBean.setTextColor(tag);

			multiListViewBean.setMultiListRowBeansList(multiListRowBean);
		} else if (localName.equalsIgnoreCase("header-social_networking")) {
			headerFacebookBean = new HeaderSocialNetworkingBean();

			tag = attributes.getValue("isAvailable");
			headerFacebookBean.setSocialNetworkingIsAvailable(Boolean.parseBoolean(tag));

			tag = attributes.getValue("url");
			headerFacebookBean.setSocialNetworkingUrl(tag);

			tag = attributes.getValue("action");
			headerFacebookBean.setAction(Integer.parseInt(tag));

			screenBean.setHeaderSocialNetworkingList(headerFacebookBean);
		}

		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		currentElement = false;

		// if (currentValue.equalsIgnoreCase(""))
		// return;

		if (localName.equalsIgnoreCase("table-row")) {
			isTableRow = false;
		} else if (localName.equalsIgnoreCase("text-box")) {
			textBoxBean.setText(currentValue);
		} else if (localName.equalsIgnoreCase("button")) {
			buttonBean.setText(currentValue);
		} else if (localName.equalsIgnoreCase("url")) {
			webBean.setUrl(currentValue);
		} else if (localName.equalsIgnoreCase("address")) {
			mapViewBean.setAddress(currentValue);
		} else if (localName.equalsIgnoreCase("map-key")) {
			mapViewBean.setMapKey(currentValue);
		} else if (localName.equalsIgnoreCase("app-id")) {
			facebookBean.setAppId(currentValue);
		} else if (localName.equalsIgnoreCase("app-key")) {
			facebookBean.setAppKey(currentValue);
		} else if (localName.equalsIgnoreCase("app-secret")) {
			facebookBean.setAppSecret(currentValue);
		} else if (localName.equalsIgnoreCase("consumer-key")) {
			twitterBean.setConsumerKey(currentValue);
		} else if (localName.equalsIgnoreCase("consumer-secret")) {
			twitterBean.setConsumerSecret(currentValue);
		} else if (localName.equalsIgnoreCase("event-header")) {
			eventHeaderBean.setEventHeader(currentValue);
		} else if (localName.equalsIgnoreCase("event-description")) {
			eventDescriptionBean.setEventDescription(currentValue);
		} else if (localName.equalsIgnoreCase("custom-button")) {
			buttonBean.setText(currentValue);
		} else if (localName.equalsIgnoreCase("downLoadUrl")) {
			urlBean.setUrl(currentValue);
		}

		currentValue = "";

		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currentElement) {
			currentValue = new String(ch, start, length);
			currentElement = false;
		}
		super.characters(ch, start, length);

	}
}
