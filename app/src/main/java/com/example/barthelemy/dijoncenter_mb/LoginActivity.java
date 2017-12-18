package com.example.barthelemy.dijoncenter_mb;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.barthelemy.dijoncenter_mb.Model.Parcours;
import com.example.barthelemy.dijoncenter_mb.Model.Personne;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText txtLogin;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtLogin = (EditText)findViewById(R.id.txtLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtLogin.getText().toString().trim().length() > 0){
                    //Appeler le content pour vérifier si le login existe
                    String login = txtLogin.getText().toString();
                    Uri URL = Uri.parse("content://com.example.barthelemy.myhealthbarthelemy/login/" + login);
                    Cursor cursor = getContentResolver().query(URL, new String[]{"nomPersonne", "prenomPersonne", "poidsPersonne"},null,null,null);
                    Integer res = cursor.getCount();

                    ArrayList<Personne> personnes = new ArrayList<Personne>();

                    if(cursor.getCount() > 0){
                        while (cursor.moveToNext()) {
                            Personne pers = new Personne();
                            pers.setNom(cursor.getString(0));
                            pers.setPrenom(cursor.getString(1));
                            pers.setPoids(cursor.getInt(2));
                            personnes.add(pers);
                        }
                        Toast.makeText(LoginActivity.this, "Bienvenue " + personnes.get(0).getPrenom(), Toast.LENGTH_SHORT).show();

                        String typeRestos = "";
                        if(personnes.get(0).getPoids() > 80){
                            typeRestos = "regime";
                        }
                        else{
                            typeRestos = "gras";
                        }

                        //On passe des données dans l'intent
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        i.putExtra("typeRestos", typeRestos);
                        startActivity(i);

                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Connexion impossible.", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "Connexion impossible.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
