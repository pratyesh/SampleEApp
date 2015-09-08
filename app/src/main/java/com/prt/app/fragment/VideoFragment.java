package com.prt.app.fragment;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.bean.ScreenBean;
import com.prt.app.util.Constants;

/**
 * @author Pratyesh Singh
 */
public class VideoFragment extends Fragment {

	private ScreenBean screenBean;
	public int action;
	public int templateId;

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.fragment_video, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		try {

			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		// getActivity().setTitle(screenBean.getTitle());
		((TextView) getActivity().findViewById(R.id.titleText)).setText(screenBean.getTitle());
		((HomeActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(screenBean.getTitleBackgroundColor())));
		getActivity().findViewById(R.id.titleLayout).setBackgroundColor(Color.parseColor(screenBean.getTitleBackgroundColor()));

		VideoView vidView = (VideoView) getView().findViewById(R.id.myVideo);
		// String vidAddress = screenBean.getWebBeansList().get(0).getUrl();
		// String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		// Uri vidUri = Uri.parse(vidAddress);
		// // (MediaController) getView().findViewById(R.id.mediaController);
		// MediaController vidControl = new MediaController(getActivity());
		// vidControl.setAnchorView(vidView);
		// vidView.setMediaController(vidControl);
		//
		// vidView.setVideoURI(vidUri);
		// vidView.start();

		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=gzd6f7noW4M&feature=youtu.be"));
		startActivity(intent);

	}

	@Override
	public void onStart() {

		super.onStart();
		FlurryAgent.setCaptureUncaughtExceptions(true);
		FlurryAgent.onStartSession(getActivity(), Constants.flurryKey);
		HashMap<String, String> data = new HashMap<String, String>();
		data.put(Constants.SCREEN, Constants.FLURRY_VIDEO_VIEW);
		data.put(Constants.SUB_EVENT, ((TextView) getActivity().findViewById(R.id.titleText)).getText().toString());
		FlurryAgent.logEvent(Constants.FLURRY_VIDEO_VIEW, data);
	}

	@Override
	public void onStop() {

		super.onStop();
		FlurryAgent.onEndSession(getActivity());
	}
}
