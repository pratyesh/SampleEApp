package com.prt.app.fragment;

import java.util.Hashtable;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.api.HTTP_ApiRequest;
import com.prt.app.util.AsyncTaskExecuter;
import com.prt.app.util.Constants;
import com.prt.app.util.Utility;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * @author Pratyesh Singh
 */
public class SendMeetingRequest extends Fragment {
	private Bundle bundle;

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		bundle = getArguments();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		View view = inflater.inflate(R.layout.fragment_send_meeting_request, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		init(getView());
	}

	private void init(final View view) {

		TextView send = (TextView) view.findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText name = (EditText) view.findViewById(R.id.name);
				EditText email = (EditText) view.findViewById(R.id.email);
				EditText contact = (EditText) view.findViewById(R.id.contact);
				EditText company = (EditText) view.findViewById(R.id.company);

				if (name.getText().toString().trim().equals("")) {
					Crouton.makeText(getActivity(), "Name should not be blank", Style.INFO).show();
					return;
				}
				String _email = email.getText().toString().trim();
				if (_email.equals("")) {
					Crouton.makeText(getActivity(), "Email Id should not be blank", Style.INFO).show();
					return;
				} else if (!_email.contains("@") || !_email.contains(".") || _email.contains("@.") || _email.contains(".@")) {

					Crouton.makeText(getActivity(), "please enter valid email", Style.INFO).show();
					return;
				}

				JSONObject request_body = new JSONObject();
				try {

					request_body.put("senderName", name.getText().toString());
					request_body.put("from", email.getText().toString());
					request_body.put("senderContact", contact.getText().toString());
					request_body.put("company", company.getText().toString());
					request_body.put("to", bundle.getString("to"));
					request_body.put("receiverName", bundle.getString("receiverName"));
					request_body.put("eventId", bundle.getInt("eventId"));

				} catch (JSONException e) {
					e.printStackTrace();
				}

				Hashtable<String, String> header = new Hashtable<String, String>();
				header.put("Content-Type", "application/json");
				String searchUrl = Constants.meetingrequest_Url;
				String requestType = "POST";

				AsyncTaskExecuter.AsyncTaskExecuterListener asyncTaskExecuterListener = new AsyncTaskExecuter.AsyncTaskExecuterListener() {

					@Override
					public void notifyRespons(Object[] result) {
						int resid = (Integer) result[0];
						if (resid == 200) {
							try {
								JSONObject res = (JSONObject) result[1];
								// Toast.makeText(getActivity(), res.getString("status"), Toast.LENGTH_LONG).show();
								Toast.makeText(getActivity(), " send successfully", Toast.LENGTH_LONG).show();
								getActivity().onBackPressed();
							} catch (Exception e) {
								e.printStackTrace();
							}
						} else {
							Toast.makeText(getActivity(), "Please try after some time", Toast.LENGTH_LONG).show();
						}
					}
				};
				if (Utility.checkInternetConnection(getActivity())) {
					new AsyncTaskExecuter(getActivity(), asyncTaskExecuterListener, searchUrl, request_body.toString(), requestType, header, true).execute();
				} else {
					Crouton.makeText(getActivity(), getResources().getString(R.string.no_internet_connection_found), Style.INFO).show();
				}

				// Object[] obj = HTTP_ApiRequest.getApiResult(searchUrl, request_body.toString(), requestType, header);

			}
		});

	}
}
