package com.example.pm2e10005;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLlamada extends AppCompatActivity {
    TextView numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);
        numero=(TextView) findViewById(R.id.txtnumero);
        Intent intent = getIntent();
        String d1 = intent.getStringExtra("dato");
        numero.setText(d1);
        Button btnAtras=(Button) findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PantallaLista.class);
                startActivity(intent);
            }
        });
    }

}