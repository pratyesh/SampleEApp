package com.prt.app.fragment;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.adapter.GridViewAdapter;
import com.prt.app.bean.EventBean;
import com.prt.app.bean.GridBean;
import com.prt.app.bean.GridColBean;
import com.prt.app.bean.GridRowBean;
import com.prt.app.bean.HeaderSocialNetworkingBean;
import com.prt.app.bean.ScreenBean;
import com.prt.app.util.Constants;
import com.prt.app.util.Utility;

/**
 * @author Pratyesh Singh
 */
public class Dashboard extends Fragment {
	private EventBean eventBean;
	private ScreenBean screenBean;
	public int action;
	public int templateId;
	int[] gridactions;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		try {
			this.eventBean = ((HomeActivity) getActivity()).eventBean;
			this.action = ((HomeActivity) getActivity()).action;
			this.templateId = ((HomeActivity) getActivity()).templateId;

			screenBean = ((HomeActivity) getActivity()).eventBean.getEventMap().get(action);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
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
		getActivity().setTitle(screenBean.getTitle());

		Button headerFacebook = (Button) getView().findViewById(R.id.facebookButton);

		ArrayList<HeaderSocialNetworkingBean> headerFacebookBeansList = screenBean.getHeaderSocialNetworkingList();

		HeaderSocialNetworkingBean headerFacebookBean = new HeaderSocialNetworkingBean();
		if (!headerFacebookBeansList.isEmpty()) {
			headerFacebookBean = headerFacebookBeansList.get(0);
			boolean facebookIsAvailable = headerFacebookBean.getSocialNetworkingIsAvailable();

			if (facebookIsAvailable) {
				headerFacebook.setVisibility(View.VISIBLE);
				InputStream stream;
				try {
					stream = getActivity().getResources().getAssets().open((Constants.densityFolder + headerFacebookBean.getImage()));
					Drawable bowlTeamDrawable = Drawable.createFromStream(stream, null);
					headerFacebook.setBackground(bowlTeamDrawable);

				} catch (IOException e) {

					e.printStackTrace();
				}
			} else {
				headerFacebook.setVisibility(View.GONE);
			}

			String fbUrl = headerFacebookBean.getSocialNetworkingUrl();
		}

		headerFacebook.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				Utility.startAction(Dashboard.this, 28, 0, true, bundle);
			}
		});

		GridView gridView = (GridView) getView().findViewById(R.id.gridview);
		RelativeLayout gridViewLinearLayout = (RelativeLayout) getView().findViewById(R.id.gridViewLinearLayout);
		TextView marqueeTextView = (TextView) getView().findViewById(R.id.marqueeWebView);

		marqueeTextView.setVisibility(View.VISIBLE);
		if (!Constants.tickerText.equalsIgnoreCase("")) {
			marqueeTextView.setText(Constants.tickerText);
		} else {
			marqueeTextView.setText("Powered by Saturnmobi Software.");
		}

		int noOfGridRow;
		int noOfGridCol;
		int verticalSpacing;
		int horizontalSpacing;
		int bottom = 10;
		int left = 0;
		int top = 0;
		int index = 0;

		ArrayList<GridRowBean> rowBeansList;
		ArrayList<GridColBean> gridColBeansList;
		GridColBean gridColBean;
		GridRowBean gridRowBean;

		ArrayList<GridBean> gridBeansList = screenBean.getGridBeansList();
		GridBean gridBean = null;
		ArrayList<String> text = new ArrayList<String>();
		ArrayList<String> image = new ArrayList<String>();
		ArrayList<String> textColor = new ArrayList<String>();
		if (!gridBeansList.isEmpty()) {
			gridBean = gridBeansList.get(0);
			noOfGridCol = gridBean.getNoOfGridCol();
			noOfGridRow = gridBean.getNoOfGridRow();

			gridactions = new int[noOfGridCol * noOfGridRow];

			DisplayMetrics displaymetrics = new DisplayMetrics();
			getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
			int width = displaymetrics.widthPixels;
			int height = displaymetrics.heightPixels;

			if (height <= 320) {
				verticalSpacing = 18;
				horizontalSpacing = 2;
				bottom = -3;
				left = 10;
				top = 8;
				marqueeTextView.setTextSize(14);
			} else if ((height >= 320) && (height <= 480)) {
				verticalSpacing = 21;
				bottom = 0;
				marqueeTextView.setTextSize(14);
			} else if ((height > 480) && (height <= 800)) {
				horizontalSpacing = 12;
				verticalSpacing = 40;
				bottom = 27;
				marqueeTextView.setTextSize(16);
			} else if ((height > 800) && (height <= 854)) {
				verticalSpacing = 50;
				bottom = 100;
			}

			else if ((height > 854) && (height < 1280)) {
				verticalSpacing = 70;
				bottom = 80;
			} else if (height == 1280) {
				verticalSpacing = 125;
				bottom = 70;
				marqueeTextView.setTextSize(20);
			}

			gridView.setHorizontalSpacing(gridBean.getHorizontalSpacing());
			gridView.setVerticalSpacing(gridBean.getVerticalSpacing());
			gridView.setNumColumns(noOfGridCol);
			if (!gridBean.getGridBackground().equalsIgnoreCase("")) {
				// gridView.setBackgroundDrawable(CommonFunctions.getDrawableFromAssets(this, gridBean.getGridBackground()));
			}
			String gravity = gridBean.getGravity();

			if (gravity.equals("center")) {
				gridViewLinearLayout.setGravity(Gravity.CENTER);
			} else if (gravity.equalsIgnoreCase("top")) {
				gridViewLinearLayout.setGravity(Gravity.TOP);
			} else if (gravity.equalsIgnoreCase("bottom")) {
				gridViewLinearLayout.setGravity(Gravity.BOTTOM);
			} else if (gravity.equalsIgnoreCase("right")) {
				gridViewLinearLayout.setGravity(Gravity.RIGHT);
			} else if (gravity.equalsIgnoreCase("left")) {
				gridViewLinearLayout.setGravity(Gravity.LEFT);
			} else {
				gridViewLinearLayout.setGravity(Gravity.NO_GRAVITY);
			}

			rowBeansList = gridBean.getRowBeans();

			int len = rowBeansList.size();
			if (len > 0) {
				for (int i = 0; i < len; i++) {
					gridRowBean = rowBeansList.get(i);
					gridColBeansList = gridRowBean.getGridColBeans();
					for (int j = 0; j < gridColBeansList.size(); j++) {
						gridColBean = gridColBeansList.get(j);

						textColor.add(gridColBean.getTextColor());
						text.add(gridColBean.getText());
						image.add(gridColBean.getImage());
						gridactions[index] = gridColBean.getAction();

						index++;
					}

				}
			}
		}

		if (!screenBean.getBackgroundColor().equalsIgnoreCase("")) {
			gridViewLinearLayout.setBackgroundColor(Color.parseColor(screenBean.getBackgroundColor()));
		} else if (!screenBean.getBackgroundImg().equalsIgnoreCase("")) {
			InputStream stream;
			try {
				stream = getActivity().getResources().getAssets().open((Constants.densityFolder + screenBean.getBackgroundImg()));
				Drawable bowlTeamDrawable = Drawable.createFromStream(stream, null);
				gridViewLinearLayout.setBackground(bowlTeamDrawable);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		gridView.setPadding(left, top, 0, bottom);

		gridView.setAdapter(new GridViewAdapter(getActivity(), text, image, textColor));
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				int templateId = eventBean.getEventMap().get(gridactions[position]).getTemplateId();

				Bundle bundle = new Bundle();
				Utility.startAction(Dashboard.this, templateId, gridactions[position], true, bundle);
			}
		});
	}

}
