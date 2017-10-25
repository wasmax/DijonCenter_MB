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
import android.widget.ListView;
import android.widget.Toast;

import com.example.barthelemy.dijoncenter_mb.Adapters.ParcoursAdapter;
import com.example.barthelemy.dijoncenter_mb.Adapters.PoisAdapter;
import com.example.barthelemy.dijoncenter_mb.Database.DiiageBaseDb;
import com.example.barthelemy.dijoncenter_mb.Model.Location;
import com.example.barthelemy.dijoncenter_mb.Model.Parcours;
import com.example.barthelemy.dijoncenter_mb.Model.Pois;
import com.example.barthelemy.dijoncenter_mb.Model.Position;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

public class ParcoursActivity extends AppCompatActivity {

    Button btnAdd;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcours);

        btnAdd = (Button)findViewById(R.id.btnAdd);
        lv = (ListView)findViewById(R.id.lstParcours);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ParcoursActivity.this, AddParcoursActivity.class);
                startActivity(i);
            }
        });

        DiiageBaseDb myDb = new DiiageBaseDb(this);
        SQLiteDatabase db = myDb.getWritableDatabase();

        ParcoursAdapter pa = new ParcoursAdapter(db, ParcoursActivity.this);
        lv.setAdapter(pa);

        //ArrayAdapter<Parcours> adapter = new ArrayAdapter<Parcours>(this,
                    //android.R.layout.simple_list_item_1, pa.getAllParcours());
        //lv.setAdapter(adapter);
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
