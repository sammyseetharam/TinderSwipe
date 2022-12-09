package com.jabirdeveloper.tinderswipe.Connectors;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jabirdeveloper.tinderswipe.Model.Artist;
import com.jabirdeveloper.tinderswipe.Model.Item;
import com.jabirdeveloper.tinderswipe.Model.Playlist;
import com.jabirdeveloper.tinderswipe.Model.Song;
import com.jabirdeveloper.tinderswipe.R;
import com.jabirdeveloper.tinderswipe.VolleyCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SongService {
    //https://towardsdatascience.com/using-the-spotify-api-with-your-android-application-the-essentials-1a3c1bc36b9e
    //https://developer.spotify.com/console/get-playlist-tracks/

    private ArrayList<Song> songs = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private RequestQueue queue;
    public ArrayList<String> playlistNames = new ArrayList<>();

    private Playlist playlist = new Playlist();
    private ArrayList<Item> playlistItems = new ArrayList<>();
    private ArrayList<Artist> artists = new ArrayList<>();


    private String userID = "";
    private Playlist myNewPlaylist = new Playlist();


    //final private String playlistID = "4RHzWhzEqiHn60jqif4i7d";

    public SongService(Context context) {
        sharedPreferences = context.getSharedPreferences("SPOTIFY", 0);
        queue = Volley.newRequestQueue(context);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
    public ArrayList<Item> getItems(){
        return playlistItems;
    }

    public Playlist getPlaylist(){
        return playlist;
    }
    public Playlist getMyNewPlaylist(){
        return myNewPlaylist;
    }

    public Playlist getmPlaylist(final VolleyCallBack callBack,String playlistID) {
        playlist = new Playlist();
        String endpoint = "https://api.spotify.com/v1/playlists/" + playlistID;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, response -> {
                            Gson gson = new Gson();
                            playlist = gson.fromJson(response.toString(), Playlist.class);
                            callBack.onSuccess();
                }, error -> {
                    // TODO: Handle error

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
        getmItems(() -> {
            Log.d("Ben","Got Tracks");
        }, playlistID);
        playlist.getTracks().setItems(playlistItems);

        return playlist;
    }


    public ArrayList<Item> getmItems(final VolleyCallBack callBack, String playlistID){
        playlistItems = new ArrayList<Item>();
        String endpoint = "https://api.spotify.com/v1/playlists/" + playlistID + "/tracks";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, response -> {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.optJSONArray("items");
                    for (int n = 0; n < jsonArray.length(); n++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(n);
                            Item item = gson.fromJson(object.toString(), Item.class);
                            playlistItems.add(item);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.onSuccess();
                }, error -> {
                    // TODO: Handle error

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
        return playlistItems;
    }





    public ArrayList<Song> getRecentlyPlayedTracks(final VolleyCallBack callBack) {
        String endpoint = "https://api.spotify.com/v1/me/player/recently-played";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endpoint, null, response -> {
                    Gson gson = new Gson();
                    JSONArray jsonArray = response.optJSONArray("items");
                    for (int n = 0; n < jsonArray.length(); n++) {
                        try {
                            JSONObject object = jsonArray.getJSONObject(n);
                            //object = object.optJSONObject("track");
                            Song song = gson.fromJson(object.toString(), Song.class);
                            songs.add(song);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBack.onSuccess();
                }, error -> {
                    // TODO: Handle error

                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
        return songs;
    }


    public void addSongToLibrary(Song song) {
        JSONObject payload = preparePutPayload(song);
        JsonObjectRequest jsonObjectRequest = prepareSongLibraryRequest(payload);
        queue.add(jsonObjectRequest);
    }

    private JsonObjectRequest prepareSongLibraryRequest(JSONObject payload) {
        return new JsonObjectRequest(Request.Method.PUT, "https://api.spotify.com/v1/me/tracks", payload, response -> {
        }, error -> {
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
    }

    private JSONObject preparePutPayload(Song song) {
        JSONArray idarray = new JSONArray();
        idarray.put(song.getId());
        JSONObject ids = new JSONObject();
        try {
            ids.put("ids", idarray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ids;
    }

    public void makePlaylist(VolleyCallBack callBack,String name)   {
        JSONObject payload = preaparePayload(name);
        JsonObjectRequest jsonObjectRequest = makemPlaylist(callBack,payload);
        queue.add(jsonObjectRequest);
        if(jsonObjectRequest != null) Log.d("Ben", "Playlist Added");
        Log.d("Ben", jsonObjectRequest.toString());
    }


    public JsonObjectRequest makemPlaylist(final VolleyCallBack callback, JSONObject payload){
        UserService uSer = new UserService(queue, sharedPreferences);
        userID = sharedPreferences.getString("userid","");
        String endpoint ="https://api.spotify.com/v1/users/" + userID + "/playlists";

        return new JsonObjectRequest(Request.Method.POST, endpoint, payload, response -> {
            Gson gson = new Gson();
            myNewPlaylist = gson.fromJson(response.toString(), Playlist.class);
            callback.onSuccess();
        }, error -> {
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
    }

    private JSONObject preaparePayload(String name)   {
        JSONObject nameArray = new JSONObject();
        //nameArray.put(name);


        try{
            nameArray.put("name", name);
            nameArray.put("description", "Your personalized playlist courtesy of Spindr");
            nameArray.put("public", true);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return nameArray;
    }






    public void addSongs(ArrayList<Item> songs)   {
        JSONObject payload = preapareSPayload(songs);
        JsonObjectRequest jsonObjectRequest = addmSongs(payload);
        queue.add(jsonObjectRequest);
        if(jsonObjectRequest != null) Log.d("Ben", "Songs Added");
        //Log.d("Ben", jsonObjectRequest.toString());
    }


    public JsonObjectRequest addmSongs( JSONObject payload){
        String playId = myNewPlaylist.getId();
        String endpoint ="https://api.spotify.com/v1/playlists/" + playId + "/tracks";

        return new JsonObjectRequest(Request.Method.POST, endpoint, payload, response -> {
        }, error -> {
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
    }

    private JSONObject preapareSPayload(ArrayList<Item> songs)   {
        JSONArray array = new JSONArray();
        for (Item x : songs) {
            array.put(x.getTrack().getUri());
        }
        JSONObject obj = new JSONObject();
        try{
            obj.put("uris",array);
            obj.put("position",0);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }
}
