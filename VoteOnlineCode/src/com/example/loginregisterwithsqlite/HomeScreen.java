package com.example.loginregisterwithsqlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends Activity {

	private Button userBtn, adminBtn, reportBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		adminBtn = (Button) findViewById(R.id.admin_btn);
		userBtn = (Button) findViewById(R.id.user_btn);
		reportBtn = (Button) findViewById(R.id.report_btn);

		adminBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeScreen.this, AddAdminScreen.class);
			    startActivity(i);
			}
		});

		userBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeScreen.this, LoginAndRegistrion.class);
				startActivity(i);
			}
		});

		reportBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				Intent i = new Intent(HomeScreen.this, ReportScreen.class);
			    startActivity(i);
			}
		});

	}

}
