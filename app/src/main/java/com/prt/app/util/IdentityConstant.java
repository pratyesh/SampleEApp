package com.prt.app.util;

public enum IdentityConstant {
	/** ----------------------------------- */
	SCHEDULE(12), CAMERA(13), WEB(14), EXHIBITORDETAIL(15), VIDEO(16), DEFAULT_LIST(17), GALLERY_FOLDERS(18), GALLERY_FOLDERS_IMAGE(19), SPEAKERDETAILS(20), SCHEDULEDETAILS(21), NOTIFICATION(22), FLOORPLAN(23), FAVORITES(
			24), NOTES(25), EXHIBITORS(26), GALLERY_FOLDERS_IMAGE_NEW(27), VenueOnMap(28), Venue(29), FLOOR_FOLDERS(30), PRESENTATION_FOLDERS(31), PDF_PRESENTATION(32), SPEAKER(37);

	/** ----------------------------------- */

	public int VALUE;

	IdentityConstant(int value) {
		this.VALUE = value;

	}
}
