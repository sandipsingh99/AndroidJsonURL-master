package com.example.rez.connectiontesting;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 1694676 on 9/2/2017.
 */

public class Profile {
    public String Login;
    public String Avatar;
    public String url;
    public String followersURL;
    public String followingURL;
    public String reposURL;

    public Profile(JSONObject jsonProfile)
    {
        try {
            Login = jsonProfile.getString("login");
            Avatar = jsonProfile.getString("avatar_url");
            url = jsonProfile.getString("url");
            followersURL = jsonProfile.getString("followers_url");
            followingURL = jsonProfile.getString("following_url");    //String Manipulation required.
            reposURL = jsonProfile.getString("repos_url");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
