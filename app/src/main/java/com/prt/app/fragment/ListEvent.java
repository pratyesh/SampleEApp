package com.prt.app.fragment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.adapter.LsitviewAdapter;
import com.prt.app.api.HTTP_ApiRequest;
import com.prt.app.bean.ExhibitorBean;
import com.prt.app.bean.ListRowBean;
import com.prt.app.bean.NotificationBean;
import com.prt.app.bean.ScheduleBean;
import com.prt.app.bean.ScreenBean;
import com.prt.app.bean.SpeakerBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.parser.JsonParser;
import com.prt.app.util.AsyncTaskExecuter;
import com.prt.app.util.Constants;
import com.prt.app.util.FilterList;
import com.prt.app.util.IdentityConstant;
import com.prt.app.util.Utility;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Pratyesh Singh
 * 
 */
public class ListEvent extends Fragment {

	boolean refreshenable = false;

	private String tempText = "";
	private ArrayList<ExhibitorBean> tempexhibitorBeanList;
	private ArrayList<SpeakerBean> tempspeakerBeanList;

	private boolean mInitiate;
	private ScreenBean screenBean;
	public int action;
	public int templateId;
	private ArrayList<ExhibitorBean> exhibitorBeanList;
	private ArrayList<SpeakerBean> speakerBeanList;
	private ArrayList<String> galleryList;
	private ArrayList<String> floorList;
	private ArrayList<String> presentationList;
	private ArrayList<String> image_gallery;
	private ArrayList<ScheduleBean> scheduleList;
	ArrayList<String> scheduleDaysList;
	ArrayList<NotificationBean> notificationBeanList;
	private int selected_index = 0;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onResume() {
		((HomeActivity) getActivity()).getSupportActionBar().show();
		super.onResume();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		try {

			Bundle bundle = getArguments();
			this.action = bundle.getInt("linkedScreen");
			screenBean = ((HomeActivity) getActivity()).eventBean.getEventMap().get(action);
			this.templateId = screenBean.getTemplateId();
			if (templateId == IdentityConstant.EXHIBITORS.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
				exhibitorBeanList = executionQuery.getExhibitorsList();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.SPEAKER.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 2);
				speakerBeanList = executionQuery.getSpeakersList();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.SCHEDULE.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 3);
				scheduleDaysList = executionQuery.getScheduleDays(screenBean.getCalendarBeansList().get(0).getGroupName());
				selected_index = 0;
				if (scheduleDaysList.size() > 0)
					scheduleList = executionQuery.getScheduleList(screenBean.getCalendarBeansList().get(0).getGroupName(), scheduleDaysList.get(selected_index));
				else
					scheduleList = new ArrayList<ScheduleBean>();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.FLOOR_FOLDERS.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 5);
				floorList = executionQuery.getFloorFolders();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.PRESENTATION_FOLDERS.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 8);
				presentationList = executionQuery.getPresentationFolders();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.GALLERY_FOLDERS.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 4);
				galleryList = executionQuery.getGalleryFolders();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE.VALUE) {
				String folder_name = bundle.getString("folder_name");
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 4);
				image_gallery = executionQuery.getImageGalleryList(folder_name);
				executionQuery.closeDatabase();
			} else if (templateId == IdentityConstant.FAVORITES.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
				exhibitorBeanList = executionQuery.getExhibitorsListForFavorites();
				executionQuery.closeDatabase();

				executionQuery = new ExecutionQuery(getActivity(), 2);
				speakerBeanList = executionQuery.getSpeakersListForFavorites();
				executionQuery.closeDatabase();

			} else if (templateId == IdentityConstant.NOTES.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
				exhibitorBeanList = executionQuery.getExhibitorsListForNotes();
				executionQuery.closeDatabase();

				executionQuery = new ExecutionQuery(getActivity(), 2);
				speakerBeanList = executionQuery.getSpeakersListForNotes();
				executionQuery.closeDatabase();
			} else if (templateId == IdentityConstant.NOTIFICATION.VALUE) {
				ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 7);
				notificationBeanList = executionQuery.getNotificationList();
				executionQuery.closeDatabase();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = null;
		if (templateId == IdentityConstant.EXHIBITORS.VALUE || templateId == IdentityConstant.SPEAKER.VALUE) {
			view = inflater.inflate(R.layout.fragment_exhibitor_list, container, false);
		} else {
			view = inflater.inflate(R.layout.fragment_list, container, false);
		}

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		if (!mInitiate) {
			try {
				updatingContent();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			mInitiate = true;
		} else {
			init();
		}

	}

	private void updatingContent() throws JSONException {

		SharedPreferences sf = getActivity().getSharedPreferences("saturn_mobi_app", Context.MODE_PRIVATE);

		if (templateId == IdentityConstant.EXHIBITORS.VALUE) {

			final Hashtable<String, String> header = new Hashtable<String, String>();
			final JSONObject request_body = new JSONObject();
			request_body.put("event_id", Constants.EVENT_ID);
			request_body.put("exhibitor_version", sf.getInt("exhibitor_version", 1));
			final String url = Constants.exhibitor_Url;

			AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

				@Override
				public void notifyRespons(Object[] result) {
					parseAndSetData(result);

					init();
					if (exhibitorBeanList.size() == 0) {
						Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
					}
				}
			};
			if (Utility.checkInternetConnection(getActivity())) {
				if (exhibitorBeanList.size() == 0) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, url, request_body.toString(), "POST", header, true).execute();
				} else {

					init();
					new Thread(new Runnable() {

						@Override
						public void run() {
							parseAndSetData(HTTP_ApiRequest.getApiResult(url, request_body.toString(), "POST", header));
							refreshList();
						}
					}).start();

				}

			} else {
				init();
				if (exhibitorBeanList.size() == 0) {
					Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
				}
				// Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Toast.LENGTH_LONG).show();
			}

		} else if (templateId == IdentityConstant.SPEAKER.VALUE) {
			final Hashtable<String, String> header = new Hashtable<String, String>();
			final JSONObject request_body = new JSONObject();
			request_body.put("event_id", Constants.EVENT_ID);
			request_body.put("speaker_version", sf.getInt("speaker_version", 1));

			final String url = Constants.speaker_Url;
			AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

				@Override
				public void notifyRespons(Object[] result) {

					parseAndSetData(result);

					init();
					if (speakerBeanList.size() == 0) {
						Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
					}
				}
			};
			if (Utility.checkInternetConnection(getActivity())) {
				if (speakerBeanList.size() == 0) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, url, request_body.toString(), "POST", header, true).execute();
				} else {
					init();
					new Thread(new Runnable() {

						@Override
						public void run() {

							parseAndSetData(HTTP_ApiRequest.getApiResult(url, request_body.toString(), "POST", header));

							refreshList();
						}
					}).start();

				}
			} else {
				init();
				if (speakerBeanList.size() == 0) {
					Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
				}
				// Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Toast.LENGTH_LONG).show();
			}
		} else if (templateId == IdentityConstant.SCHEDULE.VALUE) {

			final Hashtable<String, String> header = new Hashtable<String, String>();
			final JSONObject request_body = new JSONObject();
			request_body.put("event_id", Constants.EVENT_ID);
			request_body.put("scheduler_version", sf.getInt("scheduler_version", 1));

			final String url = Constants.scheduler_Url;
			AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

				@Override
				public void notifyRespons(Object[] result) {

					parseAndSetData(result);

					init();
					if (scheduleList.size() == 0) {
						Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
					}
				}
			};
			if (Utility.checkInternetConnection(getActivity())) {
				if (scheduleList.size() == 0) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, url, request_body.toString(), "POST", header, true).execute();
				} else {
					init();
					new Thread(new Runnable() {

						@Override
						public void run() {
							parseAndSetData(HTTP_ApiRequest.getApiResult(url, request_body.toString(), "POST", header));
							// refreshList();
						}
					}).start();

				}
			} else {
				init();
				if (scheduleList.size() == 0) {
					Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
				}
				// Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Toast.LENGTH_LONG).show();
			}
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS.VALUE) {

			final Hashtable<String, String> header = new Hashtable<String, String>();
			final JSONObject request_body = new JSONObject();
			request_body.put("event_id", Constants.EVENT_ID);
			request_body.put("gallery_version", sf.getInt("gallery_version", 1));

			final String url = Constants.gallery_Url;
			AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

				@Override
				public void notifyRespons(Object[] result) {

					parseAndSetData(result);

					init();
					if (galleryList.size() == 0) {
						Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
					}
				}
			};
			if (Utility.checkInternetConnection(getActivity())) {
				if (galleryList.size() == 0) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, url, request_body.toString(), "POST", header, true).execute();
				} else {
					init();
					new Thread(new Runnable() {

						@Override
						public void run() {
							parseAndSetData(HTTP_ApiRequest.getApiResult(url, request_body.toString(), "POST", header));
							refreshList();
						}
					}).start();
				}
			} else {
				init();
				if (galleryList.size() == 0) {
					Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
				}
				// Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Toast.LENGTH_LONG).show();
			}
		} else if (templateId == IdentityConstant.FLOOR_FOLDERS.VALUE) {

			final Hashtable<String, String> header = new Hashtable<String, String>();
			final JSONObject request_body = new JSONObject();
			request_body.put("event_id", Constants.EVENT_ID);
			request_body.put("floor_version", sf.getInt("floor_version", 1));

			final String url = Constants.floors_Url;
			AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

				@Override
				public void notifyRespons(Object[] result) {

					parseAndSetData(result);

					init();
					if (floorList.size() == 0) {
						Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
					}
				}
			};
			if (Utility.checkInternetConnection(getActivity())) {
				if (floorList.size() == 0) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, url, request_body.toString(), "POST", header, true).execute();
				} else {
					init();
					new Thread(new Runnable() {

						@Override
						public void run() {
							parseAndSetData(HTTP_ApiRequest.getApiResult(url, request_body.toString(), "POST", header));
							refreshList();
						}
					}).start();
				}
			} else {
				init();
				if (floorList.size() == 0) {
					Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
				}
				// Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Toast.LENGTH_LONG).show();
			}
		} else if (templateId == IdentityConstant.PRESENTATION_FOLDERS.VALUE) {

			final Hashtable<String, String> header = new Hashtable<String, String>();
			final JSONObject request_body = new JSONObject();
			request_body.put("event_id", Constants.EVENT_ID);
			request_body.put("presentation_version", sf.getInt("presentation_version", 1));

			final String url = Constants.presentations_Url;
			AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

				@Override
				public void notifyRespons(Object[] result) {

					parseAndSetData(result);

					init();
					if (presentationList.size() == 0) {
						Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
					}
				}
			};
			if (Utility.checkInternetConnection(getActivity())) {
				if (presentationList.size() == 0) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, url, request_body.toString(), "POST", header, true).execute();
				} else {
					init();
					new Thread(new Runnable() {

						@Override
						public void run() {
							parseAndSetData(HTTP_ApiRequest.getApiResult(url, request_body.toString(), "POST", header));
							refreshList();
						}
					}).start();
				}
			} else {
				init();
				if (presentationList.size() == 0) {
					Crouton.makeText(getActivity(), "Coming soon", Style.INFO).show();
				}
				// Toast.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Toast.LENGTH_LONG).show();
			}
		} else {
			init();
		}
	}

	private void refreshList() {
		if (refreshenable) {

			getActivity().runOnUiThread(new Runnable() {

				@Override
				public void run() {
					// getListView().addHeaderView(null);
					// getListView().setAdapter(null);
					init();
				}
			});
		}

	}

	private void parseAndSetData(Object[] result) {
		if (result != null && (Integer) result[0] == 200) {

			try {

				JSONObject da = (JSONObject) result[1];
				SharedPreferences sf = getActivity().getSharedPreferences("saturn_mobi_app", Context.MODE_PRIVATE);

				if (templateId == IdentityConstant.EXHIBITORS.VALUE && da.has("exhibitors")) {
					JsonParser.parseAndSaveExhibitors(da.getJSONArray("exhibitors"), getActivity());

					ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 1);
					exhibitorBeanList = executionQuery.getExhibitorsList();
					executionQuery.closeDatabase();

					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("exhibitor_version", version);
					ed.commit();
					refreshenable = true;
				} else if (templateId == IdentityConstant.SPEAKER.VALUE && da.has("speakers")) {

					JsonParser.parseAndSaveSpeaker(da.getJSONArray("speakers"), getActivity());

					ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 2);
					speakerBeanList = executionQuery.getSpeakersList();
					executionQuery.closeDatabase();

					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("speaker_version", version);
					ed.commit();
					refreshenable = true;
				} else if (templateId == IdentityConstant.SCHEDULE.VALUE && da.has("schedule")) {

					JsonParser.parseAndSaveSchedule(da.getJSONArray("schedule"), getActivity());

					ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 3);
					scheduleDaysList = executionQuery.getScheduleDays(screenBean.getCalendarBeansList().get(0).getGroupName());
					selected_index = 0;
					scheduleList = executionQuery.getScheduleList(screenBean.getCalendarBeansList().get(0).getGroupName(), scheduleDaysList.get(selected_index));
					executionQuery.closeDatabase();

					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("scheduler_version", version);
					ed.commit();
					refreshenable = true;
				} else if (templateId == IdentityConstant.GALLERY_FOLDERS.VALUE && da.has("gallery")) {

					JsonParser.parseAndSaveGallery(da.getJSONArray("gallery"), getActivity());

					ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 4);
					galleryList = executionQuery.getGalleryFolders();
					executionQuery.closeDatabase();

					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("gallery_version", version);
					ed.commit();
					refreshenable = true;
				} else if (templateId == IdentityConstant.FLOOR_FOLDERS.VALUE && da.has("floor")) {

					JsonParser.parseAndSaveFloorPlan(da.getJSONArray("floor"), getActivity());

					ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 5);
					floorList = executionQuery.getFloorFolders();
					executionQuery.closeDatabase();

					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("floor_version", version);
					ed.commit();
					refreshenable = true;
				} else if (templateId == IdentityConstant.PRESENTATION_FOLDERS.VALUE && da.has("presentation")) {

					JsonParser.parseAndSavePresentation(da.getJSONArray("presentation"), getActivity());

					ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 8);
					presentationList = executionQuery.getPresentationFolders();
					executionQuery.closeDatabase();

					int version = Integer.parseInt(da.getString("version"));
					Editor ed = sf.edit();
					ed.putInt("presentation_version", version);
					ed.commit();
					refreshenable = true;
				}

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void init() {
		if (getView() == null)
			return;

		// getActivity().setTitle(screenBean.getTitle());
		// ((HomeActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(screenBean.getTitleBackgroundColor())));
		// getActivity().findViewById(R.id.titleLayout).setBackgroundColor(Color.parseColor(screenBean.getTitleBackgroundColor()));
		((TextView) getActivity().findViewById(R.id.titleText)).setText(screenBean.getTitle());

		if (templateId == IdentityConstant.EXHIBITORS.VALUE) {

			final EditText search_text = ((EditText) getView().findViewById(R.id.search_text));
			final ImageView clear_search = ((ImageView) getView().findViewById(R.id.clear_search));
			clear_search.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					search_text.setText("");
				}
			});

			search_text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if (s.length() != 0) {
						clear_search.setVisibility(View.VISIBLE);
					} else {
						clear_search.setVisibility(View.GONE);
					}
					// get here text
					tempText = s + "";
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {

				}

				@Override
				public void afterTextChanged(Editable s) {
					// set here adapter
					tempexhibitorBeanList = new FilterList().getFilteredExhibitor(exhibitorBeanList, tempText);
					setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), tempexhibitorBeanList, templateId));
				}
			});
			if (tempText.trim().equalsIgnoreCase("")) {
				tempexhibitorBeanList = exhibitorBeanList;
				setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), tempexhibitorBeanList, templateId));

			} else {
				search_text.setText(tempText);
			}

		} else if (templateId == IdentityConstant.SPEAKER.VALUE) {

			final EditText search_text = ((EditText) getView().findViewById(R.id.search_text));
			final ImageView clear_search = ((ImageView) getView().findViewById(R.id.clear_search));
			clear_search.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					search_text.setText("");
				}
			});

			search_text.addTextChangedListener(new TextWatcher() {

				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					if (s.length() != 0) {
						clear_search.setVisibility(View.VISIBLE);
					} else {
						clear_search.setVisibility(View.GONE);
					}
					// get here text
					tempText = s + "";
				}

				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					// get here text
				}

				@Override
				public void afterTextChanged(Editable s) {
					// set here adapter
					tempspeakerBeanList = new FilterList().getFilteredSpeaker(speakerBeanList, tempText);
					setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), tempspeakerBeanList, templateId));
				}
			});
			if (tempText.trim().equalsIgnoreCase("")) {
				tempspeakerBeanList = speakerBeanList;
				setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), tempspeakerBeanList, templateId));
			} else {
				search_text.setText(tempText);
			}

		} else if (templateId == IdentityConstant.NOTIFICATION.VALUE) {

			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), notificationBeanList, templateId));
			if (notificationBeanList.size() == 0) {
				Crouton.makeText(getActivity(), "No Message Available.", Style.INFO).show();
			}

		} else if (templateId == IdentityConstant.SCHEDULE.VALUE) {
			if (scheduleList.size() > 0) {

				if (getListView().getAdapter() == null) {
				} else {
					getListView().setAdapter(null);
				}

				LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				final View schedule_header = inflater.inflate(R.layout.schedule_header, null);
				getListView().addHeaderView(schedule_header);
				schedule_header.setOnClickListener(null);

				if (scheduleDaysList.size() == 1) {
					schedule_header.findViewById(R.id.left_image_view).setVisibility(View.GONE);
					schedule_header.findViewById(R.id.right_image_view).setVisibility(View.GONE);

				} else if (selected_index == 0) {
					schedule_header.findViewById(R.id.left_image_view).setVisibility(View.GONE);
					schedule_header.findViewById(R.id.right_image_view).setVisibility(View.VISIBLE);

				} else if (selected_index >= 0 && scheduleDaysList.size() - selected_index == 1) {
					schedule_header.findViewById(R.id.left_image_view).setVisibility(View.VISIBLE);
					schedule_header.findViewById(R.id.right_image_view).setVisibility(View.GONE);

				} else if (selected_index >= 0) {
					schedule_header.findViewById(R.id.left_image_view).setVisibility(View.VISIBLE);
					schedule_header.findViewById(R.id.right_image_view).setVisibility(View.VISIBLE);
				}

				schedule_header.findViewById(R.id.left_image_view).setOnClickListener(new View.OnClickListener() {

					@Override
					public synchronized void onClick(View v) {

						if (selected_index == 0) {
							schedule_header.findViewById(R.id.left_image_view).setVisibility(View.GONE);
							return;
						}
						schedule_header.findViewById(R.id.right_image_view).setVisibility(View.VISIBLE);
						selected_index--;
						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 3);
						scheduleList = executionQuery.getScheduleList(screenBean.getCalendarBeansList().get(0).getGroupName(), scheduleDaysList.get(selected_index));
						executionQuery.closeDatabase();
						try {
							String str_date = scheduleList.get(0).getScheduleDate();
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							java.util.Date dt = sdf.parse(str_date);
							DateFormat df = new SimpleDateFormat("EEEE MMM, dd yyyy");
							((TextView) schedule_header.findViewById(R.id.textView)).setText(df.format(dt));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), scheduleList, templateId));

						if (selected_index == 0) {
							schedule_header.findViewById(R.id.left_image_view).setVisibility(View.GONE);
						}
					}
				});
				schedule_header.findViewById(R.id.right_image_view).setOnClickListener(new View.OnClickListener() {

					@Override
					public synchronized void onClick(View v) {

						if (selected_index + 1 >= scheduleDaysList.size()) {
							schedule_header.findViewById(R.id.right_image_view).setVisibility(View.GONE);
							return;
						}
						schedule_header.findViewById(R.id.left_image_view).setVisibility(View.VISIBLE);

						selected_index++;
						ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 3);
						scheduleList = executionQuery.getScheduleList(screenBean.getCalendarBeansList().get(0).getGroupName(), scheduleDaysList.get(selected_index));
						executionQuery.closeDatabase();

						try {
							String str_date = scheduleList.get(0).getScheduleDate();
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
							java.util.Date dt = sdf.parse(str_date);
							DateFormat df = new SimpleDateFormat("EEEE MMM, dd yyyy");
							((TextView) schedule_header.findViewById(R.id.textView)).setText(df.format(dt));
						} catch (ParseException e) {
							e.printStackTrace();
						}
						setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), scheduleList, templateId));

						if (selected_index + 1 >= scheduleDaysList.size()) {
							schedule_header.findViewById(R.id.right_image_view).setVisibility(View.GONE);
						}
					}
				});

				try {
					String str_date = scheduleList.get(0).getScheduleDate();
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					java.util.Date dt = sdf.parse(str_date);
					DateFormat df = new SimpleDateFormat("EEEE MMM, dd yyyy");
					((TextView) schedule_header.findViewById(R.id.textView)).setText(df.format(dt));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				schedule_header.findViewById(R.id.textView).setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {

					}
				});
			}
			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), scheduleList, templateId));
		} else if (templateId == IdentityConstant.FLOOR_FOLDERS.VALUE) {
			Log.d("FLOOR", floorList.toString());
			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), floorList, templateId));
		} else if (templateId == IdentityConstant.PRESENTATION_FOLDERS.VALUE) {
			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), presentationList, templateId));
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS.VALUE) {
			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), galleryList, templateId));
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE.VALUE) {
			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), image_gallery, templateId));
			getListView().setDividerHeight(15);
		} else if (templateId == IdentityConstant.NOTES.VALUE || templateId == IdentityConstant.FAVORITES.VALUE) {

			if (exhibitorBeanList.size() == 0) {
				if (templateId == IdentityConstant.NOTES.VALUE) {
					Crouton.makeText(getActivity(), "No Exhibitor added to Notes", Style.INFO).show();
				} else if (templateId == IdentityConstant.FAVORITES.VALUE) {
					Crouton.makeText(getActivity(), "No Exhibitor added to Favorites", Style.INFO).show();
				}
			}
			if (getListView().getAdapter() == null) {
			} else {
				getListView().setAdapter(null);
			}

			LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			final View notes_header = inflater.inflate(R.layout.notes_header, null);
			getListView().addHeaderView(notes_header);
			notes_header.setOnClickListener(null);
			notes_header.findViewById(R.id.left_text).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					notes_header.findViewById(R.id.left_text).setBackgroundResource(R.drawable.ractangle_selected);
					notes_header.findViewById(R.id.right_text).setBackgroundResource(R.drawable.ractangle_unselected);
					setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), exhibitorBeanList, IdentityConstant.EXHIBITORS.VALUE));

					if (exhibitorBeanList.size() == 0) {
						if (templateId == IdentityConstant.NOTES.VALUE) {
							Crouton.makeText(getActivity(), "No Exhibitor added to Notes", Style.INFO).show();
						} else if (templateId == IdentityConstant.FAVORITES.VALUE) {
							Crouton.makeText(getActivity(), "No Exhibitor added to Favorites", Style.INFO).show();
						}
					}
				}
			});
			notes_header.findViewById(R.id.right_text).setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					notes_header.findViewById(R.id.left_text).setBackgroundResource(R.drawable.ractangle_unselected);
					notes_header.findViewById(R.id.right_text).setBackgroundResource(R.drawable.ractangle_selected);
					setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), speakerBeanList, IdentityConstant.SPEAKER.VALUE));

					if (speakerBeanList.size() == 0) {
						if (templateId == IdentityConstant.NOTES.VALUE) {
							Crouton.makeText(getActivity(), "No Speaker added to Notes", Style.INFO).show();
						} else if (templateId == IdentityConstant.FAVORITES.VALUE) {
							Crouton.makeText(getActivity(), "No Speaker added to Favorites", Style.INFO).show();
						}
					}
				}
			});

			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), exhibitorBeanList, IdentityConstant.EXHIBITORS.VALUE));

		} else {
			setListAdapter(new LsitviewAdapter<ListRowBean>(getActivity(), screenBean.getListViewBeansList().get(0).getListRowBeansList(), templateId));
		}
	}

	private ListView getListView() {
		return (ListView) getView().findViewById(R.id.listView);
	}

	private void setListAdapter(LsitviewAdapter<?> lsitviewAdapter) {
		getListView().setAdapter(lsitviewAdapter);
		getListView().setOnItemClickListener(onItemClickListener);

	}

	OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			if (templateId == IdentityConstant.EXHIBITORS.VALUE) {
				int newAction = screenBean.getMultiListViewBeansList().get(0).getAction();

				int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();

				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("exhibitorBeanList", tempexhibitorBeanList);
				bundle.putInt("clickedIndex", position);
				Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);

			} else if (templateId == IdentityConstant.SPEAKER.VALUE) {

				int newAction = screenBean.getMultiListViewBeansList().get(0).getAction();

				int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();

				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("speakerBeanList", tempspeakerBeanList);
				bundle.putInt("clickedIndex", position);
				Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);
			} else if (templateId == IdentityConstant.SCHEDULE.VALUE) {

				Bundle bundle = new Bundle();
				// we are doing this (subtracting) because header are added in list view so this is increasing position of listindex
				bundle.putInt("clickedIndex", position - 1);
				bundle.putParcelableArrayList("scheduleList", scheduleList);
				Utility.startAction(ListEvent.this, 21, 2202, true, bundle);
			} else if (templateId == IdentityConstant.GALLERY_FOLDERS.VALUE) {
				ListRowBean listRowBean = screenBean.getListViewBeansList().get(0).getListRowBeansList().get(0);

				int newAction = listRowBean.getAction();
				int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();
				Bundle bundle = new Bundle();
				bundle.putString("folder_name", galleryList.get(position));
				Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);

			} else if (templateId == IdentityConstant.FLOOR_FOLDERS.VALUE) {
				ListRowBean listRowBean = screenBean.getListViewBeansList().get(0).getListRowBeansList().get(0);
				int newAction = listRowBean.getAction();
				int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();
				Bundle bundle = new Bundle();
				bundle.putString("floor_name", floorList.get(position));
				Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);
			} else if (templateId == IdentityConstant.PRESENTATION_FOLDERS.VALUE) {
				ListRowBean listRowBean = screenBean.getListViewBeansList().get(0).getListRowBeansList().get(0);
				int newAction = listRowBean.getAction();
				int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();
				Bundle bundle = new Bundle();
				bundle.putString("url", presentationList.get(position));
				Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);
			} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE.VALUE) {
				System.out.println("Hiii...........19 ");
			} else if (templateId == IdentityConstant.NOTIFICATION.VALUE) {
				// Toast.makeText(getActivity(), "coming soon...", Toast.LENGTH_SHORT).show();
			} else if (templateId == IdentityConstant.NOTES.VALUE || templateId == IdentityConstant.FAVORITES.VALUE) {
				LsitviewAdapter lsitviewAdapter = ((LsitviewAdapter) ((HeaderViewListAdapter) getListView().getAdapter()).getWrappedAdapter());
				// LsitviewAdapter lsitviewAdapter = (LsitviewAdapter) (((HeaderViewListAdapter)getListView().getAdapter()).getWrappedAdapter);// getListAdapter();
				Object item = lsitviewAdapter.getItem(position - 1);
				if (item instanceof ExhibitorBean) {

					int newAction = screenBean.getMultiListViewBeansList().get(0).getAction();
					int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();

					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("exhibitorBeanList", exhibitorBeanList);
					bundle.putInt("clickedIndex", position - 1);
					Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);
				} else if (item instanceof SpeakerBean) {

					int newAction = screenBean.getMultiListViewBeansList().get(1).getAction();
					int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();

					Bundle bundle = new Bundle();
					bundle.putParcelableArrayList("speakerBeanList", speakerBeanList);
					bundle.putInt("clickedIndex", position - 1);
					Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);
				}

			} else {
				ListRowBean listRowBean = screenBean.getListViewBeansList().get(0).getListRowBeansList().get(position);

				int newAction = listRowBean.getAction();
				int newTemplateId = ((HomeActivity) getActivity()).eventBean.getEventMap().get(newAction).getTemplateId();
				Bundle bundle = new Bundle();

				Utility.startAction(ListEvent.this, newTemplateId, newAction, true, bundle);

			}

		}
	};

	@Override
	public void onStart() {

		super.onStart();
		FlurryAgent.setCaptureUncaughtExceptions(true);
		FlurryAgent.onStartSession(getActivity(), Constants.flurryKey);
		HashMap<String, String> data = new HashMap<String, String>();
		data.put(Constants.SCREEN, Constants.FLURRY_LIST_VIEW);
		data.put(Constants.SUB_EVENT, screenBean.getTitle());
		FlurryAgent.logEvent(Constants.FLURRY_LIST_VIEW, data);
	}

	@Override
	public void onStop() {

		super.onStop();
		FlurryAgent.onEndSession(getActivity());
	}

}
