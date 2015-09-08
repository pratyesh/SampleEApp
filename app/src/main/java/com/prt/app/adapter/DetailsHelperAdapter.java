package com.prt.app.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.prt.app.bean.ExhibitorBean;
import com.prt.app.bean.ScheduleBean;
import com.prt.app.bean.SpeakerBean;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.fragment.DetailsHelper;
import com.prt.app.util.IdentityConstant;

/**
 * @author Pratyesh Singh
 */
public class DetailsHelperAdapter extends FragmentPagerAdapter {

	private ArrayList<ExhibitorBean> exhibitorBeanList;
	private ArrayList<SpeakerBean> speakerBeanList;
	private ArrayList<ScheduleBean> scheduleList;
	private ArrayList<String> image_gallery;

	private int templateId;

	public DetailsHelperAdapter(FragmentManager fm, Bundle bundle, int templateId, Context context) {
		super(fm);
		this.templateId = templateId;

		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {
			this.exhibitorBeanList = bundle.getParcelableArrayList("exhibitorBeanList");
		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			this.speakerBeanList = bundle.getParcelableArrayList("speakerBeanList");
		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {
			this.scheduleList = bundle.getParcelableArrayList("scheduleList");
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			String folder_name = bundle.getString("folder_name");
			ExecutionQuery executionQuery = new ExecutionQuery(context, 4);
			image_gallery = executionQuery.getImageGalleryList(folder_name);
			executionQuery.closeDatabase();
		}

	}

	@Override
	public Fragment getItem(int position) {

		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {
			return DetailsHelper.newInstance(exhibitorBeanList.get(position), templateId);
		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			return DetailsHelper.newInstance(speakerBeanList.get(position), templateId);
		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {
			return DetailsHelper.newInstance(scheduleList.get(position), templateId);
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			return DetailsHelper.newInstance(image_gallery.get(position), templateId);
		} else {
			return DetailsHelper.newInstance(exhibitorBeanList.get(position), templateId);
		}

	}

	@Override
	public CharSequence getPageTitle(int position) {

		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {
			return exhibitorBeanList.get(position).getCompanyName().toUpperCase();
		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			return speakerBeanList.get(position).getSpeakerName().toUpperCase();
		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {
			return scheduleList.get(position).getScheduleName().toUpperCase();
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			return "".toUpperCase();
		} else {
			return exhibitorBeanList.get(position).getCompanyName().toUpperCase();
		}
	}

	@Override
	public int getCount() {

		if (templateId == IdentityConstant.EXHIBITORDETAIL.VALUE) {
			return exhibitorBeanList.size();
		} else if (templateId == IdentityConstant.SPEAKERDETAILS.VALUE) {
			return speakerBeanList.size();
		} else if (templateId == IdentityConstant.SCHEDULEDETAILS.VALUE) {
			return scheduleList.size();
		} else if (templateId == IdentityConstant.GALLERY_FOLDERS_IMAGE_NEW.VALUE) {
			return image_gallery.size();
		} else {
			return exhibitorBeanList.size();
		}
	}

}
