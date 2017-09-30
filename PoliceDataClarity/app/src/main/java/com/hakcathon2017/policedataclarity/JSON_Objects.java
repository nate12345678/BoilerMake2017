package com.hakcathon2017.policedataclarity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by Pavitra on 30-Sep-17.
 */

public class JSON_Objects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trial);
        try {
             trial();
        } catch(Exception e) {

        }
    }
    public void trial() throws JSONException
    {
        String data ="http://www.bsservicess.com/photoUpload/star_avg.php?bookName=";
        TextView t= (TextView)findViewById(R.id.textView5);
        t.setText(data);
        JSONArray jsonArr = new JSONArray(data);

        for (int i = 0; i < jsonArr.length(); i++)
        {
            JSONObject jsonObj = jsonArr.getJSONObject(i);

            System.out.println(jsonObj);
        }

    }

}




