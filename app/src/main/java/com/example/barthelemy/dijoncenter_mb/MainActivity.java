package com.example.barthelemy.dijoncenter_mb;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.support.annotation.MainThread;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.barthelemy.dijoncenter_mb.Adapters.PoisAdapter;
import com.example.barthelemy.dijoncenter_mb.Model.Location;
import com.example.barthelemy.dijoncenter_mb.Model.Pois;
import com.example.barthelemy.dijoncenter_mb.Model.Position;
import com.example.barthelemy.dijoncenter_mb.Receivers.BatteryReceiver;

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
    ListView lv;
    BatteryReceiver batRec;

    private BroadcastReceiver mSmsBroadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context arg0, Intent intent) {
            Toast.makeText(MainActivity.this, "Message reçu", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        batRec = new BatteryReceiver();

        Toast.makeText(this, R.string.bienvenue, Toast.LENGTH_SHORT).show();

        lv = (ListView)findViewById(R.id.listView);
        allPois = new ArrayList<Pois>();

        callAPi();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a,
                                    View v, int position, long id) {

                Pois pois = (Pois)a.getItemAtPosition(position);
                Intent intent = new Intent(v.getContext(), DetailActivity.class);
                intent.putExtra("pois", pois);
                startActivity(intent);
            }
        });

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECEIVE_SMS)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECEIVE_SMS},
                        0);
            }
        }
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

                        Intent intent = getIntent();
                        String typePois = intent.getStringExtra("typeRestos");

                        if(typePois.equals("regime")){
                            if(obj.getString("name").equals("Ekin Kebab")
                                    || obj.getString("name").equals("Quick")
                                    || obj.getString("name").equals("Subway")
                                    || obj.getString("name").equals("La Boîte à Pasta")
                                    || obj.getString("name").equals("Boludo Empanadas")) {
                            }else{

                                allPois.add(
                                        new Pois(
                                                obj.getString("id"),
                                                obj.getString("type"),
                                                obj.getString("name"),
                                                new Location(location.getString("adress"), location.getString("postalCode"), location.getString("city")),
                                                new Position(Float.parseFloat(position.getString("lat")), Float.parseFloat(position.getString("lon")))));
                            }
                        }
                        else if(typePois.equals("gras")){
                            if(obj.getString("name").equals("Ekin Kebab")
                                    || obj.getString("name").equals("Quick")
                                    || obj.getString("name").equals("Subway")
                                    || obj.getString("name").equals("La Boîte à Pasta")
                                    || obj.getString("name").equals("Boludo Empanadas")){

                                allPois.add(
                                        new Pois(
                                                obj.getString("id"),
                                                obj.getString("type"),
                                                obj.getString("name"),
                                                new Location(location.getString("adress"), location.getString("postalCode"), location.getString("city")),
                                                new Position(Float.parseFloat(position.getString("lat")), Float.parseFloat(position.getString("lon")))));
                            }
                        }
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

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.layout.menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.pois:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                Toast.makeText(this, R.string.pois, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.parcours:
                Intent intent2 = new Intent(this, ParcoursActivity.class);
                startActivity(intent2);
                Toast.makeText(this, R.string.parcours, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.quitter:
                finish();
                return true;
        }
        return false;
    }

}
