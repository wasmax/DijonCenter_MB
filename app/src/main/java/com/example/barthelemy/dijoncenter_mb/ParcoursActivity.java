package com.example.barthelemy.dijoncenter_mb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ParcoursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcours);
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
                Toast.makeText(this, "Les Pois", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.parcours:
                Intent intent2 = new Intent(this, ParcoursActivity.class);
                startActivity(intent2);
                Toast.makeText(this, "Mes Parcours", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.quitter:
                finish();
                return true;
        }
        return false;
    }
}
