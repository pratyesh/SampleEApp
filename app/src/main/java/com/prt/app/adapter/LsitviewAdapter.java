package com.prt.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ledexpo.android.R;
import com.prt.app.bean.ExhibitorBean;
import com.prt.app.bean.ListRowBean;
import com.prt.app.bean.NotificationBean;
import com.prt.app.bean.ScheduleBean;
import com.prt.app.bean.SpeakerBean;
import com.prt.app.util.IdentityConstant;
import com.prt.app.util.ImageUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LsitviewAdapter<T> extends BaseAdapter {

	private Activity activity = null;
	private LayoutInflater inflater = null;

	ArrayList<T> dataList;
	int templateId;

	public LsitviewAdapter(Activity activity, ArrayList dataList, int templateId) {
		this.activity = activity;
		this.dataList = dataList;
		this.templateId = templateId;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public static class ViewHolder {
		public TextView textView;
		public TextView headerTextView;
		public TextView subTitleTextView;
		public TextView smallTextView;
		public ImageView left_imageView;
		public ImageView right_imageView;
	}

	public int getCount() {

		return dataList.size();
	}

	public T getItem(int position) {

		return dataList.get(position);
	}

	public long getItemId(int position) {

		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder = null;
		if (convertView == null) {
			if (templateId == IdentityConstant.EXHIBITORS.VALUE) {// exhibitor
				vi = inflater.inflate(R.layout.multiline_list_item, null);
				holder = new ViewHolder();
				holder.headerTextView = (TextView) vi.findViewById(R.id.headerTextView);
				holder.subTitleTextView = (TextView) vi.findViewById(R.id.subTitleTextView);
				holder.smallTextView = (TextView) vi.findViewById(R.id.smallTextView);
				// holder.linearLayout = (LinearLayout) vi.findViewById(R.id.multiLineLinearLayout);
				holder.left_imageView = (ImageView) vi.findViewById(R.id.leftImageView);

			} else if (templateId == IdentityConstant.DEFAULT_LIST.VALUE || templateId == IdentityConstant.GALLERY_FOLDERS.VALUE || templateId == IdentityConstant.FLOOR_FOLDERS.VALUE
					|| templateId == IdentityConstant.PRESENTATION_FOLDERS.VALUE) {// simple on text
				vi = inflater.inflate(R.layout.list_item, null);
				holder = new ViewHolder();
				holder.textView = (TextView) vi.findViewById(R.id.time_text);

			} else if (templateId == IdentityConstant.SCHEDULE.VALUE || templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
				vi = inflater.inflate(R.layout.list_schedule_item, null);
				holder = new ViewHolder();
				holder.textView = (TextView) vi.findViewById(R.id.time_text);
				holder.headerTextView = (TextView) vi.findViewById(R.id.schedule_name);
				holder.subTitleTextView = (TextView) vi.findViewById(R.id.schedule_date_time);
				holder.smallTextView = (TextView) vi.findViewById(R.id.schedule_desc);
				holder.left_imageView = (ImageView) vi.findViewById(R.id.left_image);
				holder.right_imageView = (ImageView) vi.findViewById(R.id.aad_to_calender);

			} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE.VALUE) {
				vi = inflater.inflate(R.layout.image_gallery_list, null);

			} else if (templateId == IdentityConstant.SPEAKER.VALUE || templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {// speaker
				vi = inflater.inflate(R.layout.speaker_list_item, null);
				holder = new ViewHolder();
				holder.headerTextView = (TextView) vi.findViewById(R.id.headerTextView);
				holder.subTitleTextView = (TextView) vi.findViewById(R.id.subTitleTextView);
				holder.smallTextView = (TextView) vi.findViewById(R.id.smallTextView);
				// holder.linearLayout = (LinearLayout) vi.findViewById(R.id.multiLineLinearLayout);
				holder.left_imageView = (ImageView) vi.findViewById(R.id.leftImageView);

			} else if (templateId == IdentityConstant.NOTIFICATION.VALUE) {
				vi = inflater.inflate(R.layout.list_notification_item, null);

				holder = new ViewHolder();
				holder.headerTextView = (TextView) vi.findViewById(R.id.title_text);
				holder.subTitleTextView = (TextView) vi.findViewById(R.id.message_text);
				holder.smallTextView = (TextView) vi.findViewById(R.id.time_text);
			}

			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		T item = getItem(position);
		if (item instanceof ListRowBean) {
			ListRowBean listRowBean = (ListRowBean) item;
			holder.textView.setText(listRowBean.getText());
		} else if (templateId == IdentityConstant.NOTIFICATION.VALUE) {
			NotificationBean listRowBean = (NotificationBean) item;
			holder.headerTextView.setText(listRowBean.getTitle());
			holder.subTitleTextView.setText(listRowBean.getMessage());

			String str_date = listRowBean.getTime();// "29/02/2013";
			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = new Date(Long.parseLong(str_date));
			DateFormat df = new SimpleDateFormat("HH:mm a  EEEE MMM, dd yyyy");

			holder.smallTextView.setText(df.format(dt));

		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE.VALUE) {
			String listRowBean = (String) item;
			ImageUtil.getInstance().loadImage(listRowBean, ((ImageView) vi));

		} else if (templateId != IdentityConstant.GALLERY_FOLDERS_IMAGE.VALUE && item instanceof String) {
			String listRowBean = (String) item;
			holder.textView.setText(listRowBean);

		} else if (templateId == IdentityConstant.SCHEDULE.VALUE || templateId == IdentityConstant.SPEAKERDETAILS.VALUE && item instanceof ScheduleBean) {
			ScheduleBean listRowBean = (ScheduleBean) item;
			holder.textView.setText(listRowBean.getStartTime().toUpperCase() + " - " + listRowBean.getEndTime().toUpperCase());
			holder.headerTextView.setText(listRowBean.getScheduleName());

			try {
				String str_date = listRowBean.getScheduleDate();// "29/02/2013";
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dt = sdf.parse(str_date);
				DateFormat df = new SimpleDateFormat("EEEE MMM, dd yyyy");

				holder.subTitleTextView.setText(df.format(dt) + " " + listRowBean.getStartTime().toUpperCase() + " - " + listRowBean.getEndTime().toUpperCase());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			holder.smallTextView.setText(listRowBean.getDescription());
			// holder.left_imageView.setImageResource(resId);
			// holder.right_imageView.setImageResource(resId);

		} else if (item instanceof ExhibitorBean) {
			ExhibitorBean listRowBean = (ExhibitorBean) item;
			holder.headerTextView.setText(listRowBean.getCompanyName());
			holder.subTitleTextView.setText(listRowBean.getCountry());
			holder.smallTextView.setText("Hall No: " + listRowBean.getHallNo() + " Booth No: " + listRowBean.getBoothNum());

			ImageUtil.getInstance().loadImage(listRowBean.getImage(), holder.left_imageView);
		} else if (item instanceof SpeakerBean) {
			SpeakerBean listRowBean = (SpeakerBean) item;
			holder.headerTextView.setText(listRowBean.getSpeakerName());
			holder.subTitleTextView.setText(listRowBean.getDesignation());
			holder.smallTextView.setText(listRowBean.getCompanyName());

			ImageUtil.getInstance().loadImage(listRowBean.getImage(), holder.left_imageView);

		}

		return vi;
	}
}
