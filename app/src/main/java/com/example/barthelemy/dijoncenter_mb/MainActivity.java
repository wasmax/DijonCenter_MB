package com.example.barthelemy.dijoncenter_mb;

import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.barthelemy.dijoncenter_mb.Adapters.PoisAdapter;
import com.example.barthelemy.dijoncenter_mb.Model.Location;
import com.example.barthelemy.dijoncenter_mb.Model.Pois;
import com.example.barthelemy.dijoncenter_mb.Model.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Pois> allPois;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allPois = new ArrayList<Pois>();

        callAPi();
    }

    private static final String URL_API = "https://my-json-server.typicode.com/lpotherat/pois/pois";

    public void callAPi(){

        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                try {
                    URL url = new URL(URL_API);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                        }
                        bufferedReader.close();
                        return stringBuilder.toString();
                    }
                    finally{
                        urlConnection.disconnect();
                    }
                }
                catch(Exception e) {
                    Log.e("ERROR", e.getMessage(), e);
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                try {
                    JSONArray pois = new JSONArray(s);

                    for (int i = 0; i < pois.length(); i++) {
                        JSONObject obj = pois.getJSONObject(i);

                        JSONObject location = obj.getJSONObject("location");
                        JSONObject position = location.getJSONObject("position");

                        allPois.add(
                                new Pois(
                                        obj.getString("id"),
                                        obj.getString("type"),
                                        obj.getString("name"),
                                        new Location(location.getString("adress"), location.getString("postalCode"), location.getString("city")),
                                        new Position(Float.parseFloat(position.getString("lat")), Float.parseFloat(position.getString("lon")))));
                    }


                    PoisAdapter pa = new PoisAdapter(allPois, MainActivity.this);
                    ListView itemsListView  = (ListView) findViewById(R.id.listView);
                    itemsListView.setAdapter(pa);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute();
    }
}
