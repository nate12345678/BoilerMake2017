package com.hakcathon2017.policedataclarity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
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
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
		implements NavigationView.OnNavigationItemSelectedListener {

	LinearLayout insightsView;
	LinearLayout rankingView;
	ScrollView weekView;
	ScrollView careerView;
	RelativeLayout mainLayout;

	Toolbar toolbar;

	TextView cadUnitText;
	TextView weekDispText;
	TextView weekOverviewText;
	TextView careerStatsText;
	TextView extraCallsText;
	TextView trafficStopText;
	TextView shotsFiredText;
	TextView gnText;
	TextView suicideText;
	TextView comIntText;
	TextView perGunText;
	TextView perDownText;


	Thread averageDailyHours = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.allSched);
			String s;
			String[] D;
			int yearStr;
			int monthStr;
			int dayStr;
			int dayEnd;
			int monthEnd;
			int yearEnd;
			int hourStr;
			int hourEnd;
			int minStr;
			int minEnd;


			int noOfHours = 0;
			for (int i = 0; i <= a.size(); i++) {

				s = a.get(i).StartTime;

				s = (s.split(" "))[0];
				Log.w("s", s);
				D = s.split("-");
				Log.w("D[0]", D[0]);
				yearStr = Integer.parseInt(D[0]);
				monthStr = Integer.parseInt(D[1]);
				dayStr = Integer.parseInt(D[2]);
				s = a.get(i).EndTime;

				s = (s.split(" "))[0];
				Log.w("s", s);
				D = s.split("-");
				dayEnd = Integer.parseInt(D[2]);
				monthEnd = Integer.parseInt(D[1]);
				dayEnd = Integer.parseInt(D[2]);

				s = a.get(i).StartTime;

				s = (s.split(" "))[2];
				D = s.split(":");
				minStr = Integer.parseInt(D[1]);
				hourStr = Integer.parseInt(D[0]);
				Log.w("s", Integer.toString(hourStr));
				s = a.get(i).EndTime;

				s = (s.split(" "))[2];
				D = s.split(":");
				minEnd = Integer.parseInt(D[1]);
				hourEnd = Integer.parseInt(D[0]);

				//Not considering edge cases and minutes
				if ((dayStr - dayEnd) > 1) {
					noOfHours += (dayStr - dayEnd - 1) * 24;
					noOfHours += (24 - hourStr) + hourEnd;
				} else if ((dayStr - dayEnd) == 1) {
					noOfHours += (24 - hourStr) + hourEnd;
				} else {
					noOfHours += -(hourStr - hourEnd);
				}
				Globals.averageDailyHours = noOfHours;
				//Log.w("hours", Double.toString(Globals.averageDailyHours = noOfHours));
			}
		}
	});


	Thread weeklyDispatcherCalls = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekDisp);

			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Type);
			}
			Globals.noOfDSPType = list.size();
			try {
				Thread.sleep(50);
			} catch (Exception e) {
			}
			Log.w("weekly dispatch", Integer.toString(list.size()));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					weekDispText.setText(Integer.toString(Globals.noOfDSPType));
				}
			});
		}
	});


	Thread weekOverview = new Thread(new Runnable() {
		@Override
		public void run() {
			final ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.allLastWeek);
			String text = "";
			for (JsonObject j : a) {
				text += "ID: " + j.Id + "\n    Start Time: " + j.StartTime + "\n    End Time: " + j.EndTime + "\n    Type: " + j.Type +
						"\n    Code: " + j.Code + "\n    Description: " + j.Descr + "\n\n\n";
			}
			text += "                                                                                                                          " +
					"                                  ";
			Log.w("week overview", Integer.toString(a.size()));
			final String finalText = text;
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					weekOverviewText.setText(finalText);
				}
			});
		}
	});


	Thread extraCalls = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekStkDisp);
			//Log.w("testing dsp",Integer.toString(a.size()));
			//Log.w("Get url", Globals.mainURL + Globals.allDisp);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Type);
			}

			Globals.noOfSDKDSPType = list.size();
			Log.w("testing dsp", Integer.toString(list.size()));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					extraCallsText.setText(Integer.toString(Globals.noOfSDKDSPType));
				}
			});
		}
	});


	Thread tStop = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekStops);
			//Log.w("testing dsp",Integer.toString(a.size()));
			//Log.w("Get url", Globals.mainURL + Globals.allDisp);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Type);
			}

			Globals.noOfTSTOPType = list.size();
			//Log.w("testing dsp",Integer.toString(count));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					trafficStopText.setText(Integer.toString(Globals.noOfTSTOPType));
				}
			});
		}
	});


	Thread gn = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekGn);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Code);
			}

			Globals.GN = list.size();
			//Log.w("testing dsp",Integer.toString(count));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gnText.setText(Integer.toString(Globals.GN));
				}
			});
		}
	});


	Thread threaS = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekThreas);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Code);
			}
			Globals.THREAS = list.size();
			//Log.w("testing dsp",Integer.toString(count));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					suicideText.setText(Integer.toString(Globals.THREAS));
				}
			});
		}
	});


	Thread comInt = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekComint);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Code);
			}

			Globals.COMINT = list.size();
			//Log.w("testing dsp",Integer.toString(count));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					comIntText.setText(Integer.toString(Globals.COMINT));
				}
			});
		}
	});


	Thread perGun = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.lastWeekShotsF);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Code);
			}

			Globals.perGun = list.size();
			//Log.w("testing dsp",Integer.toString(count));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					perGunText.setText(Integer.toString(Globals.perGun));
				}
			});
		}
	});


	Thread perDown = new Thread(new Runnable() {
		@Override
		public void run() {
			ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL+ Globals.lastWeekPerDown);
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < a.size(); i++) {
				list.add((a.get(i)).Code);
			}

			Globals.PERDOWN = list.size();
			//Log.w("testing dsp",Integer.toString(count));
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					perDownText.setText(Integer.toString(Globals.PERDOWN));
				}
			});
		}
	});


	Thread careerStats = new Thread(new Runnable() {
		@Override
		public void run() {
			final ArrayList<JsonObject> a = returnJsonArray(Globals.mainURL + Globals.allButSched);
			String text = "Lifetime activity (excluding hours worked):\n\n";
			for (JsonObject j : a) {
				text += "ID: " + j.Id + "\n    Start Time: " + j.StartTime + "\n    End Time: " + j.EndTime + "\n    Type: " + j.Type +
						"\n    Code: " + j.Code + "\n    Description: " + j.Descr + "\n\n\n";
			}
			text += "                                                                                                                          " +
					"                                  ";
			Log.w("Career stats", Integer.toString(a.size()));
			final String finalText = text;
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					careerStatsText.setText(finalText);
				}
			});
		}
	});

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SharedPreferences prefs = getSharedPreferences("settings", 0);
		Globals.username = prefs.getString("username", "NULL");
		//Log.w("test", Globals.username);
		if (Globals.username.equals("NULL")) {
			startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
			finish();
		}

		Globals.mainURL = "http://claritybm5.azurewebsites.net/odata/Events?$filter=CadUnit%20eq%20%27" + Globals.username + "%27";
		Globals.precinct = Globals.username.substring(0, 2);

		insightsView = (LinearLayout) findViewById(R.id.insightsView);
		rankingView = (LinearLayout) findViewById(R.id.rankingView);

		weekView = (ScrollView) findViewById(R.id.weekView);
		careerView = (ScrollView) findViewById(R.id.careerView);

		cadUnitText = (TextView) findViewById(R.id.cadUnitText);
		weekDispText = (TextView) findViewById(R.id.weekDispText);
		careerStatsText = (TextView) findViewById(R.id.careerStatsText);
		weekOverviewText = (TextView) findViewById(R.id.weekOverviewText);
		extraCallsText = (TextView) findViewById(R.id.extraCallsText);
		trafficStopText = (TextView) findViewById(R.id.trafficStopText);
		shotsFiredText = (TextView) findViewById(R.id.shotsFiredText);
		gnText = (TextView) findViewById(R.id.gnText);
		suicideText = (TextView) findViewById(R.id.suicideText);
		comIntText = (TextView) findViewById(R.id.comIntText);
		perGunText = (TextView) findViewById(R.id.perGunText);
		perDownText = (TextView) findViewById(R.id.perDownText);


		mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

		rankingView.setVisibility(View.GONE);
		weekView.setVisibility(View.GONE);
		careerView.setVisibility(View.GONE);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		toolbar.setTitle("Insights");

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.setDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);
		(new Thread(new Runnable() {
			@Override
			public void run() {
				weekOverview.start();
				careerStats.start();

				extraCalls.start();
				tStop.start();
				weeklyDispatcherCalls.start();
				perGun.start();
				perDown.start();
				threaS.start();
				comInt.start();
				gn.start();

				try {
					weeklyDispatcherCalls.join();
					tStop.join();
					extraCalls.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		})).start();
	}


	public ArrayList<JsonObject> returnJsonArray(String url) {
		try {
			URL mainURL = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) mainURL.openConnection();
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
			br.close();

			conn.disconnect();
			Globals.list.clear();
			JSONArray jsonArr = new JSONArray(output);
			for (int i = 0; i < jsonArr.length(); i++) {

				JSONObject jsonObj = jsonArr.getJSONObject(i);
				JsonObject data = new JsonObject(jsonObj.getString("Id"), jsonObj.getString("CadUnit"), jsonObj.getString("OrgUnit"),
						jsonObj.getString("StartTime"), jsonObj.getString("EndTime"), jsonObj.getString("Type"), jsonObj.getString("Code"),
						jsonObj.getString("Descr"));
				Globals.list.add(data);
				/*String Id=jsonObj.getString("Id");
				Log.w("test",Id);
				*/
			}

			//String a=list[0].
			Log.w("test JSON return", Integer.toString(Globals.list.size()));


		} catch (IOException | JSONException e) {

			e.printStackTrace();
		}
		return Globals.list;
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
			toolbar.setTitle("Insights");
		} else if (id == R.id.nav_ranking) {
			rankingView.setVisibility(View.VISIBLE);
			toolbar.setTitle("Rankings");
		} else if (id == R.id.nav_week) {
			weekView.setVisibility(View.VISIBLE);
			toolbar.setTitle("Week Overview");
		} else if (id == R.id.nav_career) {
			careerView.setVisibility(View.VISIBLE);
			toolbar.setTitle("Career");
		} else if (id == R.id.nav_settings) {
			Snackbar.make(mainLayout, "No Settings at this time", Snackbar.LENGTH_SHORT).show();
		} else if (id == R.id.logout) {
			Globals.username = "NULL";
			startActivity(new Intent(getApplicationContext(), LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
			finish();
		}

		DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;
	}
}
