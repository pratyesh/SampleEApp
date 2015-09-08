package com.prt.app.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.prt.app.activity.HomeActivity;
import com.prt.app.bean.ScreenBean;
import com.prt.app.bean.WebBean;
import com.prt.app.util.Constants;
import com.prt.app.util.IdentityConstant;

/**
 * @author Pratyesh Singh
 */
public class WebFragment extends Fragment {

	private ScreenBean screenBean;
	public int action;
	public int templateId;
	String image_url = "";

	@Override
	public void onAttach(Activity activity) {

		super.onAttach(activity);
	}

	@Override
	public void onResume() {
		if (templateId == IdentityConstant.Venue.VALUE) {
			((HomeActivity) getActivity()).findViewById(R.id.show_on_map).setVisibility(View.VISIBLE);
		}
		((HomeActivity) getActivity()).getSupportActionBar().show();
		super.onResume();
	}

	@Override
	public void onPause() {
		if (templateId == IdentityConstant.Venue.VALUE) {
			((HomeActivity) getActivity()).findViewById(R.id.show_on_map).setVisibility(View.GONE);
		}
		super.onPause();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		try {
			Bundle bundle = getArguments();
			image_url = bundle.getString("url");

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
		View view = inflater.inflate(R.layout.fragment_web, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		try {

			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init() {
		final Activity activity = getActivity();

		// getActivity().setTitle(screenBean.getTitle());
		String title = screenBean.getTitle();
		((TextView) getActivity().findViewById(R.id.titleText)).setText(screenBean.getTitle());

		WebView webView = (WebView) getView().findViewById(R.id.webView);
		String url = "";
		if (image_url != null && !image_url.trim().equals("")) {
			url = image_url;
		} else {
			ArrayList<WebBean> webBeansList = screenBean.getWebBeansList();

			final WebBean webBean = webBeansList.get(0);
			url = webBean.getUrl();
		}

		webView.getSettings().setJavaScriptEnabled(true);
		if (templateId == IdentityConstant.Venue.VALUE) {
			webView.getSettings().setBuiltInZoomControls(true);
			webView.getSettings().setSupportZoom(true);
		}
		webView.setWebChromeClient(new WebChromeClient() {

			public void onProgressChanged(WebView view, int progress) {
				if (getView() == null)
					return;
				ProgressBar pd = (ProgressBar) getView().findViewById(R.id.progressBar);
				pd.setVisibility(ProgressBar.VISIBLE);

				if (progress < 100 && pd.getVisibility() == ProgressBar.GONE) {
					pd.setVisibility(ProgressBar.VISIBLE);
				}
				pd.setProgress(progress);
				if (progress == 100) {
					pd.setVisibility(ProgressBar.GONE);
				}
			}
		});

		webView.setWebViewClient(new WebViewClient() {
			@Override
			public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
				// Handle the error
				// Toast.makeText(activity, "Ooops! " + description, Toast.LENGTH_SHORT).show();
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				String mailToRegexp = "mailto\\:([^?]+)\\?{0,1}((subject\\=([^&]+))|(body\\=([^&]+))|(bcc\\=([^&]+))|(cc\\=([^&]+)))*";
				Pattern mailToPattern = Pattern.compile(mailToRegexp);
				Matcher mailToMatcher = mailToPattern.matcher(url);
				if (mailToMatcher.find()) {
					String email = mailToMatcher.group(1);
					String subject = mailToMatcher.group(4);
					String body = mailToMatcher.group(6);
					String bcc = mailToMatcher.group(8);
					String cc = mailToMatcher.group(10);

					Intent intent = new Intent(Intent.ACTION_SEND);

					intent.setType("message/rfc822");
					if (email != null)
						intent.putExtra(Intent.EXTRA_EMAIL, new String[] { email });
					if (subject != null)
						intent.putExtra(Intent.EXTRA_SUBJECT, subject);
					if (body != null)
						intent.putExtra(Intent.EXTRA_TEXT, body);
					if (bcc != null)
						intent.putExtra(Intent.EXTRA_BCC, new String[] { bcc });
					if (cc != null)
						intent.putExtra(Intent.EXTRA_CC, new String[] { cc });

					// intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "recipient@example.com", "pratyesh3test@gmail.com" });
					// intent.putExtra(Intent.EXTRA_SUBJECT, "android fragment send email");
					// intent.putExtra(Intent.EXTRA_TEXT, "This is for testing a mail");
					// intent.putExtra(Intent.EXTRA_BCC, new String[] { "recipient@example.com", "pratyesh3test@gmail.com" });
					// intent.putExtra(Intent.EXTRA_CC, new String[] { "recipient@example.com", "pratyesh3test@gmail.com" });

					activity.startActivity(Intent.createChooser(intent, "Send Email"));

					return true;
				} else {
					view.loadUrl(url);
					return true;
				}
			}
		});

		if (url.startsWith("http")) {
			webView.loadUrl(url);
		} else {
			webView.loadUrl("file:///data/data/" + getActivity().getPackageName() + "/databases/" + url);// html/contact.html

		}

		WebSettings webSettings = webView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webView.requestFocus();
	}

	@Override
	public void onStart() {

		super.onStart();
		FlurryAgent.setCaptureUncaughtExceptions(true);
		FlurryAgent.onStartSession(getActivity(), Constants.flurryKey);
		HashMap<String, String> data = new HashMap<String, String>();
		data.put(Constants.SCREEN, Constants.FLURRY_WEB_VIEW);
		data.put(Constants.SUB_EVENT, ((TextView) getActivity().findViewById(R.id.titleText)).getText().toString());
		FlurryAgent.logEvent(Constants.FLURRY_WEB_VIEW, data);
	}

	@Override
	public void onStop() {

		super.onStop();
		FlurryAgent.onEndSession(getActivity());
	}
}
