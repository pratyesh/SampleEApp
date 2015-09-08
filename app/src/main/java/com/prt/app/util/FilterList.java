package com.prt.app.util;

import java.util.ArrayList;

import com.prt.app.bean.ExhibitorBean;
import com.prt.app.bean.SpeakerBean;

public class FilterList {

	public ArrayList<ExhibitorBean> getFilteredExhibitor(ArrayList<ExhibitorBean> exhibitorBeanList, String text) {
		ArrayList<ExhibitorBean> filterredExhibitorBeanList = new ArrayList<ExhibitorBean>();
		for (ExhibitorBean exhibitorBean : exhibitorBeanList) {
			if (exhibitorBean.getCompanyName().toUpperCase().contains(text.trim().toUpperCase())) {
				filterredExhibitorBeanList.add(exhibitorBean);
			}
		}
		return filterredExhibitorBeanList;
	}

	public ArrayList<SpeakerBean> getFilteredSpeaker(ArrayList<SpeakerBean> speakerBeanList, String text) {
		ArrayList<SpeakerBean> filterredSpeakerBeanList = new ArrayList<SpeakerBean>();
		for (SpeakerBean speakerBean : speakerBeanList) {
			if (speakerBean.getSpeakerName().toUpperCase().contains(text.trim().toUpperCase())) {
				filterredSpeakerBeanList.add(speakerBean);
			}
		}
		return filterredSpeakerBeanList;
	}
}
