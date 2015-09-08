package com.prt.app.customcomponent;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.prt.app.util.ScaleImageView;

public class EnableDisableViewPager extends ViewPager {
	private boolean enabled = true;

	public EnableDisableViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
		if (enabled)
			return super.onInterceptTouchEvent(motionEvent);

		return false;
	}

	// @Override
	// protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
	// if (v instanceof ScaleImageView) {
	// return ((ScaleImageView) v).canScroll(dx);
	// } else {
	// return super.canScroll(v, checkV, dx, x, y);
	// }
	// }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
