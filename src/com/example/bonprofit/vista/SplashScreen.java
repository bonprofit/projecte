package com.example.bonprofit.vista;

import com.example.bonprofit.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class SplashScreen extends Activity {

	private static final int SPLASH_TIME = 3 * 1000;// 3 seconds

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash_screen);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent intent = new Intent(SplashScreen.this, Login.class);
				startActivity(intent);

				SplashScreen.this.finish();

				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

			}
		}, SPLASH_TIME);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
			}
		}, SPLASH_TIME);

	}

	@Override
	public void onBackPressed() {
		this.finish();
		super.onBackPressed();
	}
}
