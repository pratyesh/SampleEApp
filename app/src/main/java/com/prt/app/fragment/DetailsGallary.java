package com.prt.app.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ledexpo.android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.prt.app.activity.HomeActivity;
import com.prt.app.adapter.DetailsHelperAdapter;
import com.prt.app.bean.ScreenBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.util.IdentityConstant;

/**
 * @author Pratyesh Singh
 */
public class DetailsGallary extends Fragment {
	Bundle bundle;
	int templateId;

	ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options = null;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
		bundle = getArguments();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.imagethumb).showImageForEmptyUri(R.drawable.imagethumb).showImageOnFail(R.drawable.imagethumb).cacheOnDisc(true)
				.bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).build();
		imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.fragment_details, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {

		ScreenBean screenBean = ((HomeActivity) getActivity()).eventBean.getEventMap().get(bundle.getInt("linkedScreen"));
		templateId = screenBean.getTemplateId();
		FragmentPagerAdapter adapter = new DetailsHelperAdapter(getChildFragmentManager(), bundle, templateId, getActivity());

		final ViewPager pager = (ViewPager) getView().findViewById(R.id.pager);
		pager.setAdapter(adapter);

		if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			PagerTabStrip pager_title_strip = (PagerTabStrip) getView().findViewById(R.id.pager_title_strip);
			pager_title_strip.setVisibility(View.GONE);
			/****** --------------------------------------------------- *******/
			final ActionBar actionBar = ((HomeActivity) getActivity()).getSupportActionBar();

			// Specify that tabs should be displayed in the action bar.
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

			final ActionBar.TabListener tabListener = new ActionBar.TabListener() {

				@Override
				public void onTabUnselected(Tab tab, FragmentTransaction ft) {

				}

				@Override
				public void onTabSelected(Tab tab, FragmentTransaction ft) {
					int selectedTab = tab.getPosition();
					pager.setCurrentItem(selectedTab);

				}

				@Override
				public void onTabReselected(Tab tab, FragmentTransaction ft) {

				}
			};
			pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

				@Override
				public void onPageSelected(int position) {
					actionBar.setSelectedNavigationItem(position);
				}

				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
					// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
				}

				@Override
				public void onPageScrollStateChanged(int state) {
					// if (state == ViewPager.SCROLL_STATE_IDLE) {
					// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
					// }

				}
			});

			String folder_name = bundle.getString("folder_name");
			ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 4);
			ArrayList<String> image_gallery = executionQuery.getImageGalleryList(folder_name);
			executionQuery.closeDatabase();

			// For each of the sections in the app, add a tab to the action bar.
			for (int i = 0; i < adapter.getCount(); i++) {
				actionBar.addTab(actionBar.newTab().setTabListener(tabListener).setCustomView(getViewForPageIndicator(image_gallery.get(i))));
			}
			/****** --------------------------------------------------- *******/

		}

		int clickedIndex = bundle.getInt("clickedIndex");
		pager.setCurrentItem(clickedIndex);

		((TextView) getActivity().findViewById(R.id.titleText)).setText(screenBean.getTitle());
	}

	private View getViewForPageIndicator(String thumb_utl) {
		LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View view = inflater.inflate(R.layout.item_pager_indicator_image, null);
		ImageView image = (ImageView) view.findViewById(R.id.image);
		final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);

		imageLoader.displayImage(thumb_utl, image, options, new SimpleImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				spinner.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				spinner.setVisibility(View.GONE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				spinner.setVisibility(View.GONE);
			}
		});
		return view;
	}

	@Override
	public void onDestroy() {
		if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			ActionBar actionBar = ((HomeActivity) getActivity()).getSupportActionBar();
			actionBar.removeAllTabs();
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
			// actionBar.hide();

		}
		super.onDestroy();
	}

}
