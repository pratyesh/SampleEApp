package com.prt.app.fragment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ledexpo.android.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.prt.app.activity.HomeActivity;
import com.prt.app.bean.ScreenBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.util.IdentityConstant;
import com.prt.app.util.Utility;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Pratyesh Singh
 */
public class ImageZoom extends Fragment {

	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options = null;

	private ScreenBean screenBean;
	public int action;
	public int templateId;

	String floor_name = "";

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		try {
			Bundle bundle = getArguments();

			options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.imagethumb).showImageForEmptyUri(R.drawable.imagethumb).showImageOnFail(R.drawable.imagethumb).cacheOnDisc(true)
					.bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).build();
			imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));

			floor_name = bundle.getString("floor_name");
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
		View view = inflater.inflate(R.layout.fragment_image_zoom, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {

		if (!Utility.checkInternetConnection(getActivity())) {
			Crouton.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Style.INFO).show();
		}

		ImageView image = (ImageView) getView().findViewById(R.id.image);
		// if (imageName != null && !imageName.trim().equals("")) {
		// try {
		// InputStream ips = new FileInputStream(imageName);
		// image.setImageBitmap(BitmapFactory.decodeStream(ips));
		//
		// getView().findViewById(R.id.extra_action).setVisibility(View.VISIBLE);
		//
		// getView().findViewById(R.id.cancle).setOnClickListener(onClickListener);
		// getView().findViewById(R.id.save).setOnClickListener(onClickListener);
		// getView().findViewById(R.id.share).setOnClickListener(onClickListener);
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		//
		// try {
		// InputStream ips = getResources().getAssets().open(imageName);
		// image.setImageBitmap(BitmapFactory.decodeStream(ips));
		// } catch (IOException e1) {
		// e1.printStackTrace();
		// }
		// }
		// } else
		if (templateId == IdentityConstant.FLOORPLAN.VALUE) {
			// imageName = screenBean.getImageBean().get(0).getImage();
			// try {
			// InputStream ips = getResources().getAssets().open(imageName);
			// image.setImageBitmap(BitmapFactory.decodeStream(ips));
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
			ExecutionQuery executionQuery = new ExecutionQuery(getActivity(), 5);
			String image_url = executionQuery.getFloorPlanImageUrl(floor_name);
			executionQuery.closeDatabase();

			// imageLoader.displayImage(image_url, image, options);
			final ProgressBar spinner = (ProgressBar) getView().findViewById(R.id.loading);
			imageLoader.displayImage(image_url, image, options, new SimpleImageLoadingListener() {
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
		}
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "Coming Soon...", Toast.LENGTH_LONG).show();
		}
	};

}
