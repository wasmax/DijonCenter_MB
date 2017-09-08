package com.example.barthelemy.dijoncenter_mb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.barthelemy.dijoncenter_mb.Model.Pois;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class DetailActivity extends AppCompatActivity {

    TextView tvName;
    TextView tvType;
    TextView tvAddress;
    TextView tvCP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pois);

        tvName = (TextView) findViewById(R.id.tvName);
        tvType = (TextView) findViewById(R.id.tvType);
        tvAddress = (TextView) findViewById(R.id.tvaddress);
        tvCP = (TextView) findViewById(R.id.tvcpville);

        Intent i = getIntent();
        Pois pois = (Pois) i.getSerializableExtra("pois");

        tvName.setText(pois.getName());
        tvType.setText("Type : " + pois.getType());
        tvAddress.setText(pois.getLocation().getAddress());
        tvCP.setText(pois.getLocation().getPostalCode() + " " + pois.getLocation().getCity());
    }
}
