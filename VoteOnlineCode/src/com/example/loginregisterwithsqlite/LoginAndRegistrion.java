package com.example.loginregisterwithsqlite;


import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

public class LoginAndRegistrion extends Activity {

	LoginDataBaseAdapter loginDataBaseAdapter;
	Button login;
	Button registerr;
	EditText enterpassword;
	TextView forgetpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_registration);

		login=(Button)findViewById(R.id.login_btn);
		registerr=(Button)findViewById(R.id.register_btn);
		enterpassword=(EditText)findViewById(R.id.password_edt);
		forgetpass=(TextView)findViewById(R.id.textView2);

		loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
		loginDataBaseAdapter.open();

		registerr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(LoginAndRegistrion.this,Registration.class);
				startActivity(i);
			}
		});

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Password=enterpassword.getText().toString();

				String storedPassword=loginDataBaseAdapter.getSinlgeEntry(Password);

				if(Password.equals(storedPassword))
				{
					Intent ii=new Intent(LoginAndRegistrion.this,OtpSubmitScreen.class);
					startActivity(ii);
				}
				else
					if(Password.equals("")){
						Toast.makeText(LoginAndRegistrion.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(LoginAndRegistrion.this, "Password Incorrect", Toast.LENGTH_LONG).show();
					}
			}
		});

		forgetpass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				final Dialog dialog = new Dialog(LoginAndRegistrion.this);
				dialog.getWindow();
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  
				dialog.setContentView(R.layout.forget_search);
				dialog.show();

				final  EditText security=(EditText)dialog.findViewById(R.id.securityhint_edt);
				final  TextView getpass=(TextView)dialog.findViewById(R.id.textView3);

				Button ok=(Button)dialog.findViewById(R.id.getpassword_btn);
				Button cancel=(Button)dialog.findViewById(R.id.cancel_btn);

				ok.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {

						String userName=security.getText().toString();
						if(userName.equals(""))
						{
							Toast.makeText(getApplicationContext(), "Please enter your securityhint", Toast.LENGTH_SHORT).show();
						}
						else
						{
							String storedPassword=loginDataBaseAdapter.getAllTags(userName);
							if(storedPassword==null)
							{
								Toast.makeText(getApplicationContext(), "Please enter correct securityhint", Toast.LENGTH_SHORT).show();
							}else{
								Log.d("GET PASSWORD",storedPassword);
								getpass.setText(storedPassword);
							}
						}
					}
				});
				cancel.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// Close The Database
		loginDataBaseAdapter.close();
	}

}
