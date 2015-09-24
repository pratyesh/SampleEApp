package com.prt.app.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
//import android.widget.ProgressBar;
//
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.FailReason;
//import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.ledexpo.android.R;

public class ImagePagerAdapter extends PagerAdapter {

	private int[] images;
	private LayoutInflater inflater;

	// ImageLoader imageLoader = ImageLoader.getInstance();
	// DisplayImageOptions options = null;
	OnClickListener onClickListener;

	public ImagePagerAdapter(int[] images, Activity activity, OnClickListener onClickListener) {
		this.images = images;
		inflater = activity.getLayoutInflater();
		this.onClickListener = onClickListener;
		// options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.slideimg).showImageOnFail(R.drawable.slideimg).resetViewBeforeLoading(true).cacheOnDisc(true)
		// .imageScaleType(ImageScaleType.EXACTLY_STRETCHED).bitmapConfig(Bitmap.Config.RGB_565).considerExifParams(true).displayer(new FadeInBitmapDisplayer(300)).build();
		// imageLoader.init(ImageLoaderConfiguration.createDefault(activity));

	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView((View) object);
	}

	@Override
	public void finishUpdate(View container) {
	}

	@Override
	public int getCount() {
		return images.length;
	}

	@Override
	public Object instantiateItem(ViewGroup view, int position) {
		View imageLayout = inflater.inflate(R.layout.item_pager_image, view, false);
		ImageView imageView = (ImageView) imageLayout.findViewById(R.id.image);
		imageView.setImageResource(images[position]);

		// final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
		//
		// imageLoader.displayImage(images[position], imageView, options, new SimpleImageLoadingListener() {
		//
		// @Override
		// public void onLoadingStarted(String imageUri, View view) {
		// spinner.setVisibility(View.VISIBLE);
		// }
		//
		// @Override
		// public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
		// String message = null;
		// switch (failReason.getType()) {
		// case IO_ERROR:
		// message = "Input/Output error";
		// break;
		// case DECODING_ERROR:
		// message = "Image can't be decoded";
		// break;
		// case NETWORK_DENIED:
		// message = "Downloads are denied";
		// break;
		// case OUT_OF_MEMORY:
		// message = "Out Of Memory error";
		// break;
		// case UNKNOWN:
		// message = "Unknown error";
		// break;
		// } // Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
		//
		// spinner.setVisibility(View.GONE);
		// }
		//
		// @Override
		// public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
		// spinner.setVisibility(View.GONE);
		// }
		// });
		// imageView.setOnClickListener(onClickListener);
		((ViewPager) view).addView(imageLayout, 0);
		return imageLayout;
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view.equals(object);
	}

	@Override
	public void restoreState(Parcelable state, ClassLoader loader) {
	}

	@Override
	public Parcelable saveState() {
		return null;
	}

	@Override
	public void startUpdate(View container) {
	}
}
