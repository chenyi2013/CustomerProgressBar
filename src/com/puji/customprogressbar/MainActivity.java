package com.puji.customprogressbar;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.puji.customprogressbar.R;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends ActionBarActivity implements
		Button.OnClickListener {

	private Button mAddBtn;
	private Button mSubBtn;
	private CustomProgressBar mProgress;
	ProgressBar probar;

	private int mCurrentProgress = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		mAddBtn = (Button) findViewById(R.id.add);
		mAddBtn.setOnClickListener(this);

		mSubBtn = (Button) findViewById(R.id.sub);
		mSubBtn.setOnClickListener(this);

		mProgress = (CustomProgressBar) findViewById(R.id.progress);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.add:
			if (mCurrentProgress < 1000) {
				mCurrentProgress = mCurrentProgress + 10;
			}

			break;
		case R.id.sub:
			if (mCurrentProgress >= 10) {
				mCurrentProgress = mCurrentProgress - 10;
			}

			break;

		default:
			break;
		}
		mProgress.setProgress(mCurrentProgress);
	}

}
