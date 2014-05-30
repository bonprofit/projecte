package com.example.bonprofit.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	static String DATABASE_NAME = "Opinions";
	public static final String TABLE_NAME = "comentaris";
	public static final String KEY_TITOL = "titol";
	public static final String KEY_TEMA = "tema";
	public static final String KEY_DESCRIPCIO = "descripcio";
	public static final String KEY_ID = "id";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + KEY_ID
				+ " INTEGER PRIMARY KEY, " + KEY_TITOL + " TEXT, " + KEY_TEMA
				+ " TEXT, " + KEY_DESCRIPCIO + " TEXT)";
		db.execSQL(CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);

	}

}
