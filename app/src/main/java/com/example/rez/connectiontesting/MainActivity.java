package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    TextView id = null;
    ImageView avatar = null;
    String followersUrl="";
    Button follow=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (TextView) findViewById(R.id.login);
        avatar = (ImageView) findViewById(R.id.avatar);

        String url = "";

        Intent i=getIntent();

        url=i.getStringExtra("url");
        if(url==null)
        {
            url = "https://api.github.com/users/MadReza";
        }
        //This will retrieve the string json from the URL requested
        JsonRetriever.RetrieveFromURL(this, url, this); //First Param for Context, Last Param for Callback Function
                                    //First param is required for the library
                                    //Third param, allows to use any class that implements CallBackMe
    }
    public void followersClicked(View v)

    {
        if(followersUrl=="")
        {
            Toast.makeText(this,"please try again when page loaded",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent  i=new Intent(this,FollowersActivity.class);
            i.putExtra("url",followersUrl);
            startActivity(i);

        }
    }


    @Override
    public void CallThis(String jsonText) {

        //Parse the Json here
        //Good examples: https://stackoverflow.com/questions/8091051/how-to-parse-json-string-in-android

        try {
            JSONObject json = new JSONObject(jsonText);
            followersUrl=json.getString("followers_url");
            //Set Login Text
            login.setText(json.getString("login"));
    //     String url = json.getString("repos_url");
    //        JsonRetriever.RetrieveFromURL(this, url, this);

            //Set Avatar Image From URL
            new DownloadImageTask(avatar)
                    .execute(json.getString("avatar_url"));

        } catch (JSONException e) {

            e.printStackTrace();
        }

    }
}
