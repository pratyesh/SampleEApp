package com.prt.app.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.bean.ScreenBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.util.IdentityConstant;
import com.prt.app.util.ImageUtil;
import com.prt.app.util.Utility;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Pratyesh Singh
 */
public class ImageZoom extends Fragment {

	private ScreenBean screenBean;
	public int action;
	public int templateId;

	String floor_name = "";


	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		try {
			Bundle bundle = getArguments();

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
			ImageUtil.getInstance().loadImage(image_url, image);
		}
	}

	OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Toast.makeText(getActivity(), "Coming Soon...", Toast.LENGTH_LONG).show();
		}
	};

}
