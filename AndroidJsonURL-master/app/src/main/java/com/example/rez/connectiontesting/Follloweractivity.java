package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Follloweractivity extends AppCompatActivity implements CallBackMe{

    TextView followerName;
    String url;
    String profile = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follloweractivity);

        followerName= (TextView)findViewById(R.id.followername);



        Intent intent=getIntent();
        url= intent.getStringExtra("url");
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        JsonRetriever.RetrieveFromURL(this,url,this);
   }

    public void ViewProfile(View v)
    {
        if(profile=="")
        {
            Toast.makeText(this, "please try again", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("url", profile);
            startActivity(intent);
        }
    }
  @Override
    public void CallThis(String jsonText) {
        try {
            JSONArray json = new JSONArray(jsonText);

            JSONObject myfirstfollower=json.getJSONObject(1);
            profile=myfirstfollower.getString("url");
            followerName.setText(myfirstfollower.getString("login"));

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, e+"error", Toast.LENGTH_SHORT).show();
        }

    }
}
