package com.prt.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ledexpo.android.R;
import com.prt.app.circularproperties.CircleLayout;

/**
 * @author Pratyesh Singh
 */
public class DashboardCircular extends Fragment {

	TextView selectedTextView;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.fragment_dashboard_circular, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		CircleLayout circleMenu = (CircleLayout) getView().findViewById(R.id.main_circle_layout);
		
		getView().findViewById(R.id.main_center_image).setOnClickListener(onClickListener);
		getView().findViewById(R.id.main_facebook_image).setOnClickListener(onClickListener);
		getView().findViewById(R.id.main_myspace_image).setOnClickListener(onClickListener);
		getView().findViewById(R.id.main_google_image).setOnClickListener(onClickListener);
		getView().findViewById(R.id.main_linkedin_image).setOnClickListener(onClickListener);
		getView().findViewById(R.id.main_twitter_image).setOnClickListener(onClickListener);
		getView().findViewById(R.id.main_wordpress_image).setOnClickListener(onClickListener);

	}

	OnClickListener onClickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.main_center_image:
				Toast.makeText(getActivity(), "Center", Toast.LENGTH_SHORT).show();
			case R.id.main_facebook_image:
				Toast.makeText(getActivity(), "Facebook", Toast.LENGTH_SHORT).show();
				
				break;
			case R.id.main_myspace_image:
				Toast.makeText(getActivity(), "Myspace", Toast.LENGTH_SHORT).show();
				break;
			case R.id.main_google_image:
				Toast.makeText(getActivity(), "Google", Toast.LENGTH_SHORT).show();
				break;
			case R.id.main_linkedin_image:
				Toast.makeText(getActivity(), "LinkedIn", Toast.LENGTH_SHORT).show();
				break;
			case R.id.main_twitter_image:
				Toast.makeText(getActivity(), "Twitter", Toast.LENGTH_SHORT).show();
				break;
			case R.id.main_wordpress_image:
				Toast.makeText(getActivity(), "Wordpress", Toast.LENGTH_SHORT).show();
				break;

			default:
				break;
			}

		}
	};
}
