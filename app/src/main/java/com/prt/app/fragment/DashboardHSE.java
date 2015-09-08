package com.prt.app.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.adapter.CirclePageIndicator;
import com.prt.app.adapter.ImagePagerAdapter;
import com.prt.app.adapter.PageIndicator;
import com.prt.app.bean.EventBean;
import com.prt.app.bean.GridBean;
import com.prt.app.bean.GridColBean;
import com.prt.app.bean.GridRowBean;
import com.prt.app.bean.ScreenBean;
import com.prt.app.util.Utility;

/**
 * @author Pratyesh Singh
 */
public class DashboardHSE extends Fragment {

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

			ArrayList<GridBean> gridBeansList = screenBean.getGridBeansList();

			GridBean gridBean = null;
			ArrayList<GridRowBean> rowBeansList;
			ArrayList<GridColBean> gridColBeansList;
			GridColBean gridColBean;
			GridRowBean gridRowBean;
			int noOfGridRow;
			int noOfGridCol;
			int index = 0;

			if (!gridBeansList.isEmpty()) {
				gridBean = gridBeansList.get(0);
				noOfGridCol = gridBean.getNoOfGridCol();
				noOfGridRow = gridBean.getNoOfGridRow();

				gridactions = new int[noOfGridCol * noOfGridRow];

				rowBeansList = gridBean.getRowBeans();

				int len = rowBeansList.size();
				if (len > 0) {
					for (int i = 0; i < len; i++) {
						gridRowBean = rowBeansList.get(i);
						gridColBeansList = gridRowBean.getGridColBeans();
						for (int j = 0; j < gridColBeansList.size(); j++) {
							gridColBean = gridColBeansList.get(j);
							gridactions[index] = gridColBean.getAction();
							index++;
						}
					}
				}
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
		View view = inflater.inflate(R.layout.fragment_dashboard_hse_new, container, false);
		return view;
	}

	@Override
	public void onResume() {
		// ((HomeActivity) getActivity()).getSupportActionBar().hide();
		super.onResume();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		((HomeActivity) getActivity()).refreshBanner();
		
		((HomeActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor(screenBean.getTitleBackgroundColor())));
		getActivity().findViewById(R.id.titleLayout).setBackgroundColor(Color.parseColor(screenBean.getTitleBackgroundColor()));
		((TextView) getActivity().findViewById(R.id.titleText)).setText(screenBean.getTitle());
		
		TextView marquee_text = ((TextView) getView().findViewById(R.id.marquee_text));
		marquee_text.setSelected(true);
		try {
			SharedPreferences sf = getActivity().getSharedPreferences("saturn_mobi_app", Context.MODE_PRIVATE);
			marquee_text.setText(sf.getString("ticker", marquee_text.getText().toString()).trim() + "                                                                                        ");
		} catch (Exception e) {
			e.printStackTrace();
		}

		LinearLayout first_row = (LinearLayout) getView().findViewById(R.id.first_row);

		first_row.getChildAt(0).setTag(0);
		first_row.getChildAt(0).setOnClickListener(items_clickListener);

		first_row.getChildAt(1).setTag(1);
		first_row.getChildAt(1).setOnClickListener(items_clickListener);

		first_row.getChildAt(2).setTag(2);
		first_row.getChildAt(2).setOnClickListener(items_clickListener);

		LinearLayout second_row = (LinearLayout) getView().findViewById(R.id.second_row);

		second_row.getChildAt(0).setTag(3);
		second_row.getChildAt(0).setOnClickListener(items_clickListener);

		second_row.getChildAt(1).setTag(4);
		second_row.getChildAt(1).setOnClickListener(items_clickListener);

		second_row.getChildAt(2).setTag(5);
		second_row.getChildAt(2).setOnClickListener(items_clickListener);

		LinearLayout third_row = (LinearLayout) getView().findViewById(R.id.third_row);

		third_row.getChildAt(0).setTag(6);
		third_row.getChildAt(0).setOnClickListener(items_clickListener);

		third_row.getChildAt(1).setTag(7);
		third_row.getChildAt(1).setOnClickListener(items_clickListener);

		third_row.getChildAt(2).setTag(8);
		third_row.getChildAt(2).setOnClickListener(items_clickListener);

	}

	OnClickListener items_clickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			int templateId = eventBean.getEventMap().get(gridactions[(int) v.getTag()]).getTemplateId();

			Bundle bundle = new Bundle();
			Utility.startAction(DashboardHSE.this, templateId, gridactions[(int) v.getTag()], true, bundle);

		}
	};

	OnClickListener imagePager_clickListener = new View.OnClickListener() {

		@Override
		public void onClick(View v) {

			int templateId = eventBean.getEventMap().get(gridactions[1]).getTemplateId();

			Bundle bundle = new Bundle();
			Utility.startAction(DashboardHSE.this, templateId, gridactions[1], true, bundle);

		}
	};

}
