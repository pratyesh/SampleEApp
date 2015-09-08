package com.prt.app.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import net.sf.andpdf.pdfviewer.PdfViewerActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ledexpo.android.R;
import com.prt.app.activity.CameraActivity;
import com.prt.app.activity.CommonPDFActivity;
import com.prt.app.db.ExecutionQuery;
import com.prt.app.fragment.DetailsGallary;
import com.prt.app.fragment.Feedback;
import com.prt.app.fragment.ImageZoom;
import com.prt.app.fragment.ListEvent;
import com.prt.app.fragment.VenueOnMap;
import com.prt.app.fragment.VideoFragment;
import com.prt.app.fragment.WebFragment;
import com.prt.app.fragment_basecontroller.BaseControllerFragment;
import com.prt.app.fragment_container.HomeContainerFragment;

public class Utility {

	public static long getAvailable(String path) {
		StatFs stat = new StatFs(path);
		long bytesAvailable = (long) stat.getBlockSize() * (long) stat.getBlockCount();
		long megAvailable = bytesAvailable / 1048576;

		return megAvailable;

	}

	public static void startAction(final Fragment fragment, int templateId, int action, boolean addToBackStack, Bundle bundle) {

		bundle.putInt("linkedScreen", action);

		switch (templateId) {
		case 0:

			break;
		case 10:
			startAction((ActionBarActivity) fragment.getActivity(), templateId, action, bundle);

			break;
		case 11:
			Feedback feeadback = new Feedback();
			feeadback.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(feeadback, addToBackStack);

			break;
		case 12:// schedule
			ListEvent listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);
			break;
		case 13:
			/**** --this is for camera action- */
			startAction((ActionBarActivity) fragment.getActivity(), templateId, action, bundle);
			break;
		case 14:
			WebFragment webFragment = new WebFragment();
			webFragment.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(webFragment, addToBackStack);

			break;

		case 15:
			DetailsGallary detailsGallary = new DetailsGallary();
			detailsGallary.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(detailsGallary, addToBackStack);
			break;

		case 16:
			VideoFragment videoFragment = new VideoFragment();
			videoFragment.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(videoFragment, addToBackStack);
			break;
		case 17:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		case 18:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);
			break;

		case 19:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);
			break;

		case 20:
			detailsGallary = new DetailsGallary();
			detailsGallary.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(detailsGallary, addToBackStack);
			break;
		case 21:
			detailsGallary = new DetailsGallary();
			detailsGallary.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(detailsGallary, addToBackStack);
			break;
		case 22:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);
			break;
		case 23:
			ImageZoom imageZoom = new ImageZoom();
			imageZoom.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(imageZoom, addToBackStack);
			break;
		case 24:// FAVORITES
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		case 25:// NOTES
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		case 26:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		case 27:
			detailsGallary = new DetailsGallary();
			detailsGallary.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(detailsGallary, addToBackStack);
			break;
		case 28:
			VenueOnMap venueOnMap = new VenueOnMap();
			venueOnMap.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(venueOnMap, addToBackStack);
			break;
		case 29:
			webFragment = new WebFragment();
			webFragment.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(webFragment, addToBackStack);
			break;
		case 30:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		case 31:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		case 32:
			try {

				final String image_url = bundle.getString("url");
				ExecutionQuery executionQuery = new ExecutionQuery(fragment.getActivity(), 5);
				final String pdf_url = executionQuery.getPresentationPdfUrl(image_url);
				executionQuery.closeDatabase();

				new AsyncTask<Void, Void, String>() {
					final ProgressDialog dialog = new ProgressDialog(fragment.getActivity());

					@Override
					protected void onPreExecute() {
						dialog.setMessage("Loading...");
						dialog.show();
						super.onPreExecute();
					}

					@Override
					protected String doInBackground(Void... params) {

						String url = "";
						Log.d("WEB PDF", "getPresentationPdfUrl");

						// String outFileName = "/data/data/" + getActivity().getPackageName() + "/databases/";
						String outFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
						File file = new File(outFileName, "Saturn Mobi Gallery");
						if (!file.exists()) {
							file.mkdir();
						}

						file = new File(file, "Presentation Files");
						if (!file.exists()) {
							file.mkdir();
						}

						url = pdf_url.substring(pdf_url.lastIndexOf("/") + 1);

						File zipFile = new File(file, url);
						if (!zipFile.exists())
							Utility.downloadFile(pdf_url, fragment.getActivity());

						return zipFile.getAbsolutePath();
					}

					@Override
					protected void onPostExecute(String result) {

						super.onPostExecute(result);
						if (dialog.isShowing())
							dialog.dismiss();

						try {
							Intent intent = new Intent(fragment.getActivity(), CommonPDFActivity.class);
							intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME, result);
							fragment.getActivity().startActivity(intent);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.execute();

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 33:
			// intent = new Intent(context, GroupListActivity.class);
			// intent.putExtra("linkedScreen", action);
			// context.startActivity(intent);
			break;
		case 34:
			// intent = new Intent(context, Gallary_Folders_List_Activity.class);
			// intent.putExtra("linkedScreen", action);
			// context.startActivity(intent);
			break;
		case 35:
			// NotesDbAdapter mDbHelper = new NotesDbAdapter(context);
			// mDbHelper.open();
			//
			// Cursor notesCursor = mDbHelper.fetchAllNotes();
			// if (notesCursor.getCount() == 0) {
			// Toast.makeText(context, R.string.no_notes, Toast.LENGTH_LONG).show();
			// notesCursor.close();
			// } else {
			// intent = new Intent(context, NotepadActivity.class);
			// intent.putExtra("linkedScreen", action);
			// context.startActivity(intent);
			// }
			// mDbHelper.close();
			break;
		case 36:
			// intent = new Intent(context, NoteEdit.class);
			// context.startActivity(intent);
			break;
		case 37:
			listEvent = new ListEvent();
			listEvent.setArguments(bundle);
			((BaseControllerFragment) fragment.getParentFragment()).replaceFragment(listEvent, addToBackStack);

			break;
		default:
			break;

		}

	}

	public static void startAction(AppCompatActivity activity, int templateId, int action, Bundle bundle) {
		// Bundle bundle = new Bundle();

		switch (templateId) {
		case 0:

			break;
		case 10:
			// bundle.putInt("linkedScreen", action);

			bundle.putString("dashboard", "openDashboard");
			HomeContainerFragment homeContainerFragment = new HomeContainerFragment();
			homeContainerFragment.setArguments(bundle);
			FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
			// transaction.addToBackStack(null);
			transaction.replace(R.id.fragment_container, homeContainerFragment);
			transaction.commit();
			break;
		case 13:
			Intent intent = new Intent(activity, CameraActivity.class);
			bundle.putInt("linkedScreen", action);
			intent.putExtras(bundle);

			activity.startActivity(intent);
			break;
		default:
			break;
		}

	}

	public static void copyAssets(Context context, String filename, String tag_name) throws IOException {
		copyAssets(context, filename);
		SharedPreferences sf = context.getSharedPreferences("saturn_mobi_app", Context.MODE_PRIVATE);
		Editor ed = sf.edit();
		ed.putBoolean(tag_name, true);
		ed.commit();
	}

	public static void copyAssets(Context context, String filename) throws IOException {

		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(filename);
		// Path to the just created empty db
		File file = new File("data");
		if (!file.exists())
			file.mkdir();

		file = new File(file, "data");
		if (!file.exists())
			file.mkdir();

		file = new File(file, context.getPackageName());
		if (!file.exists())
			file.mkdir();

		file = new File(file, "databases");
		if (!file.exists())
			file.mkdir();

		file = new File(file, filename);
		if (!file.exists())
			file.createNewFile();

		String outFileName = "/data/data/" + context.getPackageName() + "/databases/" + filename;
		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);

		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public static void downloadZip(String url, String downloadFileName, Context context) {
		int count = 0;
		String innerFileName = "";
		Log.d("DEBUG", "downloading----------------------------" + downloadFileName);
		URLConnection urlConnection;
		try {
			/************************************************
			 * 
			 * IF you are unzipping a zipped file save under some URL in remote server *
			 *********************************************/

			URL finalUrl = new URL(url
			/* Url string where the zipped file is stored... */);
			urlConnection = finalUrl.openConnection();

			// Get the size of the ( zipped file's) inputstream from
			// server..
			int contentLength = urlConnection.getContentLength();
			Log.d("DEBUG", "urlConnection.getContentLength():" + contentLength);

			urlConnection = finalUrl.openConnection();
			urlConnection.setConnectTimeout(2000);

			/*****************************************************
			 * 
			 * YOU CAN GET INPUT STREAM OF A ZIPPED FILE FROM ASSETS FOLDER AS WELL..,IN THAT CASE JUST PASS THAT INPUTSTEAM OVER HERE...MAKE SURE YOU HAVE SET STREAM CONTENT LENGTH OF THE SAME..
			 * 
			 ******************************************************/

			InputStream input = new BufferedInputStream(urlConnection.getInputStream());

			String outFileName = "/data/data/" + context.getPackageName() + "/databases/";
			File zipFile = new File(outFileName, downloadFileName);
			if (!zipFile.exists())
				zipFile.createNewFile();

			FileOutputStream output = new FileOutputStream(zipFile.getAbsolutePath());

			byte data[] = new byte[2048];

			long tot = 0;

			while ((count = input.read(data)) != -1) {
				tot += count;
				output.write(data, 0, count);
			}

			output.flush();
			output.close();
			input.close();

			extractZip(zipFile.getAbsolutePath());

			// // File zipFile = new File(Constants.path + packageName + ".zip");
			// File extractFile = new File(outFileName);
			// unzip(zipFile, extractFile);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static void downloadFile(String url, Context context) {
		String downloadFileName = url.substring(url.lastIndexOf("/") + 1);
		int count = 0;
		Log.d("DEBUG", "downloading----------------------------" + downloadFileName);
		URLConnection urlConnection;
		try {

			URL finalUrl = new URL(url);
			urlConnection = finalUrl.openConnection();

			int contentLength = urlConnection.getContentLength();
			Log.d("DEBUG", "urlConnection.getContentLength():" + contentLength);

			urlConnection = finalUrl.openConnection();
			urlConnection.setConnectTimeout(2000);

			InputStream input = new BufferedInputStream(urlConnection.getInputStream());

			String outFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
			File file = new File(outFileName, "Saturn Mobi Gallery");
			if (!file.exists()) {
				file.mkdir();
			}

			file = new File(file, "Presentation Files");
			if (!file.exists()) {
				file.mkdir();
			}

			// String outFileName = "/data/data/" + context.getPackageName() + "/databases/";
			File zipFile = new File(file, downloadFileName);
			if (!zipFile.exists())
				zipFile.createNewFile();

			FileOutputStream output = new FileOutputStream(zipFile.getAbsolutePath());

			byte data[] = new byte[2048];

			while ((count = input.read(data)) != -1) {

				output.write(data, 0, count);
			}

			output.flush();
			output.close();
			input.close();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public static final void unzip(File zip, File extractTo) throws IOException {
		Log.d("DEBUG", "unzipping----------------------------");
		ZipFile archive = new ZipFile(zip);
		Enumeration e = archive.entries();
		while (e.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) e.nextElement();
			File file = new File(extractTo, entry.getName());
			if (entry.isDirectory() && !file.exists()) {

				file.mkdirs();
			} else {
				if (!file.getParentFile().exists()) {

					file.getParentFile().mkdirs();
				}

				InputStream in = archive.getInputStream(entry);
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));

				byte[] buffer = new byte[8192];
				int read;

				while (-1 != (read = in.read(buffer))) {
					out.write(buffer, 0, read);
				}

				in.close();
				out.close();
			}
		}
	}

	public static void extractZip(String absoluteFileName) {
		/*
		 * Iterate over all the files and folders
		 */
		int total = 0;
		String jsonFile = "";
		int count = 0;
		try {
			File file = new File(absoluteFileName);
			FileInputStream fileInputStream = new FileInputStream(file);
			ZipInputStream zipInputStream = new ZipInputStream(fileInputStream);

			for (ZipEntry zipEntry = zipInputStream.getNextEntry(); zipEntry != null; zipEntry = zipInputStream.getNextEntry()) {

				/*
				 * Extracted file will be saved with same file name that in zipped folder.
				 */
				String innerFileName = file.getParent() + "/" + zipEntry.getName();

				File innerFile = new File(innerFileName);

				/*
				 * Checking for pre-existence of the file and taking necessary actions
				 */
				if (innerFile.exists()) {

					innerFile.delete();
				}

				/*
				 * Checking for extracted entry for folder type..and taking necessary actions
				 */
				if (zipEntry.isDirectory()) {

					innerFile.mkdirs();
				} else {

					FileOutputStream outputStream = new FileOutputStream(innerFileName);
					final int BUFFER_SIZE = 2048;

					/*
					 * Get the buffered output stream..
					 */
					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, BUFFER_SIZE);
					/*
					 * Write into the file's buffered output stream ,..
					 */

					byte[] buffer = new byte[BUFFER_SIZE];

					while ((count = zipInputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
						total += count;
						// publishProgress(""
						// + (int) ((total * 100) / contentLength));
						// System.out.println("count" +count +
						// "total "+total + "lenght "+contentLength);
						bufferedOutputStream.write(buffer, 0, count);
					}
					/***********************************************
					 * IF YOU WANT TO TRACK NO OF FILES DOWNLOADED, HAVE A STATIC COUNTER VARIABLE, INITIALIZE IT IN startUnzipping() before calling startUnZipping(), AND INCREMENT THE COUNTER VARIABLE OVER
					 * HERE..LATER YOU CAN USE VALUE OF COUNTER VARIABLE TO CROSS VERIFY WHETHER ALL ZIPPED FILES PROPERLY UNZIPPED AND SAVED OR NOT.
					 * 
					 * ************************************************
					 */
					/*
					 * Handle closing of output streams..
					 */
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
				/*
				 * Finish the current zipEntry
				 */
				zipInputStream.closeEntry();

			}
			zipInputStream.close();

			/*
			 * here zip file is deleting
			 */
			file.delete();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static boolean checkInternetConnection(Context context) {

		ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		// ARE WE CONNECTED TO THE NET
		if (conMgr.getActiveNetworkInfo() != null

		&& conMgr.getActiveNetworkInfo().isAvailable()

		&& conMgr.getActiveNetworkInfo().isConnected()) {

			return true;

		} else {
			return false;
		}
	}
}
