package com.prt.app.activity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.flurry.android.FlurryAgent;
import com.ledexpo.android.R;
import com.prt.app.bean.Map2DBean;
import com.prt.app.bean.ScreenBean;
import com.prt.app.util.Constants;

// ----------------------------------------------------------------------

public class CameraActivity extends FragmentActivity {

	private Preview mPreview;
	private final String TAG = "Camera";
	FrameLayout preview;
	Camera mCamera;
	public static Bitmap b = null;
	int orientation = 0;
	String myVersion;
	int sdkVersion;
	int orientationMode;
	Bitmap bMapRotate;
	BitmapFactory.Options options;
	MediaScannerConnection conn = null;
	private Bundle bundle = null;
	private int screenId;
	ScreenBean screenBean;
	Map2DBean imagebean;
	int height = 0;
	int width = 0;
	String url = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// bundle = getIntent().getExtras();
		// screenBean = CommonFunctions.returnScreenObject(String.valueOf(screenId));

		setContentView(R.layout.cam);

		try {
			myVersion = android.os.Build.VERSION.RELEASE; // e.g. myVersion :=
															// "1.6"
			sdkVersion = android.os.Build.VERSION.SDK_INT; // e.g. sdkVersion :=
															// 8;
			orientationMode = getResources().getConfiguration().orientation;

		} catch (Exception e) {

		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		// Setup the FrameLayout with the Camera Preview Screen
		mPreview = new Preview(this);
		preview = (FrameLayout) findViewById(R.id.preview);
		preview.addView(mPreview);
	}

	public void snap(View view) {
		int size = 2;
		// System.out.println("CommonFunctions.getAvailable=" + CommonFunctions.getAvailable(Constants.path));
		// if (Utility.getAvailable(Constants.path) > size) {
		mCamera.takePicture(shutterCallback, rawCallback, myPictureCallback_JPG);
		// } else {
		// Toast.makeText(this, getResources().getString(R.string.lowMemoryMessage), Toast.LENGTH_LONG).show();
		// this.finish();
		// }

	}

	ShutterCallback shutterCallback = new ShutterCallback() {
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	PictureCallback rawCallback = new PictureCallback() {
		public void onPictureTaken(byte[] _data, Camera _camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	PictureCallback myPictureCallback_JPG = new PictureCallback() {
		@Override
		public void onPictureTaken(byte[] data, Camera arg1) {
			options = new BitmapFactory.Options();

			Bitmap bMap = null;
			bMap = BitmapFactory.decodeByteArray(data, 0, data.length);

			options.inSampleSize = 1;
			options.inDither = false; // Disable Dithering mode
			options.inPurgeable = true; // Tell to gc that whether it needs free
										// memory, the Bitmap can be cleared
			options.inInputShareable = true; // Which kind of reference will be
												// used to recover the Bitmap
												// data after being clear, when
												// it will be used in the future
			options.inTempStorage = new byte[32 * 1024];
			options.inPreferredConfig = Bitmap.Config.RGB_565;
			// bMap = BitmapFactory.decodeByteArray(arg0, 0, arg0.length, options);

			// others devices
			if (myVersion.substring(0, 3).equals("2.1") && (sdkVersion == 7)) {
				setOrientation_2_1();

			} else {
				setOrientation_2_2();
			}

			if (orientation != 0) {
				Matrix matrix = new Matrix();
				matrix.postRotate(orientation);

				// //
				DisplayMetrics metrics = new DisplayMetrics();
				getWindowManager().getDefaultDisplay().getMetrics(metrics);
				int height = metrics.heightPixels;
				// //
				// if (height == 1280) {
				// bMapRotate = Bitmap.createBitmap(bMap, 0, 0, 1024, 800, matrix, true);
				// } else {
				bMapRotate = Bitmap.createBitmap(bMap, 0, 0, bMap.getWidth(), bMap.getHeight(), matrix, true);
				// }

			} else {
				bMapRotate = Bitmap.createScaledBitmap(bMap, bMap.getWidth(), bMap.getHeight(), true);
			}

			AlertDialog.Builder customBuilder = new AlertDialog.Builder(new ContextThemeWrapper(CameraActivity.this, android.R.style.Theme_Dialog));

			customBuilder.setTitle(R.string.app_name);
			customBuilder.setNegativeButton("Discard", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					bMapRotate = null;
					mCamera.startPreview();

				}
			});
			customBuilder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					new SaveImageTask().execute();

				}
			});
			// ///
			DisplayMetrics metrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			int height = metrics.heightPixels;
			// ///
			AlertDialog dialog = customBuilder.create();
			dialog.setCancelable(false);
			dialog.show();
			Button negative = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
			if (negative != null) {
				negative.setBackgroundColor(Color.parseColor("#1073A1"));
				// negative.setWidth(100);
				// if (height == 1280) {
				// negative.setHeight(100);
				// negative.setTextSize(20);
				// } else {
				// negative.setHeight(40);
				// negative.setTextSize(16);
				// }

			}
			Button positive = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
			if (positive != null) {
				positive.setBackgroundColor(Color.parseColor("#11A5C6"));
				positive.setWidth(100);
				// if (height == 1280) {
				// positive.setHeight(100);
				// positive.setTextSize(20);
				// } else {
				// positive.setHeight(40);
				// positive.setTextSize(16);
				// }
			}
		}
	};

	public Bitmap combineImages(Bitmap logo, Bitmap save) { // can add a 3rd parameter
		// 'String loc' if you
		// want to save the new
		// image - left some
		// code to do that at
		// the bottom
		Bitmap cs = null;

		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int deviceWidth = metrics.widthPixels;
		int deviceHeight = metrics.heightPixels;

		System.out.println("deviceWidth=" + deviceWidth);
		System.out.println("logoWidth=" + logo.getWidth());
		System.out.println("saveWidth=" + save.getWidth());

		int width, height = 0;

		if (logo.getWidth() > save.getWidth()) {
			width = logo.getWidth();
			height = logo.getHeight() + save.getHeight();
		} else {
			width = save.getWidth();
			height = save.getHeight() + logo.getHeight();
		}

		int scaleWidth = getWidth(logo.getWidth(), deviceWidth, width);
		int scaleHeight = getHeight(logo.getHeight(), deviceHeight, height);

		Bitmap newlogo = Bitmap.createScaledBitmap(logo, scaleWidth, scaleHeight, true);

		cs = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);// ARGB_8888
		Canvas comboImage = new Canvas(cs);

		int xCoordinate = (deviceWidth) - (logo.getWidth()) / 2;
		int yCoordinate = (deviceHeight - logo.getHeight()) / 2;

		// this is for logo adding into your image
		comboImage.drawBitmap(save, 0f, 0f, null);

		// for Left Top Corner
		comboImage.drawBitmap(newlogo, 0, 0, null);

		// for Right Top Corner
		// comboImage.drawBitmap(newlogo, scaleWidth, 0, null);

		// for Left Bottom Corner
		// comboImage.drawBitmap(newlogo, 0, height - scaleHeight, null);

		// for Right Bottom Corner
		// comboImage.drawBitmap(newlogo, width - scaleWidth, height - scaleHeight, null);

		comboImage.save();

		// comboImage.drawbi
		// this is an extra bit I added, just incase you want to save the new
		// image somewhere and then return the location
		/*
		 * String tmpImg = String.valueOf(System.currentTimeMillis()) + ".png";
		 * 
		 * OutputStream os = null; try { os = new FileOutputStream(loc + tmpImg); cs.compress(CompressFormat.PNG, 100, os); } catch(IOException e) { Log.e("combineImages", "problem combining images", e); }
		 */
		return cs;

	}

	public static int getWidth(int logo, int screen, int snap) {
		int percentage = (100 * logo) / screen;
		int width = (snap * percentage) / 100;

		return width;

	}

	public static int getHeight(int logo, int screen, int snap) {
		int percentage = (100 * logo) / screen;
		int height = (snap * percentage) / 100;

		return height;

	}

	public void setOrientation_2_2() {
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

		if (display.getRotation() == Surface.ROTATION_0) {
			orientation = 90;
		}

		if (display.getRotation() == Surface.ROTATION_90) {
			orientation = 0;
		}

		if (display.getRotation() == Surface.ROTATION_180) {
			orientation = 270;
		}
		if (display.getRotation() == Surface.ROTATION_270) {
			orientation = 180;
		}

	}

	public void setOrientation_2_1() {

		if (orientationMode == 1) {
			orientation = 90;
		}
		if (orientationMode == 2) {

			orientation = 0;
		}
		if (orientationMode == 3) {
			orientation = 180;
		}
		if (orientationMode == 0) {
			orientation = 0;
		}

	}

	private void startMediaScanner(String fileName) {
		MediaScannerConnection.scanFile(this, new String[] { fileName }, null, new MediaScannerConnection.OnScanCompletedListener() {
			public void onScanCompleted(String path, Uri uri) {
				Toast.makeText(CameraActivity.this, "Media scan Completed", Toast.LENGTH_SHORT).show();
			}
		});
	}

	// ----------------------------------------------------------------------

	class Preview extends SurfaceView implements SurfaceHolder.Callback {
		SurfaceHolder mHolder;

		Preview(Context context) {
			super(context);

			// Install a SurfaceHolder.Callback so we get notified when the
			// underlying surface is created and destroyed.
			mHolder = getHolder();
			mHolder.addCallback(this);
			mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}

		public void surfaceCreated(SurfaceHolder holder) {
			// The Surface has been created, acquire the camera and tell it
			// where
			// to draw.
			if (mCamera == null) {
				mCamera = Camera.open();
				try {
					mCamera.setPreviewDisplay(holder);

					// TODO test how much setPreviewCallbackWithBuffer is faster
					mCamera.setPreviewCallback(new PreviewCallback() {
						@Override
						public void onPreviewFrame(byte[] data, Camera arg1) {
							FileOutputStream outStream = null;
							try {

							} catch (Exception e) {
								e.printStackTrace();
							} finally {
							}
							Preview.this.invalidate();
						}
					});

				} catch (IOException e) {
					mCamera.release();
					mCamera = null;
				}
			}

		}

		public void surfaceDestroyed(SurfaceHolder holder) {
			// Surface will be destroyed when we return, so stop the preview.
			// Because the CameraDevice object is not a shared resource, it's
			// very
			// important to release it when the activity is paused.

			if (mCamera != null) {
				mCamera.stopPreview();
				mCamera.setPreviewCallback(null);
				mCamera.release();
				mCamera = null;
			}
		}

		private Size getOptimalPreviewSize(List<Size> sizes, int w, int h) {
			final double ASPECT_TOLERANCE = 0.05;
			double targetRatio = (double) w / h;
			if (sizes == null)
				return null;

			Size optimalSize = null;
			double minDiff = Double.MAX_VALUE;

			int targetHeight = h;

			// Try to find size and match aspect ratio and size
			for (Size size : sizes) {
				double ratio = (double) size.width / size.height;
				if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE)
					continue;
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}

			// Cannot find the one match the aspect ratio, ignore the
			// requirement
			if (optimalSize == null) {
				minDiff = Double.MAX_VALUE;
				for (Size size : sizes) {
					if (Math.abs(size.height - targetHeight) < minDiff) {
						optimalSize = size;
						minDiff = Math.abs(size.height - targetHeight);
					}
				}
			}
			return optimalSize;
		}

		public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
			// Now that the size is known, set up the camera parameters and
			// begin
			// the preview.

			Camera.Parameters parameters = mCamera.getParameters();

			if ((myVersion.substring(0, 3).equals("2.1")) && (sdkVersion == 7)) {

				if (orientationMode == 1) {
					System.out.println(getResources().getConfiguration().ORIENTATION_PORTRAIT);
					parameters.set("orientation", "portrait");
				}
				if (orientationMode == 2) {
					System.out.println(getResources().getConfiguration().ORIENTATION_LANDSCAPE);
					parameters.set("orientation", "landscape");
				}
				if (orientationMode == 3) {
					System.out.println(getResources().getConfiguration().ORIENTATION_SQUARE);
					parameters.set("orientation", "square");
				}
				if (orientationMode == 0) {
					System.out.println(getResources().getConfiguration().ORIENTATION_UNDEFINED);
					parameters.set("orientation", "undefined");
				}

			} else {
				System.out.println("in else");
				// List<Camera.Size> sizes = parameters.getSupportedPreviewSizes();
				// Camera.Size optimalSize = getOptimalPreviewSize(sizes, w, h);
				int width = parameters.getPreviewSize().width;
				int height = parameters.getPreviewSize().height;

				// Camera.Size optimalSize = sizes.get(0);

				Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
				if (display.getRotation() == Surface.ROTATION_0) {
					parameters.setPreviewSize(width, height);
					try {
						mCamera.setDisplayOrientation(90);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (display.getRotation() == Surface.ROTATION_90) {
					parameters.setPreviewSize(width, height);

				}

				if (display.getRotation() == Surface.ROTATION_180) {
					parameters.setPreviewSize(width, height);
					try {
						mCamera.setDisplayOrientation(270);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				if (display.getRotation() == Surface.ROTATION_270) {
					parameters.setPreviewSize(width, height);
					try {
						mCamera.setDisplayOrientation(180);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
			mCamera.setParameters(parameters);

			mCamera.startPreview();
		}

	}

	class SaveImageTask extends AsyncTask<Void, Void, Void> {
		private String outFileName;
		private final ProgressDialog progressDialog = new ProgressDialog(CameraActivity.this);

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected Void doInBackground(Void... params) {
			Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.logostrip, options);
			final Bitmap savedImage = combineImages(b, bMapRotate);
			FileOutputStream out;
			try {
				long currentTime = System.currentTimeMillis();
				String imagename = "saturn_mobi" + currentTime + ".jpg";
				// outFileName = "/data/data/" + getPackageName();// + "/databases/" + imagename;

				outFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
				File file = new File(outFileName, "Saturn Mobi Gallery");
				if (!file.exists()) {
					file.mkdir();
				}
				outFileName = file.getAbsolutePath() + "/" + imagename;

				out = new FileOutputStream(outFileName);
				savedImage.compress(Bitmap.CompressFormat.JPEG, 90, out);

				// startMediaScanner("/"+Constants.path+Constants.imagename);
				if (bMapRotate != null) {
					bMapRotate.recycle();
					bMapRotate = null;

				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (mCamera != null) {
				mCamera.stopPreview();
				mCamera.setPreviewCallback(null);
				mCamera.release();
				mCamera = null;
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			try {
				// sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
				// CommonFunctions.startNewActivity(CameraActivity.this, 18, 900);
				// saturnMobiCameraCallBack.notifyResponse(outFileName);

				if (progressDialog.isShowing()) {
					progressDialog.dismiss();
				}

				b = null;
				bMapRotate = null;

				findViewById(R.id.image_show_layout).setVisibility(View.VISIBLE);
				findViewById(R.id.image).setVisibility(View.GONE);

				try {
					// InputStream ips = new FileInputStream(outFileName);
					InputStream ips = new FileInputStream(new File(outFileName));
					((ImageView) findViewById(R.id.image_show)).setImageBitmap(BitmapFactory.decodeStream(ips));

					findViewById(R.id.save).setOnClickListener(onClickListener);
					findViewById(R.id.share).setOnClickListener(onClickListener);

				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {

				case R.id.save:

					onBackPressed();
					break;
				case R.id.share:
					ArrayList<Uri> imageUris = new ArrayList<Uri>();
					imageUris.add(Uri.fromFile(new File(outFileName)));
					Intent shareIntent = new Intent();
					shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);

					shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageUris);
					shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, outFileName);
					shareIntent.setType("image/*");
					startActivity(Intent.createChooser(shareIntent, "Share images to.."));

					break;

				default:
					break;
				}
			}
		};

	}

	@Override
	protected void onDestroy() {
		if (mCamera != null) {
			mCamera.stopPreview();
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onStart() {

		super.onStart();
		FlurryAgent.setCaptureUncaughtExceptions(true);
		FlurryAgent.onStartSession(this, Constants.flurryKey);

		FlurryAgent.logEvent(Constants.FLURRY_CAPTURE);
	}

	@Override
	protected void onStop() {

		super.onStop();
		FlurryAgent.onEndSession(this);
	}

}
