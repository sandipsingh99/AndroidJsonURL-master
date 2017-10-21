package com.example.rez.connectiontesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class FollowersActivity extends AppCompatActivity implements CallBackMe {
    TextView followersname;
    String url;

    TextView login = null;
    TextView id = null;
    int i;
    ImageView avatar = null;
    String profileUrl="";

    Profile[] followers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        followersname=(TextView)findViewById(R.id.followername);
        Intent i=getIntent();
        url=i.getStringExtra("url");


        JsonRetriever.RetrieveFromURL(this, url, this);
    }
    public void goBackMain(View v)
    {
            if(profileUrl=="") {
                Toast.makeText(this,"please try again when page loaded",Toast.LENGTH_LONG).show();
            }
            else
            {
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("url",profileUrl);
                startActivity(i);

            }


    }
    /* if(followersUrl=="")
        {

           Intent  i=new Intent(this,MainActivity.class);
            i.putExtra("url",followersUrl);
             Toast.makeText(this,"please try again when page loaded",Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent  i=new Intent(this,FollowersActivity.class);
            i.putExtra("url",followersUrl);
            startActivity(i);

        }*/

    @Override
    public void CallThis(String jsonText) {
        try {

            JSONArray jsonArray= new JSONArray(jsonText);;
            JSONObject follower;
            followers = new Profile[jsonArray.length()];

            for(i=0;i<=jsonArray.length();i++)
            {
                follower = jsonArray.getJSONObject(i);
                Profile currentFollower = new Profile(follower);
                followers[i] = currentFollower;
            }


            Toast.makeText(this,url, Toast.LENGTH_LONG).show();


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,"error", Toast.LENGTH_LONG).show();

        }


    }
}
