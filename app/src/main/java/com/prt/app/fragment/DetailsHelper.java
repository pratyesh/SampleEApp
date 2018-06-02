package com.prt.app.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.adapter.LsitviewAdapter;
import com.prt.app.bean.ExhibitorBean;
import com.prt.app.bean.ListRowBean;
import com.prt.app.bean.ScheduleBean;
import com.prt.app.bean.SpeakerBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.fragment_basecontroller.BaseControllerFragment;
import com.prt.app.util.Constants;
import com.prt.app.util.IdentityConstant;
import com.prt.app.util.ImageUtil;
import com.prt.app.util.Utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public final class DetailsHelper extends Fragment {

	private static final String KEY_CONTENT = "TestFragment:Content";
	private ExhibitorBean exhibitorBean;
	private SpeakerBean speakerBean;
	private ScheduleBean scheduleBean;
	private String image_gallery;
	private int templateId;
	private ArrayList<SpeakerBean> scheduleSpeakerBeanList;
	private ArrayList<ScheduleBean> scheduleBeanList;
	Bundle url_bundle = new Bundle();

	public static DetailsHelper newInstance(Object bean, int templateId) {

		DetailsHelper fragment = new DetailsHelper();

		fragment.templateId = templateId;
		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {
			fragment.exhibitorBean = (ExhibitorBean) bean;
		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			fragment.speakerBean = (SpeakerBean) bean;
		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {
			fragment.scheduleBean = (ScheduleBean) bean;
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			fragment.image_gallery = (String) bean;
		} else {
			fragment.exhibitorBean = (ExhibitorBean) bean;
		}

		return fragment;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
			exhibitorBean = savedInstanceState.getParcelable(KEY_CONTENT);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (container == null) {
			return null;
		}
		View view;
		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {
			view = inflater.inflate(R.layout.exhibitor_details, container, false);

		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			view = inflater.inflate(R.layout.speaker_details, container, false);

		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {
			view = inflater.inflate(R.layout.schedule_details, container, false);

		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			view = inflater.inflate(R.layout.fragment_image_zoom, container, false);

		} else {
			view = inflater.inflate(R.layout.fragment_details_helper, container, false);
		}
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {

			HashMap<String, String> data = new HashMap<String, String>();
			data.put(Constants.SCREEN, Constants.FLURRY_EXHIBITOR_DETAIL);
			data.put(Constants.SUB_EVENT, Constants.FLURRY_OPEN_EXHIBITOR_DETAIL);
			FlurryAgent.logEvent(Constants.FLURRY_EXHIBITOR_DETAIL, data);

			TextView company_name = (TextView) getView().findViewById(R.id.company_name);
			company_name.setText(exhibitorBean.getCompanyName());

			TextView company_address = (TextView) getView().findViewById(R.id.company_address);
			company_address.setText(exhibitorBean.getCountry());

			TextView company_category = (TextView) getView().findViewById(R.id.company_category);
			company_category.setText("Hall No: " + exhibitorBean.getHallNo() + " Booth No: " + exhibitorBean.getBoothNum());

			TextView details_button = (TextView) getView().findViewById(R.id.details_button);
			details_button.setOnClickListener(onClickListener);

			TextView notes_button = (TextView) getView().findViewById(R.id.notes_button);
			notes_button.setOnClickListener(onClickListener);

			TextView company_desc = (TextView) getView().findViewById(R.id.company_desc);
			company_desc.setText(Html.fromHtml(exhibitorBean.getDescription()));

			url_bundle.putString("url", exhibitorBean.getUrl());

			getView().findViewById(R.id.email_icon).setOnClickListener(onClickListener);
			getView().findViewById(R.id.map_icon).setOnClickListener(onClickListener);
			getView().findViewById(R.id.web_icon).setOnClickListener(onClickListener);
			getView().findViewById(R.id.fev_icon_layout).setOnClickListener(onClickListener);
			ImageView exhibitor_icon = (ImageView) getView().findViewById(R.id.exhibitor_icon);
			ImageUtil.getInstance().loadImage(exhibitorBean.getImage(), exhibitor_icon);

			if (exhibitorBean.getFavourites() == 0) {
				ImageView imageView = (ImageView) getView().findViewById(R.id.fev_icon);
				imageView.setImageResource(android.R.drawable.star_big_off);
				imageView.setTag("0");
			} else {
				ImageView imageView = (ImageView) getView().findViewById(R.id.fev_icon);
				imageView.setImageResource(android.R.drawable.star_big_on);
				imageView.setTag("1");
			}

		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			HashMap<String, String> data = new HashMap<String, String>();
			data.put(Constants.SCREEN, Constants.FLURRY_SPEAKER_DETAIL);
			data.put(Constants.SUB_EVENT, Constants.FLURRY_OPEN_SPEAKER_DETAIL);
			FlurryAgent.logEvent(Constants.FLURRY_SPEAKER_DETAIL, data);

			url_bundle.putString("url", speakerBean.getUrl());
			TextView company_name = (TextView) getView().findViewById(R.id.company_name);
			company_name.setText(speakerBean.getSpeakerName());

			TextView company_address = (TextView) getView().findViewById(R.id.company_address);
			company_address.setText(speakerBean.getDesignation());

			TextView company_category = (TextView) getView().findViewById(R.id.company_category);
			company_category.setText(speakerBean.getCompanyName());

			TextView details_button = (TextView) getView().findViewById(R.id.details_button);
			details_button.setOnClickListener(onClickListener);

			TextView notes_button = (TextView) getView().findViewById(R.id.notes_button);
			notes_button.setOnClickListener(onClickListener);

			TextView schedule_button = (TextView) getView().findViewById(R.id.schedule_button);
			schedule_button.setOnClickListener(onClickListener);

			ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 3);
			scheduleBeanList = executionQuery.getScheduleListForSpeaker(speakerBean.getSchedule().split(","));
			executionQuery.closeDatabase();

			ListView lv = (ListView) getView().findViewById(R.id.schedule_speakers_list);
			lv.setAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), scheduleBeanList, templateId));
			lv.setOnItemClickListener(onItemClickListener);

			TextView company_desc = (TextView) getView().findViewById(R.id.company_desc);
			company_desc.setText(Html.fromHtml(speakerBean.getDesc()));

			getView().findViewById(R.id.email_icon).setOnClickListener(onClickListener);
			getView().findViewById(R.id.web_icon).setOnClickListener(onClickListener);
			getView().findViewById(R.id.fev_icon_layout).setOnClickListener(onClickListener);

			ImageView exhibitor_icon = (ImageView) getView().findViewById(R.id.exhibitor_icon);
			ImageUtil.getInstance().loadImage(speakerBean.getImage(), exhibitor_icon);

			if (speakerBean.getFavourites() == 0) {
				ImageView imageView = (ImageView) getView().findViewById(R.id.fev_icon);
				imageView.setImageResource(android.R.drawable.star_big_off);
				imageView.setTag("0");
			} else {
				ImageView imageView = (ImageView) getView().findViewById(R.id.fev_icon);
				imageView.setImageResource(android.R.drawable.star_big_on);
				imageView.setTag("1");
			}
		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {

			HashMap<String, String> data = new HashMap<String, String>();
			data.put(Constants.SCREEN, Constants.FLURRY_SCHEDULE_DETAILS);
			data.put(Constants.SUB_EVENT, Constants.FLURRY_OPEN_SCHEDULE_DETAILS);
			FlurryAgent.logEvent(Constants.FLURRY_SCHEDULE_DETAILS, data);

			TextView schedule_name = (TextView) getView().findViewById(R.id.schedule_name);
			schedule_name.setText(scheduleBean.getScheduleName());

			TextView schedule_date = (TextView) getView().findViewById(R.id.schedule_date);
			try {
				String str_date = scheduleBean.getScheduleDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.util.Date dt = sdf.parse(str_date);
				DateFormat df = new SimpleDateFormat("EEEE MMM, dd yyyy");
				schedule_date.setText(df.format(dt));
			} catch (ParseException e) {
				e.printStackTrace();
			}

			TextView schedule_time = (TextView) getView().findViewById(R.id.schedule_time);
			schedule_time.setText(scheduleBean.getStartTime().toUpperCase() + " - " + scheduleBean.getEndTime().toUpperCase());

			TextView schedule_desc = (TextView) getView().findViewById(R.id.schedule_desc);
			schedule_desc.setText(scheduleBean.getDescription());

			/**** -------------------- */
			ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 2);
			scheduleSpeakerBeanList = executionQuery.getSpeakersListForSchedule(scheduleBean.getSpeakers().split(","));
			executionQuery.closeDatabase();

			ListView schedule_speakers_list = (ListView) getView().findViewById(R.id.schedule_speakers_list);

			LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View schedule_header = inflater.inflate(R.layout.schedule_speaker_header, null);
			schedule_speakers_list.addHeaderView(schedule_header);
			schedule_header.setOnClickListener(null);

			((TextView) schedule_header.findViewById(R.id.textView)).setText("Speakers");
			ImageView add_to_calender = (ImageView) getView().findViewById(R.id.aad_to_calender);

			add_to_calender.setOnClickListener(onClickListener);

			schedule_header.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

				}
			});

			/**** -------------------- */
			schedule_speakers_list.setAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), scheduleSpeakerBeanList, templateId));
			schedule_speakers_list.setOnItemClickListener(onItemClickListener);
			if (scheduleSpeakerBeanList.size() == 0) {
				schedule_speakers_list.setVisibility(View.GONE);
			} else {
				schedule_speakers_list.setVisibility(View.VISIBLE);
			}

		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			ImageView image = (ImageView) getView().findViewById(R.id.image);
			// imageLoader.displayImage(image_gallery, image, options);
			final ProgressBar spinner = (ProgressBar) getView().findViewById(R.id.loading);
			ImageUtil.getInstance().loadImage(image_gallery, image);
		} else {
			// TextView text = (TextView) getView().findViewById(R.id.textView1);
			// text.setText(mContent);
		}

	}

	View.OnClickListener onClickListenerNotes = new OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.notes_discard:
				Toast.makeText(getActivity(), "Notes Discarded", Toast.LENGTH_SHORT).show();
				if (exhibitorBean != null) {
					((EditText) getView().findViewById(R.id.notes_edit)).setText(exhibitorBean.getNotes());
				} else if (speakerBean != null) {
					((EditText) getView().findViewById(R.id.notes_edit)).setText(speakerBean.getNotes());
				}
				break;
			case R.id.notes_save:

				String text = ((EditText) getView().findViewById(R.id.notes_edit)).getText().toString().trim();
				if (!text.equals("")) {

					if (exhibitorBean != null) {
						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
						executionQuery.updateNotesOfExhibitor(exhibitorBean.getExhibitor_id(), text);
						executionQuery.closeDatabase();
						exhibitorBean.setNotes(text);

					} else if (speakerBean != null) {
						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 2);
						executionQuery.updateNotesOfSpeaker(speakerBean.getSpeakerId(), text);
						executionQuery.closeDatabase();
						speakerBean.setNotes(text);
					}
				}
				Toast.makeText(getActivity(), "Notes Saved", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;

			}
			getView().findViewById(R.id.notes_discard).setVisibility(View.GONE);
			getView().findViewById(R.id.notes_save).setVisibility(View.GONE);

		}
	};
	View.OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.company_name:

				break;
			case R.id.details_button:
				getView().findViewById(R.id.company_desc).setVisibility(View.VISIBLE);
				getView().findViewById(R.id.notes_edit).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_save).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_discard).setVisibility(View.GONE);
				getView().findViewById(R.id.details_button).setBackgroundResource(R.drawable.ractangle_selected);
				getView().findViewById(R.id.notes_button).setBackgroundResource(R.drawable.ractangle_unselected);

				if (speakerBean != null) {
					getView().findViewById(R.id.schedule_button).setBackgroundResource(R.drawable.ractangle_unselected);
					getView().findViewById(R.id.schedule_speakers_list).setVisibility(View.GONE);
				}
				break;
			case R.id.schedule_button:
				getView().findViewById(R.id.company_desc).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_edit).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_save).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_discard).setVisibility(View.GONE);
				getView().findViewById(R.id.details_button).setBackgroundResource(R.drawable.ractangle_unselected);
				getView().findViewById(R.id.notes_button).setBackgroundResource(R.drawable.ractangle_unselected);

				if (speakerBean != null) {
					getView().findViewById(R.id.schedule_button).setBackgroundResource(R.drawable.ractangle_selected);
					getView().findViewById(R.id.schedule_speakers_list).setVisibility(View.VISIBLE);
				}
				break;
			case R.id.notes_button:

				getView().findViewById(R.id.details_button).setBackgroundResource(R.drawable.ractangle_unselected);
				getView().findViewById(R.id.notes_button).setBackgroundResource(R.drawable.ractangle_selected);
				getView().findViewById(R.id.company_desc).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_edit).setVisibility(View.VISIBLE);
				getView().findViewById(R.id.notes_discard).setVisibility(View.GONE);
				getView().findViewById(R.id.notes_save).setVisibility(View.GONE);

				if (speakerBean != null) {
					getView().findViewById(R.id.schedule_button).setBackgroundResource(R.drawable.ractangle_unselected);
					getView().findViewById(R.id.schedule_speakers_list).setVisibility(View.GONE);
				}

				getView().findViewById(R.id.notes_discard).setOnClickListener(onClickListenerNotes);
				getView().findViewById(R.id.notes_save).setOnClickListener(onClickListenerNotes);

				((EditText) getView().findViewById(R.id.notes_edit)).setOnTouchListener(new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						getView().findViewById(R.id.notes_discard).setVisibility(View.VISIBLE);
						getView().findViewById(R.id.notes_save).setVisibility(View.VISIBLE);

						return false;
					}
				});

				if (exhibitorBean != null) {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_EXHIBITOR_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_EXHIBITOR_NOTE);
					FlurryAgent.logEvent(Constants.FLURRY_EXHIBITOR_DETAIL, data);

					((EditText) getView().findViewById(R.id.notes_edit)).setText(exhibitorBean.getNotes());
				} else if (speakerBean != null) {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_SPEAKER_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_SPEAKER_NOTE);
					FlurryAgent.logEvent(Constants.FLURRY_SPEAKER_DETAIL, data);

					((EditText) getView().findViewById(R.id.notes_edit)).setText(speakerBean.getNotes());
				}

				break;
			case R.id.map_icon:
				if (TextUtils.isEmpty(exhibitorBean.getFloor_info())) {
					Crouton.makeText(getActivity(), "Map it info not available for this Exhibitor", Style.INFO).show();
					return;
				}
				if (exhibitorBean != null) {
					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_EXHIBITOR_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_EXHIBITOR_MAP_IT);
					FlurryAgent.logEvent(Constants.FLURRY_EXHIBITOR_DETAIL, data);
				} else {

				}

				ImageZoom imageZoom = new ImageZoom();
				Bundle bundle = new Bundle();
				// bundle.putString("captured_imagepath", "sample_exhibitor_position_on_floor_plan.jpg");
				bundle.putString("captured_imagepath", exhibitorBean.getFloor_info());

				imageZoom.setArguments(bundle);
				((BaseControllerFragment) DetailsHelper.this.getParentFragment().getParentFragment()).replaceFragment(imageZoom, true);
				break;
			case R.id.email_icon:// send meeting request

				Bundle _bundle = new Bundle();
				String msg = "Meeting Request not available for this Person";
				if (exhibitorBean != null) {
					msg = "Meeting Request not available for this Exhibitor";
					_bundle.putString("to", exhibitorBean.getEmail());

					_bundle.putString("receiverName", exhibitorBean.getContactPerson());
					_bundle.putInt("eventId", exhibitorBean.getEventId());

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_EXHIBITOR_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_EXHIBITOR_MEETING_REQUEST);
					FlurryAgent.logEvent(Constants.FLURRY_EXHIBITOR_DETAIL, data);
				} else {
					msg = "Meeting Request not available for this Speaker";
					_bundle.putString("to", speakerBean.getEmailId());
					_bundle.putString("receiverName", speakerBean.getSpeakerName());
					_bundle.putInt("eventId", speakerBean.getEventId());

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_SPEAKER_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_SPEAKER_MEETING_REQUEST);
					FlurryAgent.logEvent(Constants.FLURRY_SPEAKER_DETAIL, data);
				}

				if (!Utility.checkInternetConnection(getActivity())) {
					Crouton.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Style.INFO).show();
					return;
				}
				if (TextUtils.isEmpty(_bundle.getString("to"))) {
					Crouton.makeText(getActivity(), msg, Style.INFO).show();
					return;
				}

				SendMeetingRequest sendMeetingRequest = new SendMeetingRequest();

				sendMeetingRequest.setArguments(_bundle);
				((BaseControllerFragment) DetailsHelper.this.getParentFragment().getParentFragment()).replaceFragment(sendMeetingRequest, true);
				break;
			case R.id.web_icon:
				if (!Utility.checkInternetConnection(getActivity())) {
					Crouton.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Style.INFO).show();
					return;
				}
				if (TextUtils.isEmpty(url_bundle.getString("url"))) {
					Crouton.makeText(getActivity(), "Web page not available", Style.INFO).show();
					return;
				}
				if (exhibitorBean != null) {
					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_EXHIBITOR_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_EXHIBITOR_WEB_PAGE);
					FlurryAgent.logEvent(Constants.FLURRY_EXHIBITOR_DETAIL, data);
				} else {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_SPEAKER_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_SPEAKER_WEB_PAGE);
					FlurryAgent.logEvent(Constants.FLURRY_SPEAKER_DETAIL, data);
				}
				WebFragment webFragment = new WebFragment();
				webFragment.setArguments(url_bundle);
				((BaseControllerFragment) DetailsHelper.this.getParentFragment().getParentFragment()).replaceFragment(webFragment, true);
				break;
			case R.id.fev_icon_layout:
				ImageView imageView = (ImageView) getView().findViewById(R.id.fev_icon);
				Integer tag = Integer.parseInt((String) imageView.getTag());

				if (exhibitorBean != null) {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_EXHIBITOR_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_EXHIBITOR_FAVOURITES);
					FlurryAgent.logEvent(Constants.FLURRY_EXHIBITOR_DETAIL, data);
				} else {

					HashMap<String, String> data = new HashMap<String, String>();
					data.put(Constants.SCREEN, Constants.FLURRY_SPEAKER_DETAIL);
					data.put(Constants.SUB_EVENT, Constants.FLURRY_SPEAKER_FAVOURITES);
					FlurryAgent.logEvent(Constants.FLURRY_SPEAKER_DETAIL, data);
				}

				if (tag == 0) {
					imageView.setImageResource(android.R.drawable.star_big_on);
					imageView.setTag("1");

					if (exhibitorBean != null) {

						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
						executionQuery.updateFavoriteStatusExhibitor(exhibitorBean.getExhibitor_id(), 1);
						executionQuery.closeDatabase();
						exhibitorBean.setFavourites(1);
					} else if (speakerBean != null) {
						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 2);
						executionQuery.updateFavoriteStatusSpeaker(speakerBean.getSpeakerId(), 1);
						executionQuery.closeDatabase();
						speakerBean.setFavourites(1);
					}

				} else {
					imageView.setImageResource(android.R.drawable.star_big_off);
					imageView.setTag("0");
					if (exhibitorBean != null) {

						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
						executionQuery.updateFavoriteStatusExhibitor(exhibitorBean.getExhibitor_id(), 0);
						executionQuery.closeDatabase();
						exhibitorBean.setFavourites(0);
					} else if (speakerBean != null) {
						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 2);
						executionQuery.updateFavoriteStatusSpeaker(speakerBean.getSpeakerId(), 0);
						executionQuery.closeDatabase();
						speakerBean.setFavourites(0);
					}
				}

				break;
			case R.id.aad_to_calender:

				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

				builder.setTitle("Add Event");

				builder.setMessage("Would you like to add this event to your Android Calendar.");

				builder.setIcon(android.R.drawable.ic_dialog_alert);

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						// CommonFunctions.writeScheduleJson(scheduleBean.getScheduleName(), scheduleBean.getScheduleDate(), scheduleBean.getEndTime(), scheduleBean.getStartTime(), Constants.favScheduleFile);

						String formatString = "dd-MM-yyyy HH:mm";
						SimpleDateFormat df = new SimpleDateFormat(formatString);

						try {

							String date = scheduleBean.getScheduleDate() + " " + scheduleBean.getStartTime();

							final Date startDate = df.parse(date);

							final Calendar calendar = Calendar.getInstance();
							calendar.setTime(startDate);

							String[] startTime = scheduleBean.getStartTime().split(":");
							String hr = startTime[0];
							String mm = startTime[1];

							String[] am_pm = mm.split(" ");
							String min = am_pm[0];
							String _am = am_pm[1];

							int flag_am;
							if (_am.equalsIgnoreCase("AM")) {
								flag_am = 0;
							} else {
								flag_am = 1;
							}

							String[] endTime = scheduleBean.getEndTime().split(":");
							String e1 = endTime[0];
							String e2 = endTime[1];

							String[] e_am_pm = e2.split(" ");
							String e_min = e_am_pm[0];
							String e_am = e_am_pm[1];

							int eflag_am;
							if (e_am.equalsIgnoreCase("AM")) {
								eflag_am = 0;
							} else {
								eflag_am = 1;
							}

							int hDiff = Integer.parseInt(e1) - Integer.parseInt(hr);
							int mDiff = Integer.parseInt(e_min) - Integer.parseInt(min);

							// reminder insert

							Intent intent = new Intent(Intent.ACTION_EDIT);
							intent.setType("vnd.android.cursor.item/event");
							intent.putExtra("title", getResources().getString(R.string.app_name));
							intent.putExtra("description", scheduleBean.getDescription());
							intent.putExtra("beginTime", calendar.getTimeInMillis());
							calendar.add(Calendar.HOUR, hDiff);
							calendar.add(Calendar.MINUTE, mDiff);
							calendar.add(Calendar.AM_PM, eflag_am);
							intent.putExtra("endTime", calendar.getTimeInMillis());
							intent.putExtra("eventLocation", Constants.EVENT_LOCATOIN);
							intent.putExtra("hasAlarm", 1);
							getActivity().startActivity(intent);

						} catch (Exception e) {

							e.printStackTrace();
						}
					}

				});

				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

					}

				});

				builder.setOnCancelListener(new OnCancelListener() {

					public void onCancel(DialogInterface dialog) {

						System.out.println("onCancel");
					}

				});

				builder.show();

				break;
			default:
				break;
			}
		}
	};

	AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {

				try {
					int newAction = 2201;
					int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();

					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("speakerBeanList", scheduleSpeakerBeanList);
					bundle.putInt("clickedIndex", position - 1);
					Utility.startAction(DetailsHelper.this.getParentFragment(), newTemplateId, newAction, true, bundle);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
				Bundle bundle = new Bundle();
				bundle.putInt("clickedIndex", position);
				bundle.putParcelableArrayList("scheduleList", scheduleBeanList);
				Utility.startAction(DetailsHelper.this.getParentFragment(), 21, 2202, true, bundle);
			}
		}
	};

	@Override
	public void onSaveInstanceState(Bundle outState) {

		super.onSaveInstanceState(outState);
		outState.putParcelable(KEY_CONTENT, exhibitorBean);
	}

	@Override
	public void onStart() {

		super.onStart();
		FlurryAgent.setCaptureUncaughtExceptions(true);
		FlurryAgent.onStartSession(getActivity(), Constants.flurryKey);
	}

	@Override
	public void onStop() {

		super.onStop();
		FlurryAgent.onEndSession(getActivity());
	}
}
