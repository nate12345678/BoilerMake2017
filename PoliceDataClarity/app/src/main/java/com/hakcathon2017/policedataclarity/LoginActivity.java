package com.hakcathon2017.policedataclarity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends ExtendedActivity {

	EditText usernameText;
	EditText passwordText;

	public void login(View v) {
		if (passwordText.getText().toString().equals("aaaa")) {
			Globals.username = usernameText.getText().toString().toUpperCase();
			Log.w("login", Globals.username);
			SharedPreferences prefs = getSharedPreferences("settings", 0);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putString("username", Globals.username);
			editor.commit();
			startActivity(new Intent(getApplicationContext(), MainActivity.class));
		}
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		usernameText = (EditText) findViewById(R.id.usernameText);
		passwordText = (EditText) findViewById(R.id.passwordText);
		Log.w("test", "launch");
	}

}
