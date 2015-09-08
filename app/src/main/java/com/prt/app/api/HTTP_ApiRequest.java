package com.prt.app.api;

import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * @author Pratyesh Singh
 */

public class HTTP_ApiRequest {

	static int responseCode = -1;

	public static Object[] getApiResult(String searchUrl, String request_body, String requestType, Hashtable<String, String> header) {

		final Object[] obj = new Object[2];

		LMDownloader rwDownloader = null;
		if (requestType.trim().equalsIgnoreCase("DELETE")) {
			rwDownloader = new LMDownloader(searchUrl, request_body, "", 2, "DELETE", header);
		} else if (requestType.trim().equalsIgnoreCase("GET")) {
			rwDownloader = new LMDownloader(searchUrl);
		} else if (requestType.trim().equalsIgnoreCase("POST")) {
			rwDownloader = new LMDownloader(searchUrl, request_body, "", 2, "POST", header);
		} else if (requestType.trim().equalsIgnoreCase("PUT")) {
			rwDownloader = new LMDownloader(searchUrl, request_body, "", 2, "PUT", header);
		} else {
			rwDownloader = new LMDownloader(searchUrl);
		}

		rwDownloader.addHttpLIstener(new HttpListener() {

			@Override
			public void notifyRespons(LMDownloader rwDownloader) {

				obj[1] = null;

				responseCode = rwDownloader.getResCode();
				String res = rwDownloader.getResponse();
				if (responseCode == 200) {

					try {

						Object json = new JSONTokener(res).nextValue();
						if (json instanceof JSONObject) {
							JSONObject jsonObject = (JSONObject) json;
							obj[1] = jsonObject;
						} else if (json instanceof JSONArray) {
							JSONArray jsonArray = (JSONArray) json;
							obj[1] = jsonArray;
						} else {
							// in case type of response will differ that JSON
							obj[1] = res;
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}

				} else if (res.equalsIgnoreCase("TIMEOUT")) {
					responseCode = -47;
				}
			}
		});
		rwDownloader.sendPostBlocking();
		obj[0] = responseCode;

		return obj;
	}
}
