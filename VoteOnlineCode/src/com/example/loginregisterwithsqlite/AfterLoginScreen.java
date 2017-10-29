/*package com.example.loginregisterwithsqlite;

import android.app.Activity;
import android.os.Bundle;

public class AfterLoginScreen extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.after_login_screen);
	}

}*/


package com.example.loginregisterwithsqlite;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AfterLoginScreen extends Activity {

	ListView list;

	List<String> politicianName,temList;
  LoginDataBaseAdapter loginDataBaseAdapter;

	private List<String> lastName; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vote_screen);
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		temList=loginDataBaseAdapter.getAllPartyName();
		lastName=loginDataBaseAdapter.getAllLastName();
		politicianName=loginDataBaseAdapter.getAllFirstName();
		//Log.e("test", "Data:"+loginDataBaseAdapter.getAllPartyNameCount("NCP"));
		
		CustomListAdapter adapter = new CustomListAdapter(this, temList,politicianName,lastName);
		list = (ListView) findViewById(R.id.candidate_listView);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener() {

			private ImageView icon;
			private String fullName,partyName;

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/** To change selected state view */
				view.setSelected(true);
				String Slecteditem = temList.get(position);
				Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

				final Dialog dialog = new Dialog(AfterLoginScreen.this);
				dialog.getWindow();
				//dialog.setTitle("Confirm your Vote");
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.vote_confirm_dialouge);

				final TextView candidate = (TextView) dialog.findViewById(R.id.CandidateName_txtView);
				final TextView polticianParty = (TextView) dialog.findViewById(R.id.party_txtView);
				icon = (ImageView) dialog.findViewById(R.id.PartyLogo);
				fullName=politicianName.get(position)+" "+lastName.get(position);
				partyName=temList.get(position);
				candidate.setText(politicianName.get(position)+" "+lastName.get(position));
				polticianParty.setText(temList.get(position));
				
				if (temList.get(position).equals("NCP")) {
					icon.setImageResource(R.drawable.watch);
				} else if (temList.get(position).equals("Congress")) {
					icon.setImageResource(R.drawable.plam);
				} else if (temList.get(position).equals("BJP")) {
					icon.setImageResource(R.drawable.bjp);
				} else if (temList.get(position).equals("Shivsena")) {
					icon.setImageResource(R.drawable.shivsena);
				} else {
					icon.setImageResource(R.drawable.independant);
				}
				dialog.show();
				Button voteBtn = (Button) dialog.findViewById(R.id.vote_btn);
				Button cancelBtn = (Button) dialog.findViewById(R.id.cancel_btn);
				voteBtn.setOnClickListener(new View.OnClickListener() {

					public void onClick(View v) {
						Toast.makeText(getApplicationContext(), "Voted for " + candidate.getText(), Toast.LENGTH_SHORT)
								.show();
						loginDataBaseAdapter.insertReportEntry(fullName, partyName);
						dialog.dismiss();
						Intent i = new Intent(AfterLoginScreen.this, HomeScreen.class);
						startActivity(i);
					}
				});
				cancelBtn.setOnClickListener(new OnClickListener() {

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
}






