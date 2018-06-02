package com.prt.app.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.Html;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShareUtil {

    public static Uri createUriFromFilePath(@NonNull Context ctx, final String imagePath) {
        final File file = new File(imagePath);
        if (file.exists()) {
            return Uri.fromFile(file);
        } else {
            return null;
        }
    }

    public static void sendingImageWithCaption(@NonNull Context ctx, final String imagePath) {
        final Uri uri = createUriFromFilePath(ctx, imagePath);
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("*/*");
        shareIntent.setDataAndType(uri, "*/*");
//        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Share To Earn");
        ctx.startActivity(Intent.createChooser(shareIntent, "Share To Earn"));
    }

    public static void sendingImageWithCaption(@NonNull Context ctx,
                                               final String caption, @NonNull final Bitmap bm) {
        final Uri uri = createUriFromBitmap(ctx, bm, caption);
        sendingImageWithCaption(ctx, uri);
    }

    public static Uri createUriFromBitmap(@NonNull Context context,
                                          @NonNull Bitmap bitmap, final String caption) {
        final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        final String path = MediaStore.Images.Media.
                insertImage(context.getContentResolver(), bitmap, caption, null);
        return Uri.parse(path);
    }

    public static ArrayList<Uri> createUriFromBitmap(@NonNull Context context, final List<Bitmap> bitmapList,
                                                     final List<String> captionList) {
        final ArrayList<Uri> uriList = new ArrayList<>();
        if (bitmapList != null && !bitmapList.isEmpty() && captionList != null && !captionList.isEmpty()
                && bitmapList.size() == captionList.size()) {
            for (int i = 0; i < bitmapList.size(); i++) {
                final Bitmap bitmap = bitmapList.get(i);
                final String caption = captionList.get(i);
                uriList.add(createUriFromBitmap(context, bitmap, caption));
            }
        }
        return uriList;
    }

    public static void sendingImageWithCaption(@NonNull Context ctx, final Uri uri) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Share To Earn");
        ctx.startActivity(Intent.createChooser(shareIntent, "Share To Earn"));
    }

    public static void sendingMultipleImageWithCaption(@NonNull Context ctx,
                                                       final List<String> caption, final List<Bitmap> bm) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        final ArrayList<Uri> uriList = createUriFromBitmap(ctx, bm, caption);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Share To Earn");
        ctx.startActivity(Intent.createChooser(shareIntent, "Share To Earn"));
    }

    public void sendingHtml(@NonNull Context ctx, final String htmlSource) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/html");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(htmlSource)); // htmlSource i.e. "<p>This is the text shared.</p>"
        ctx.startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }

    public void sendingLinks(@NonNull Context ctx, final String value) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, value); // value i.e. "http://codepath.com"
        ctx.startActivity(Intent.createChooser(shareIntent, "Share link using"));
    }
}