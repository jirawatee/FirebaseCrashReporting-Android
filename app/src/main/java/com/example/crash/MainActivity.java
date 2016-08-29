package com.example.crash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
	private static final String TAG = "MainActivity";
	private TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTextView = (TextView) findViewById(R.id.textview);
		findViewById(R.id.button_null_pointer).setOnClickListener(this);
		findViewById(R.id.button_runtime).setOnClickListener(this);

		FirebaseCrash.log("Activity created");
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.button_null_pointer:
				try {
					throw new NullPointerException();
				} catch (NullPointerException ex) {
					FirebaseCrash.logcat(Log.ERROR, TAG, "NPE caught");
					FirebaseCrash.report(ex);
					mTextView.setText(getString(R.string.sent_crash_report, "NullPointerException"));
				}
				break;
			case R.id.button_runtime:
				try {
					throw new RuntimeException();
				} catch (RuntimeException ex) {
					FirebaseCrash.logcat(Log.ERROR, TAG, "RE caught");
					FirebaseCrash.report(ex);
					mTextView.setText(getString(R.string.sent_crash_report_and_logcat, "RuntimeException"));
				}
				break;
		}
	}
}