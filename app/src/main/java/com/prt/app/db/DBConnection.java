package com.prt.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Pratyesh Singh
 */
public class DBConnection extends SQLiteOpenHelper {
	private String TABLE_NAME = "";
	private static String DB_NAME = "saturnmobi.sqlite";
	private static int DATABASE_VERSION = 1;

	public DBConnection(Context context, String tName) {
		super(context, DB_NAME, null, DATABASE_VERSION);
		this.TABLE_NAME = tName;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// db.execSQL("CREATE TABLE " + TABLE_NAME + " (product TEXT,product_id TEXT,list_price TEXT,price TEXT,third_price TEXT,image_url TEXT, PRIMARY KEY(product,product_id))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = "ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + "status" + " TEXT";
		db.execSQL(sql);
	}
}
