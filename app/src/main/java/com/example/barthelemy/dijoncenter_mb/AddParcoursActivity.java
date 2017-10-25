package com.example.barthelemy.dijoncenter_mb;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barthelemy.dijoncenter_mb.Adapters.PoisAdapter;
import com.example.barthelemy.dijoncenter_mb.Database.DiiageBaseDb;
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

public class AddParcoursActivity extends AppCompatActivity {

    private static final String URL_API = "https://my-json-server.typicode.com/lpotherat/pois/pois";
    ArrayList<String> allCinemas;
    ArrayList<String> allResto;
    Spinner spnCine;
    Spinner spnResto;
    Spinner spnStatus;
    Button btnAdd;
    String idCine;
    String idResto;
    EditText et;
    EditText etComm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_parcours);

        DiiageBaseDb maDb = new DiiageBaseDb(this);
        final SQLiteDatabase db = maDb.getWritableDatabase();

        spnCine = (Spinner)findViewById(R.id.spCine);
        spnResto = (Spinner)findViewById(R.id.spResto);
        spnStatus = (Spinner)findViewById(R.id.spStatut);

        btnAdd = (Button)findViewById(R.id.btnAddParcours);

        et = (EditText)findViewById(R.id.etDate);
        etComm = (EditText)findViewById(R.id.txtComment);

        allCinemas = new ArrayList<>();
        allResto = new ArrayList<>();

        callAPi();

        ArrayList<String> statuts = new ArrayList<>();
        statuts.add("A venir");
        statuts.add("En cours");
        statuts.add("Terminé");
        statuts.add("Annulé");

        ArrayAdapter<String> arrayadapterStatut =
                new ArrayAdapter<String>(AddParcoursActivity.this, android.R.layout.simple_spinner_dropdown_item, statuts);
        spnStatus.setAdapter(arrayadapterStatut);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

                                if(obj.getString("name").equals(spnCine.getSelectedItem().toString())){
                                    idCine = obj.getString("id");
                                }
                                else if(obj.getString("name").equals(spnResto.getSelectedItem().toString())){
                                    idResto = obj.getString("id");
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();


                String insert = "INSERT INTO parcours ('nomCinema', 'nomRestaurant','date_creation', 'date_realisation', 'commentaire', 'statut') " +
                        "VALUES ('"+spnCine.getSelectedItem().toString()+"', '"+spnResto.getSelectedItem().toString()+"', 'datetime()', '"+et.getText()+"', '"+etComm.getText()+"', '"+spnStatus.getSelectedItem().toString()+"')";
                db.execSQL(insert);

                Intent i = new Intent(AddParcoursActivity.this, ParcoursActivity.class);
                startActivity(i);
            }
        });

    }

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

                        if(obj.getString("type").equals("CINE")){
                            allCinemas.add(obj.getString("name"));
                        }
                        else if(obj.getString("type").equals("REST")){
                            allResto.add(obj.getString("name"));
                        }
                    }

                    ArrayAdapter<String> arrayadapterRest =
                            new ArrayAdapter<String>(AddParcoursActivity.this, android.R.layout.simple_spinner_dropdown_item, allCinemas);
                    spnResto.setAdapter(arrayadapterRest);

                    ArrayAdapter<String> arrayadapterCine =
                            new ArrayAdapter<String>(AddParcoursActivity.this, android.R.layout.simple_spinner_dropdown_item, allResto);
                    spnCine.setAdapter(arrayadapterCine);


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
