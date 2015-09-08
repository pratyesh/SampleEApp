package com.prt.app.adapter;

import java.io.InputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.prt.app.util.Constants;
import com.ledexpo.android.R;

public class GridViewAdapter extends BaseAdapter {

	private Activity activity = null;
	private LayoutInflater inflater = null;
	private ArrayList<String> gridTextList = null;
	private ArrayList<String> gridImageURLList = null;
	ImageLoader imageLoader = ImageLoader.getInstance();
	DisplayImageOptions options = null;
	private ArrayList<String> gridTextColor = null;

	public GridViewAdapter(Activity activity, ArrayList<String> gridTextList, ArrayList<String> gridImageURLList, ArrayList<String> gridTextColor) {
		this.activity = activity;
		this.gridImageURLList = gridImageURLList;
		this.gridTextList = gridTextList;
		this.gridTextColor = gridTextColor;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.loading_icon).showImageForEmptyUri(com.prt.app.R.drawable.ic_error_transparent)
		// .showImageOnFail(R.drawable.ic_error_transparent).cacheOnDisc(true).bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).build();
		// imageLoader.init(ImageLoaderConfiguration.createDefault(activity.getBaseContext()));
	}

	public static class ViewHolder {
		public TextView textView;
		public ImageView imageView;
	}

	public int getCount() {

		return gridTextList.size();
	}

	public Object getItem(int position) {

		return position;
	}

	public long getItemId(int position) {

		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		ViewHolder holder;
		if (convertView == null) {
			vi = inflater.inflate(R.layout.grid_adapter, null);
			holder = new ViewHolder();
			holder.textView = (TextView) vi.findViewById(R.id.gridTextView);
			holder.imageView = (ImageView) vi.findViewById(R.id.gridImageView);
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		int t = Color.parseColor(gridTextColor.get(position));
		holder.textView.setText(gridTextList.get(position).toString());
		holder.textView.setTextColor(t);
		holder.imageView.setTag(gridImageURLList.get(position).toString());
		Drawable bowlTeamDrawable = null;
		try {
			InputStream is = activity.getResources().getAssets().open((Constants.densityFolder + gridImageURLList.get(position)) + ".png");
			bowlTeamDrawable = Drawable.createFromStream(is, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		holder.imageView.setImageDrawable(bowlTeamDrawable);

		// imageLoader.displayImage(urladdress, holder.imageView, options);

		return vi;
	}

}
