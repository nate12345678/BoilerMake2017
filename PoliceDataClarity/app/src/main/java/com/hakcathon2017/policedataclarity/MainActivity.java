package com.hakcathon2017.policedataclarity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	LinearLayout insightsView;
	LinearLayout rankingView;
	LinearLayout weekView;
	LinearLayout careerView;


	volatile ArrayList<JsonObject> list = new ArrayList<>();

	TextView cadUnitText;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences prefs = getSharedPreferences("settings", 0);
		Globals.username = prefs.getString("username", "NULL");
		Log.w("test", Globals.username);
		if (Globals.username.equals("NULL")) {
			startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
			finish();
		}

		insightsView = (LinearLayout) findViewById(R.id.insightsView);
		rankingView = (LinearLayout) findViewById(R.id.rankingView);
		weekView = (LinearLayout) findViewById(R.id.weekView);
		careerView = (LinearLayout) findViewById(R.id.careerView);
		cadUnitText = (TextView) findViewById(R.id.cadUnitText);

		rankingView.setVisibility(View.GONE);
		weekView.setVisibility(View.GONE);
		careerView.setVisibility(View.GONE);
//		cadUnitText.setText(Globals.username);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		//String data ="http://www.bsservicess.com/photoUpload/star_avg.php?bookName=";
		//TextView t= (TextView)findViewById(R.id.textView5);
		//t.setText(data);

		run();

	}

	public void run() {
		(new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String mainURL = "http://claritybm5.azurewebsites.net/odata/Events?$top=10&$filter=Code%20eq%20%27GAS%27&$orderby=StartTime%20desc";
					URL url = new URL(mainURL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");
					conn.setRequestProperty("Accept", "application/json");

					if (conn.getResponseCode() != 200) {
						//Log.e("ConnectionError", "Failed : HTTP error code : " + conn.getResponseCode());
						throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
					}
					BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

					String output = "";
					String a;
					//System.out.println("Output from Server .... \n");
					while ((a = br.readLine()) != null) {
						//System.out.println(a);
						output += a;

					}

					conn.disconnect();
					JSONArray jsonArr;


					try {
						jsonArr = new JSONArray(output);
						for (int i = 0; i < jsonArr.length(); i++) {

							JSONObject jsonObj = jsonArr.getJSONObject(i);
							JsonObject data = new JsonObject(jsonObj.getString("Id"), jsonObj.getString("CadUnit"), jsonObj.getString("OrgUnit"), jsonObj.getString("StartTime"), jsonObj.getString("EndTime"), jsonObj.getString("Type"), jsonObj.getString("Code"), jsonObj.getString("Descr"));
							list.add(data);
							/*String Id=jsonObj.getString("Id");
							Log.w("test",Id);
							*/
						}
						//String a=list[0].
						Log.w("test", (list.get(0)).CadUnit);
					} catch (Exception e) {
					}


				} catch (MalformedURLException e) {

					e.printStackTrace();
				} catch (IOException e) {

					e.printStackTrace();

				}
			}
		})).start();


	}

	@Override
	public void onBackPressed() {
		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		if (drawer.isDrawerOpen(GravityCompat.START)) {
			drawer.closeDrawer(GravityCompat.START);
		} else {
			super.onBackPressed();
		}
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

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@SuppressWarnings("StatementWithEmptyBody")
	@Override
	public boolean onNavigationItemSelected(MenuItem item) {
		// Handle navigation view item clicks here.
		int id = item.getItemId();

		insightsView.setVisibility(View.GONE);
		rankingView.setVisibility(View.GONE);
		weekView.setVisibility(View.GONE);
		careerView.setVisibility(View.GONE);
		Log.v("test", "launch");

		if (id == R.id.nav_insights) {
			insightsView.setVisibility(View.VISIBLE);
		} else if (id == R.id.nav_ranking) {
			rankingView.setVisibility(View.VISIBLE);
		} else if (id == R.id.nav_week) {
			weekView.setVisibility(View.VISIBLE);
		} else if (id == R.id.nav_career) {
			careerView.setVisibility(View.VISIBLE);
		} else if (id == R.id.nav_settings) {
			insightsView.setVisibility(View.VISIBLE);
		} else if (id == R.id.logout) ;
		{
			Globals.username = "NULL";
			startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
			finish();
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}

