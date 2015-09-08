package com.prt.app.fragment_container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ledexpo.android.R;
import com.prt.app.fragment.Dashboard;
import com.prt.app.fragment.DashboardHSE;
import com.prt.app.fragment_basecontroller.BaseControllerFragment;

/**
 * @author Pratyesh Singh
 */

public class HomeContainerFragment extends BaseControllerFragment {

	private boolean mIsViewInited;

	// public static boolean enable_clear = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_container, null);
	}

	@Override
	public void onResume() {
		super.onResume();
		// if (enable_clear) {
		// int count = getChildFragmentManager().getBackStackEntryCount();
		// while (count > 0) {
		// popFragment();
		// count--;
		// }
		// enable_clear = false;
		// }
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (!mIsViewInited) {
			mIsViewInited = true;
			initView();
		}
	}

	private void initView() {
		// Dashboard dashboard = new Dashboard();
		// DashboardCircular dashboard = new DashboardCircular();
		DashboardHSE dashboard = new DashboardHSE();
		dashboard.setArguments(getArguments());
		replaceFragment(dashboard, false);
	}

}
