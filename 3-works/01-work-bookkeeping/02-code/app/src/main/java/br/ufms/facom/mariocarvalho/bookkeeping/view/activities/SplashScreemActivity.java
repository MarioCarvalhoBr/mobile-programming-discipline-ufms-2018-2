package br.ufms.facom.mariocarvalho.bookkeeping.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import br.ufms.facom.mariocarvalho.bookkeeping.R;

public class SplashScreemActivity extends AppCompatActivity{
	private boolean running = true;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);

		mContext = SplashScreemActivity.this;

		Handler handle = new Handler();
		handle.postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent i = new Intent(SplashScreemActivity.this, MainNavigationDrawerActivity.class);
				startActivity(i);
				finish();
			}
		}, 2000);
	}
}
