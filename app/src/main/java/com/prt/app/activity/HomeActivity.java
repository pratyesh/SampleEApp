package com.prt.app.activity;

import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.prt.app.bean.BannerBean;
import com.prt.app.bean.EventBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.fragment.WebFragment;
import com.prt.app.fragment_basecontroller.BaseControllerFragment;
import com.prt.app.util.Constants;
import com.prt.app.util.Utility;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class HomeActivity extends AppCompatActivity implements OnClickListener {
	public EventBean eventBean;
	public int action;
	public int templateId;
	int i = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		try {
			Bundle bundle = getIntent().getExtras();
			eventBean = (EventBean) bundle.get("eventmap");
			action = bundle.getInt("action");

			eventBean.getEventMap().get(action).getGridBeansList().get(0).getRowBeans().get(0).getGridColBeans().get(0).getAction();
			templateId = eventBean.getEventMap().get(action).getTemplateId();

			Utility.startAction(this, templateId, action, new Bundle());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// enable ActionBar app icon to behave as action to toggle nav drawer
		// getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// getSupportActionBar().setHomeButtonEnabled(true);
		// getSupportActionBar().setDisplayShowHomeEnabled(true);
		// getSupportActionBar().setDisplayShowTitleEnabled(true);
		// getSupportActionBar().setLogo(R.drawable.icon_twitter);
		ActionBar actionBar = getSupportActionBar(); // actionBar.setTitle("OMG");

		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(false);

		actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#079ADF")));
		ActionBar.LayoutParams lp = new ActionBar.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, Gravity.LEFT | Gravity.CENTER_VERTICAL);
		final View customNav = LayoutInflater.from(this).inflate(R.layout.title, null);
		actionBar.setCustomView(customNav, lp);
		actionBar.setDisplayShowCustomEnabled(true);
		customNav.findViewById(R.id.show_on_map).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!Utility.checkInternetConnection(HomeActivity.this)) {
					Crouton.makeText(HomeActivity.this, getResources().getString(R.string.no_internet_connection_found), Style.INFO).show();
					return;
				}
				BaseControllerFragment baseControllerFragment = (BaseControllerFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment_container));
				int newTemplateId = 28, newAction = 0;
				Bundle bundle = new Bundle();
				Utility.startAction(baseControllerFragment.getChildFragmentManager().getFragments().get(0), newTemplateId, newAction, true, bundle);
			}
		});
		// actionBar.hide();
	}

	final Handler handler = new Handler();
	private Runnable runnable;

	public void refreshBanner() {
		try {
			ExecutionQuery executionQuery = new ExecutionQuery(this, 6);
			final ArrayList<BannerBean> bannerList = executionQuery.getBanners();
			executionQuery.closeDatabase();

			/**** -------------------- **/
			final ImageLoader imageLoader = ImageLoader.getInstance();
			final DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.add).showImageForEmptyUri(R.drawable.add).showImageOnFail(R.drawable.add).cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).build();
			imageLoader.init(ImageLoaderConfiguration.createDefault(this));
			/**** -------------------- **/

			final ImageView banner_view = (ImageView) findViewById(R.id.banner_view);
			banner_view.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (bannerList == null || bannerList.size() == 0)
						return;
					BaseControllerFragment baseControllerFragment = (BaseControllerFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment_container));
					Bundle url_bundle = new Bundle();
					WebFragment webFragment = new WebFragment();
					// url_bundle.putString("url", "http://www.saturnmobi.com");
					url_bundle.putString("url", bannerList.get(i).getLink_url());
					webFragment.setArguments(url_bundle);
					((BaseControllerFragment) baseControllerFragment).replaceFragment(webFragment, true);
				}
			});

			imageLoader.displayImage(bannerList.get(0).getBanner_url(), banner_view, options);

			handler.removeCallbacks(runnable);
			runnable = new Runnable() {

				public void run() {
					if (bannerList == null || bannerList.size() == 0)
						return;
					i++;
					if (i >= bannerList.size())
						i = 0;
					imageLoader.displayImage(bannerList.get(i).getBanner_url(), banner_view, options);
					handler.postDelayed(this, Integer.parseInt(bannerList.get(i).getTime()));
				}
			};
			handler.postDelayed(runnable, Integer.parseInt(bannerList.get(0).getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		// if (id == R.id.action_settings) {
		// return true;
		// }
		if (item.getItemId() == android.R.id.home) {
			int newAction = eventBean.getEventMap().get(action).getHeaderSocialNetworkingList().get(2).getAction();
			int newTempleteId = eventBean.getEventMap().get(newAction).getTemplateId();
			BaseControllerFragment baseControllerFragment = (BaseControllerFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment_container));

			Bundle bundle = new Bundle();
			Utility.startAction(baseControllerFragment.getChildFragmentManager().getFragments().get(0), newTempleteId, newAction, true, bundle);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {

		BaseControllerFragment baseControllerFragment = (BaseControllerFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment_container));
		int count = baseControllerFragment.getChildFragmentManager().getBackStackEntryCount();
		if (count > 0) {
			baseControllerFragment.popFragment();
		} else {
			super.onBackPressed();
		}

	}

	@Override
	protected void onStart() {

		super.onStart();
		FlurryAgent.setCaptureUncaughtExceptions(true);
		FlurryAgent.onStartSession(this, Constants.flurryKey);

		FlurryAgent.logEvent(Constants.FLURRY_HOME);
	}

	@Override
	protected void onStop() {

		super.onStop();
		FlurryAgent.onEndSession(this);
	}

	@Override
	public void onClick(View v) {
		if (!Utility.checkInternetConnection(HomeActivity.this)) {
			Crouton.makeText(HomeActivity.this, getResources().getString(R.string.no_internet_connection_found), Style.INFO).show();
			return;
		}
		int i = Integer.parseInt((String) v.getTag());
		int newAction = 0;
		int newTempleteId = 0;

		switch (i) {
		case 1:// facebook
			newAction = eventBean.getEventMap().get(action).getHeaderSocialNetworkingList().get(0).getAction();
			newTempleteId = eventBean.getEventMap().get(newAction).getTemplateId();
			break;
		case 2: // twitter
			newAction = eventBean.getEventMap().get(action).getHeaderSocialNetworkingList().get(2).getAction();
			newTempleteId = eventBean.getEventMap().get(newAction).getTemplateId();
			break;
		case 3:// linkedin
			newAction = eventBean.getEventMap().get(action).getHeaderSocialNetworkingList().get(1).getAction();
			newTempleteId = eventBean.getEventMap().get(newAction).getTemplateId();
			break;

		default:
			break;
		}

		BaseControllerFragment baseControllerFragment = (BaseControllerFragment) (getSupportFragmentManager().findFragmentById(R.id.fragment_container));

		Bundle bundle = new Bundle();
		Utility.startAction(baseControllerFragment.getChildFragmentManager().getFragments().get(0), newTempleteId, newAction, true, bundle);

	}
}
