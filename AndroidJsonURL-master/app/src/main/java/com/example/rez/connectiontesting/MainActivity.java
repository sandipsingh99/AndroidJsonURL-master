package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rez on 2017-09-02.
 * This is a sample for my students to retrieve Json Files from URL
 * Using the Github Api Json
 * Example: https://api.github.com/users/MadReza
 */
public class MainActivity extends AppCompatActivity implements CallBackMe {

    TextView login = null;
    ImageView avatar = null;
    String followerurl="";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (TextView) findViewById(R.id.login);
        avatar = (ImageView) findViewById(R.id.avatar);


        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        if(url==null)
        {
             url = "https://api.github.com/users/MadReza";

        }
           //This will retrieve the string json from the URL requested
            JsonRetriever.RetrieveFromURL(this, url, this); //First Param for Context, Last Param for Callback Function
            //First param is required for the library
            //Third param, allows to use any class that implements CallBackMe
        }


    public void FollowerClicked(View v) {
        if (followerurl == "") {
            Toast.makeText(this, "please try again when page loaded", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, Follloweractivity.class);
            intent.putExtra("url", followerurl);
            startActivity(intent);
        }
    }


    @Override
    public void CallThis(String jsonText) {

        //Parse the Json here
        //Good examples: https://stackoverflow.com/questions/8091051/how-to-parse-json-string-in-android

        try {
            JSONObject json = new JSONObject(jsonText);

            //Set Login Text
            login.setText(json.getString("login"));

            //set follower URL
            followerurl = json.getString("followers_url");

            //Set Avatar Image From URL
            new DownloadImageTask(avatar)
                    .execute(json.getString("avatar_url"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
